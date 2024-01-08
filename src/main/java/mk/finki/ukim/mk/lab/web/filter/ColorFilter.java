//package mk.finki.ukim.mk.lab.web.filter;
//
//import mk.finki.ukim.mk.lab.service.OrderService;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter
//public class ColorFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    private final OrderService orderService;
//
//    public ColorFilter(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String type = (String) request.getSession().getAttribute("BalloonType");
//        String path = request.getServletPath();
//
//        // prva zadaca
//        /*if((!"".equals(path) && type == null) || "/logout".equals(path)){
//            request.getSession().invalidate();
//            response.sendRedirect("");
//        }
//        else{
//            filterChain.doFilter(servletRequest,servletResponse);
//        }*/
//
//        // vtora zadaca
//        if (("/selectBalloon".equals(path) || "/ConfirmationInfo".equals(path) || "/BalloonOrder.do".equals(path)) && type == null){
//            response.sendRedirect("/balloons");
//        }
//        if("/logout".equals(path)){
//            this.orderService.clearOrders();
//            request.getSession().invalidate();
//            response.sendRedirect("/balloons");
//        }
//        else{
//            filterChain.doFilter(servletRequest,servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
