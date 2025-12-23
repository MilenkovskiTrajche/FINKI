package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "ChefDetailsServlet", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final ChefService chefService;

    public ChefDetailsServlet(SpringTemplateEngine springTemplateEngine, ChefService chefService) {
        this.springTemplateEngine = springTemplateEngine;
        this.chefService = chefService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=UTF-8");

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        Long chefId = Long.parseLong(req.getParameter("chefId"));
        String dishId  = req.getParameter("dishId");

        Chef chefup = chefService.addDishToChef(chefId, dishId);

        context.setVariable("chef", chefup);
        context.setVariable("dishes", chefup.getDishes());

        springTemplateEngine.process("chefDetails.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String chefId = req.getParameter("chefId");
        String dishhId = req.getParameter("dishId");

        resp.sendRedirect("/chefDetails?chefId=" + chefId + "&dishId=" + dishhId);
    }
}
