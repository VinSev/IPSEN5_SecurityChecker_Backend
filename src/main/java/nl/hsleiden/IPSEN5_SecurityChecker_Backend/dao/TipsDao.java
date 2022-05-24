package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Tip;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipsDao implements Dao<Tip, Integer> {

    @Override
    public List<Tip> getAll() {
        return null;
    }

    @Override
    public Tip get(Integer id) {
        return null;
    }

    @Override
    public Tip create(Tip tip) {
        return null;
    }

    @Override
    public void update(Tip tip) {

    }

    @Override
    public void delete(Tip tip) {

    }
}
