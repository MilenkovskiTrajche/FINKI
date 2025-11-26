package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();
    public static Long id = 0L;

    @PostConstruct
    public void init(){
        chefs.add(new Chef(1L,"Pece","Pecevski","15 years of experience, best sushi chef", new ArrayList<>()));
        chefs.add(new Chef(2L,"Mile","Milevski","5 years of experience, best traditional chef", new ArrayList<>()));
        chefs.add(new Chef(3L,"Petko","Petkovski","2.5 years of experience, best burger chef", new ArrayList<>()));
        chefs.add(new Chef(4L,"Mitko","Mitkovski","best pizza chef", new ArrayList<>()));
        chefs.add(new Chef(5L,"Zoran","Zoranovski","No experience :D", new ArrayList<>()));

        dishes.add(new Dish(id++,"d1","Sushi rolled salamon", "Japanese", 30));
        dishes.add(new Dish(id++,"d2","Baked beans", "Macedonian", 60));
        dishes.add(new Dish(id++,"d3","Chicago Burger", "American", 10));
        dishes.add(new Dish(id++,"d4","Margarita", "Italian", 20));
        dishes.add(new Dish(id++,"d5","Water", "International", 1));
    }
}
