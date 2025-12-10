package mk.ukim.finki.wp.lab.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping
    public String getDishesPage(@RequestParam(required = false) String error,
                                Model model,
                                @RequestParam(required = false) Long chefId) {
        model.addAttribute("dishes", dishService.listDishes());
        model.addAttribute("chefs", chefService.listChefs());
        model.addAttribute("error", error);

        if(chefId != null){
            Chef chef = chefService.findById(chefId);
            model.addAttribute("filteredChef", chef);
            model.addAttribute("chefDishes", dishService.findAllByChef_Id(chefId));
        }
        return "listDishes";
    }

    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam Long chefId) {
        dishService.create(dishId, name, cuisine, preparationTime, chefId);
        return "redirect:/dishes";
    }

    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam Long chefId) {
        dishService.update(id, dishId, name, cuisine, preparationTime, chefId);
        return "redirect:/dishes";
    }

    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }


    @GetMapping("/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("chefs", chefService.listChefs());
        return "dish-form";
    }

    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        Dish dish = dishService.findById(id);
        if (dish == null) {
            return "redirect:/dishes?error=DishNotFound";
        }
        model.addAttribute("dish", dish);
        model.addAttribute("chefs", chefService.listChefs());

        return "dish-form";
    }

    @GetMapping("/add-to-chef")
    public String addDishToChef(@RequestParam Long chefId,
                                @RequestParam String dishId,
                                Model model) {
        chefService.addDishToChef(chefId, dishId);
        Chef chef = chefService.findById(chefId);
        model.addAttribute("chef", chef);
        model.addAttribute("dishes", chef.getDishes());

        return "chefDetails";
    }

    @PostMapping("/add-to-chef")
    public String addDishToChefPost(@RequestParam Long chefId,
                                    @RequestParam String dishId) {
        chefService.addDishToChef(chefId, dishId);
        return "redirect:/dishes/add-to-chef?chefId=" + chefId + "n&dishId=" + dishId;
    }

    @GetMapping("/chef/{chefId}")
    public String getDishesByChef(@PathVariable Long chefId, Model model) {
        model.addAttribute("chef", chefService.findById(chefId));
        model.addAttribute("dishes", dishService.findAllByChef_Id(chefId));
        return "dishes_by_chef";
    }
}
