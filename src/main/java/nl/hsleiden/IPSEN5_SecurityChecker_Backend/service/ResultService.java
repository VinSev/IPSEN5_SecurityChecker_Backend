package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ResultDAO;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Result;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ResultService {
    @Autowired
    private ResultDAO resultDAO;

    public Result create(Result result) {
        return resultDAO.create(result);
    }

    public Result createNew(User user, String url) {
        Result newResult = new Result( LocalDateTime.now().toString(),url, 0, false, user);
        return resultDAO.create(newResult);
    }
}
