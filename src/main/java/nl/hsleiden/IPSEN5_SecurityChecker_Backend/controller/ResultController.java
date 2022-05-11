package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;


import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ResultDAO;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Result;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.User;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/result")
@CrossOrigin(origins = {"http://4200","*"})
public class ResultController {

    @Autowired
    public final ResultDAO resultDAO;

    @Autowired
    public final ResultService resultService;

    public ResultController( ResultDAO resultDAO, ResultService resultService) {
        this.resultService = resultService;
        this.resultDAO = resultDAO;
    }

    @PostMapping({"/start"})
    @ResponseBody
    public Result startScan(@RequestBody User user, String url) {
        return resultService.createNew(user, url);
    }

    @GetMapping(value = "/all")
    public List<Result> getAllResults() {
        return resultDAO.getAll();
    }

    @GetMapping(value = "/{id}")
    public Result getResult(@PathVariable final String id) {
        return resultDAO.get(id);
    }

    @PutMapping(value = "/new")
    public Result addResult(@RequestBody Result newResult) {
        return resultDAO.create(newResult);
    }

    @DeleteMapping("/del")
    public void deleteResult(@RequestBody Result result) {
        resultDAO.delete(result);
    }


}
