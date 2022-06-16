package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.annotation.IsAdmin;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Tip;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.TipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tips")
@CrossOrigin
public class TipsController {
    @Autowired
    private TipsService tipsService;

    @IsAdmin
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Tip> getAll() {
        return this.tipsService.getAll();
    }

    @IsAdmin
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Tip get(@PathVariable("id") Integer id) {
        return this.tipsService.get(id);
    }

    @IsAdmin
    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Tip post(@RequestBody Tip tip) {
        return this.tipsService.create(tip);
    }

    @IsAdmin
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void put(@RequestBody Tip tip) {
        this.tipsService.update(tip);
    }

    @IsAdmin
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@RequestBody Tip tip) {
        this.tipsService.delete(tip);
    }
}
