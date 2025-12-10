package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.jpa.ChefRepository;
import mk.ukim.finki.wp.lab.repository.jpa.DishRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public DataHolder(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @PostConstruct
    public void init() {
        if (chefRepository.count() == 0) {
            chefs.add(new Chef(null,"Pece", "Pecevski", "15 years of experience, best sushi chef", new ArrayList<>()));
            chefs.add(new Chef(null,"Mile", "Milevski", "5 years of experience, best traditional chef", new ArrayList<>()));
            chefs.add(new Chef(null,"Petko", "Petkovski", "2.5 years of experience, best burger chef", new ArrayList<>()));
            chefs.add(new Chef(null,"Mitko", "Mitkovski", "best pizza chef", new ArrayList<>()));
            chefs.add(new Chef(null,"Zoran", "Zoranovski", "No experience :D", new ArrayList<>()));

            chefRepository.saveAll(chefs);
        }

        if (dishRepository.count() == 0) {

            List<Chef> chefsDb = chefRepository.findAll();

            dishes.add(new Dish(null,"d1", "Sushi rolled salmon", "Japanese", chefsDb.get(0), 30));
            dishes.add(new Dish(null,"d2", "Baked beans", "Macedonian", chefsDb.get(1), 60));
            dishes.add(new Dish(null,"d3", "Chicago Burger", "American", chefsDb.get(2), 10));
            dishes.add(new Dish(null,"d4", "Margarita", "Italian", chefsDb.get(3), 20));
            dishes.add(new Dish(null,"d5", "Water", "International", chefsDb.get(4), 1));

            dishRepository.saveAll(dishes);
        }
    }
}

