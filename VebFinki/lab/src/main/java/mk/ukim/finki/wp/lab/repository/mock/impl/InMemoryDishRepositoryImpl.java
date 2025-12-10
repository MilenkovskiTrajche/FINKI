package mk.ukim.finki.wp.lab.repository.mock.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.mock.InMemoryDishrepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryDishRepositoryImpl implements InMemoryDishrepository {

    @Override
    public List<Dish> findAll() {
        return DataHolder.dishes;
    }

    @Override
    public Dish findByDishId(String dishId) {
        return DataHolder.dishes.stream().filter(d -> d.getDishId().equals(dishId)).findFirst().orElse(null);
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return Optional.of(DataHolder.dishes.stream().filter(d -> d.getId().equals(id)).findFirst().orElseThrow());
    }

    @Override
    public Dish save(Dish dish) {
        Dish old = findByDishId(dish.getDishId());
        if (old != null) {
            old.setName(dish.getName());
            old.setCuisine(dish.getCuisine());
            old.setPreparationTime(dish.getPreparationTime());
            old.setDishId(dish.getDishId());
        } else {
            DataHolder.dishes.add(dish);
            old = dish;
        }
        return old;
    }

    @Override
    public void deleteById(Long id) {
        boolean removed = DataHolder.dishes.removeIf(dish -> dish.getId().equals(id));
        if (!removed) {
            System.out.println("Dish not found");
        }
    }
}
