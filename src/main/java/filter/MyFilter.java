package filter;


import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author :luo wei
 * @description:  过滤器是用在客户端请求资源时进行匹配并过滤使用的， 基于doFilter方法的参数filterChain实现函数回调，
 *                  是servlet包里的，只能用在web程序中
 *                  执行顺序上先于拦截器：tomcat  -->  filter  -->  servlet  -->  interceptor -->  controller
 * @date 2023/2/13/013 21:26
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化中");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        //模拟通过用户的session来判断用户是否有权限访问请求的页面资源
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Integer status = (Integer)session.getAttribute("status");

        if (status == null || status != 3) {
            System.out.println("你没有权限访问该页面！");
            request.setAttribute("msg","你没有权限访问该页面");
            response.sendRedirect("/MusicWebSite/index.html");
        }else{
            //用户有权限，本过滤器放行，通过filterChain（回调接口）访问下一个过滤器
            System.out.println("过滤器放行--------");
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("正在销毁过滤器....");
    }

}
