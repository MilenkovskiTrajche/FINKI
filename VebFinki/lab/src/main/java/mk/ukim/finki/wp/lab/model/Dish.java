package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    private Long id;
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;
    private boolean like;

    public Dish(Long id, String dishId, String name, String cuisine, int preparationTime) {
        this.id = id;
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
        like = false;
    }
    public Dish(String dishId, String name, String cuisine, int preparationTime) {
        this.id = Counter.id;
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
        Counter.id++;
    }

}
