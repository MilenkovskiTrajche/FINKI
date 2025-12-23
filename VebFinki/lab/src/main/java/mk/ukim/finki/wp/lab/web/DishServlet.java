package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "DishServlet", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;
    private final ChefService chefService;
    private final DishService dishService;


    public DishServlet(SpringTemplateEngine templateEngine, ChefService chefService, DishService dishService) {
        this.templateEngine = templateEngine;
        this.chefService = chefService;
        this.dishService = dishService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=UTF-8");

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String chefIdParam = req.getParameter("chefId");
        if (chefIdParam != null) {
            try {
                Long chefId = Long.parseLong(chefIdParam);
                Chef chef = chefService.findById(chefId);

        context.setVariable("dishes", dishService.listDishes());
        context.setVariable("chefId", chef.getId());
        context.setVariable("chefName", chef.getFirstName());
        context.setVariable("chefSurname", chef.getLastName());

        templateEngine.process("dishesList.html", context, resp.getWriter());
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid chefId");
            }
        } else {
            resp.sendRedirect("/dishes");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String chefId = req.getParameter("chefId");
        System.out.println("chiD " + chefId);
        resp.sendRedirect("/dish?chefId=" + chefId);
    }

}
