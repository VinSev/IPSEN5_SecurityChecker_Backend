package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository.TipsRepository;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipsDao implements Dao<Tip, Integer> {
    @Autowired
    private TipsRepository repository;

    @Override
    public List<Tip> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Tip get(Integer id) {
        return this.repository.getById(id);
    }

    @Override
    public Tip create(Tip tip) {
        return this.repository.save(tip);
    }

    @Override
    public void update(Tip tip) {
        this.repository.save(tip);
    }

    @Override
    public void delete(Tip tip) {
        this.repository.delete(tip);
    }
}
