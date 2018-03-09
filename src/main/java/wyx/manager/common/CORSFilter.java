package wyx.manager.common;

import org.jeecgframework.core.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {
    String originParam="";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        originParam = filterConfig.getInitParameter("origin");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String _host = request.getParameter("host");
        //没有host参数
        if(StringUtil.isEmpty(_host)){ return;  }

        //设置跨域的域名
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", originParam);
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
