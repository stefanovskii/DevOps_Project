//package mk.finki.ukim.mk.lab.web.servlet;
//
//import mk.finki.ukim.mk.lab.service.BalloonService;
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
//@WebServlet(name = "BalloonList",urlPatterns = "")
//public class BalloonListServlet extends HttpServlet {
//
//    private final SpringTemplateEngine springTemplateEngine;
//    private final BalloonService balloonService;
//
//    public BalloonListServlet(SpringTemplateEngine springTemplateEngine,
//                              BalloonService balloonService){
//        this.springTemplateEngine = springTemplateEngine;
//        this.balloonService = balloonService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        WebContext context = new WebContext(req,resp,req.getServletContext());
//        context.setVariable("categories",this.balloonService.listAll());
//        this.springTemplateEngine.process("listBalloons.html",context,resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getSession().setAttribute("BalloonType",req.getParameter("color"));
//        resp.sendRedirect("/selectBalloon");
//    }
//}
