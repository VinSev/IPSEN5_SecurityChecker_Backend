package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ResultDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResultService {
    @Autowired
    private ResultDao resultDAO;

    public Result create(Result result) {
        return resultDAO.create(result);
    }

    public Result createNew(String url) {
        Result newResult = new Result( LocalDateTime.now().toString(),url, 0, false);
        return resultDAO.create(newResult);
    }
}
