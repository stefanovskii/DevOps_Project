//package mk.finki.ukim.mk.lab.web.servlet;
//
//import mk.finki.ukim.mk.lab.model.Order;
//import mk.finki.ukim.mk.lab.service.OrderService;
//import mk.finki.ukim.mk.lab.service.ShoppingCartService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name="conf-info",urlPatterns = "/ConfirmationInfo")
//public class ConfirmationInfoServlet extends HttpServlet {
//
//    private final SpringTemplateEngine springTemplateEngine;
//    private final OrderService orderService;
//
//    private final ShoppingCartService shoppingCartService;
//
//    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService, ShoppingCartService shoppingCartService) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.orderService = orderService;
//        this.shoppingCartService = shoppingCartService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        WebContext context = new WebContext(req,resp,req.getServletContext());
//        String browser = req.getHeader("User-Agent");
//        String color = (String) req.getSession().getAttribute("BalloonType");
//        String size = (String) req.getSession().getAttribute("BalloonSize");
//        String clientName = (String) req.getSession().getAttribute("clientName");
//        String clientAddress = (String) req.getSession().getAttribute("clientAddress");
//        context.setVariable("ipaddress",req.getRemoteAddr());
//        context.setVariable("browser",browser);
//
//        Order order = this.orderService.save(color,size).get();
//        context.setVariable("order",order);
//        this.springTemplateEngine.process("confirmationInfo.html",context,resp.getWriter());
//    }
//
//}
