package com.easymall.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@WebFilter(filterName = "EncodingFilter",value = "/EncodingFilter")
public class EncodingFilter implements Filter {
    private FilterConfig config = null;
    private String encode = "UTF-8";
    private String encodep = "ISO8859-1";
    private boolean bEncode = true;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //响应乱码
        resp.setContentType("text/html;charset=" + encode);
        //判断是否转码
        ServletRequest myRequest=bEncode?new MyHttpServletRequest(encode,encodep, (HttpServletRequest) req):req;

        chain.doFilter(myRequest, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        //获取初始参数
        this.config = config;
        this.encode = config.getServletContext().getInitParameter("encode");
        this.encodep = config.getServletContext().getInitParameter("encodep");
        this.bEncode=Boolean.parseBoolean(config.getServletContext().getInitParameter("bEncode"));
    }

    //装饰类
    class MyHttpServletRequest extends HttpServletRequestWrapper {
        private HttpServletRequest request = null;
        private String encode=null;
        private  String encodep=null;

        public MyHttpServletRequest(String encode,String encodep,HttpServletRequest request) {
            super(request);
            this.request = request;
            this.encode=encode;
            this.encodep=encodep;
        }

        //重写3个方法
        @Override
        public Map<String, String[]> getParameterMap() {
            try {
                if ("POST".equals(request.getMethod())) {
                    request.setCharacterEncoding(encode);
                    return request.getParameterMap();
                }else if("GET".equals(request.getMethod())){
                    Map<String, String[]> map = request.getParameterMap();
                    Map<String,String[]> rmap=new HashMap<String,String[]>();

                    //if(bEncode) {
                        for (Map.Entry<String, String[]> me : map.entrySet()) {
                            String key=me.getKey();
                            String[] value = me.getValue();
                            String[] rv=new String[value.length];

                            for (int i = 0; i < value.length; i++) {
                                rv[i]=new String(value[i].getBytes(encodep),encode);
                            }
                            rmap.put(key,rv);
                        }
//                        bEncode=false;
                    //}
                    return rmap;
                }else{
                    return request.getParameterMap();
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        @Override
        public String[] getParameterValues(String name) {
            return getParameterMap().get(name);
        }

        @Override
        public String getParameter(String name) {
            String[] pv = getParameterValues(name);
            return pv==null?null:pv[0];
        }
    }
}
