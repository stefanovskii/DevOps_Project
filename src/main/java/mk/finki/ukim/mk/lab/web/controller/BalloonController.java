package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    private final OrderService orderService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService, OrderService orderService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Balloon> balloons = this.balloonService.listAll();
        model.addAttribute("balloons",balloons);
        return "listBalloons";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam(required = false) Long id,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer){
        if(id != null){
            this.balloonService.edit(id,name,description,manufacturer);
        }
        else{
            this.balloonService.save(name,description,manufacturer);
        }
        return "redirect:/balloons";
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteBalloon(@PathVariable Long id){
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddBalloonPage(Model model){
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers",manufacturers);
        return "add-balloon";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditBalloonPage(@PathVariable Long id, Model model){
        if(this.balloonService.findById(id).isPresent()){
            Balloon balloon = this.balloonService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("balloon",balloon);
            return "add-balloon";
        }
        return "redirect:/balloons?error=BalloonNotFound";
    }

    @GetMapping("/color")
    public String getBalloonColor(@RequestParam String color, HttpServletRequest request){
        request.getSession().setAttribute("BalloonType",color);
        return "selectBalloonSize";
    }

    @GetMapping("/selectBalloon")
    public String getBalloonSize(@RequestParam String size, HttpServletRequest request){
        request.getSession().setAttribute("BalloonSize",size);
        return "deliveryInfo";
    }

    @GetMapping("/BalloonOrder.do")
    public String getBalloonInfo(@RequestParam String clientAddress,
                                 @RequestParam(required = false) String orderDate,
                                 HttpServletRequest request,
                                 Model model){

        request.getSession().setAttribute("clientAddress",clientAddress);
        request.getSession().setAttribute("clientIp",request.getRemoteAddr());

        String browser = request.getHeader("sec-ch-ua").split("\"")[1].replaceAll("\"","");
        request.getSession().setAttribute("browser",browser);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime orderDateTime = LocalDateTime.parse(orderDate.replace('T',' '), formatter);


        String BalloonType = (String) request.getSession().getAttribute("BalloonType");
        String BalloonSize = (String) request.getSession().getAttribute("BalloonSize");

        Order order;
        if(orderDate == null) {
            order = this.orderService.save(BalloonType, BalloonSize).get();
        }
        else {
            order = this.orderService.save(BalloonType, BalloonSize, orderDateTime).get();
        }
        model.addAttribute("order", order);

        return "confirmationInfo";
    }


}
