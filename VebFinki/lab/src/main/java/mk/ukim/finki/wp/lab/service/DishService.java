package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Dish;

import java.util.List;

public interface DishService {
    List<Dish> listDishes();
    Dish findByDishId(String dishId);
    Dish findById(Long id);
    Dish create(String dishId, String name, String cuisine, int preparationTime, Long chefID);
    Dish update(Long id, String dishId, String name, String cuisine, int preparationTime, Long chefID);
    void delete(Long id);
    List<Dish> findAllByChef_Id(Long chefId);
}
