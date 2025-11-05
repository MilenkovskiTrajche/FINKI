package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public  ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElse(null);
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef = chefRepository.findById(chefId).orElse(null);
        Dish dish = dishRepository.findByDishId(dishId);
        List<Dish> dishes = new ArrayList<>();

        if (chef != null) {
            List<Dish> chefDishes = chef.getDishes();
            dishes.addAll(chefDishes);
        }
        if (dish != null && !dishes.contains(dish)) {
            dishes.add(dish);
        }
        if (chef != null) {
            chef.setDishes(dishes);
        }
        return chefRepository.save(chef);
    }
}
