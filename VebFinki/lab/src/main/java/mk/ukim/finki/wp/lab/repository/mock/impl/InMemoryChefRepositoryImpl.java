package mk.ukim.finki.wp.lab.repository.mock.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.repository.mock.InMemoryChefRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryChefRepositoryImpl implements InMemoryChefRepository {

    @Override
    public List<Chef> findAll() {
        return DataHolder.chefs;
    }

    @Override
    public Optional<Chef> findById(Long id) {
        return DataHolder.chefs.stream().filter(chef -> chef.getId().equals(id)).findFirst();
    }

    @Override
    public Chef save(Chef chef) {
        Optional<Chef> existing = findById(chef.getId());
        if (existing.isPresent()){
            int exID = DataHolder.chefs.indexOf(chef);
            DataHolder.chefs.set(exID, chef);
        } else {
            DataHolder.chefs.add(chef);
        }
        return chef;
    }
}
