package edu.nuaa.naive.chat.interfaces;

import cn.hutool.extra.servlet.ServletUtil;
import org.apache.taglibs.standard.lang.jstl.ImplicitObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/16 9:54
 */
//@WebFilter(filterName = "webLogFilter", urlPatterns = "/*")
//@Component
//@Order(-1)
public class WebLogFilter {
    private final Logger LOGGER = LoggerFactory.getLogger(WebLogFilter.class);


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        RequestWrapper requestWrapper = new RequestWrapper(request);
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);

        chain.doFilter(requestWrapper, responseWrapper);

        LOGGER.debug("HTTP {} {} {\n\trequest params: {},\n\trequest body: {},\n\tresponse body: {},\n\tclient ip: {},\n\tcost: {}ms\n}",
                request.getMethod(), request.getRequestURI(), ServletUtil.getParamMap(request), requestWrapper.getBodyAsString(),
                responseWrapper.getContentAsString(), ServletUtil.getClientIP(request), System.currentTimeMillis() - start);

        ServletOutputStream outputStream = servletResponse.getOutputStream();
        outputStream.write(responseWrapper.getContent());
        outputStream.flush();
        outputStream.close();
    }

    public static class RequestWrapper extends HttpServletRequestWrapper {

        private final byte[] body;

        public RequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            body = StreamUtils.copyToByteArray(request.getInputStream());
        }

        @Override
        public ServletInputStream getInputStream() {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
            return new ServletInputStream() {

                public int read() {
                    return byteArrayInputStream.read();
                }

                public boolean isFinished() {
                    return false;
                }

                public boolean isReady() {
                    return false;
                }

                public void setReadListener(ReadListener listener) {
                }
            };
        }

        @Override
        public BufferedReader getReader() {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }

        public byte[] getBody() {
            return body;
        }

        public String getBodyAsString() {
            return new String(body, StandardCharsets.UTF_8);
        }
    }

    public static class ResponseWrapper extends HttpServletResponseWrapper {
        private ByteArrayOutputStream byteArrayOutputStream;
        private ServletOutputStream servletOutputStream;

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
            byteArrayOutputStream = new ByteArrayOutputStream();
            servletOutputStream = new MyServletOutputStream(byteArrayOutputStream);
        }

        @Override
        public ServletOutputStream getOutputStream() {
            return servletOutputStream;
        }

        @Override
        public PrintWriter getWriter() {
            return new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8));
        }

        @Override
        public void flushBuffer() {
            if (servletOutputStream != null) {
                try {
                    servletOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public byte[] getContent() {
            flushBuffer();
            // response中的数据
            return byteArrayOutputStream.toByteArray();
        }

        public String getContentAsString() {
            return new String(getContent(), StandardCharsets.UTF_8);
        }

        class MyServletOutputStream extends ServletOutputStream {
            // 把response输出流中的数据写入字节流中
            private ByteArrayOutputStream byteArrayOutputStream;

            public MyServletOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
                this.byteArrayOutputStream = byteArrayOutputStream;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener listener) {
            }

            @Override
            public void write(int b) {
                byteArrayOutputStream.write(b);
            }
        }
    }
}
