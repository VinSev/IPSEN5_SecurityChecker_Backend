package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao;


import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository.ResultRepository;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Result;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultDao implements Dao<Result, String> {

    @Autowired
    private ResultRepository repository;

    @Override
    public List<Result> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Result get(String id) {
        return this.repository.getById(id);
    }

    @Override
    public Result create(Result result) {
        return this.repository.save(result);
    }

    @Override
    public void update(Result result) {
        this.repository.save(result);
    }

    @Override
    public void delete(Result result) {
        this.repository.delete(result);
    }

}
