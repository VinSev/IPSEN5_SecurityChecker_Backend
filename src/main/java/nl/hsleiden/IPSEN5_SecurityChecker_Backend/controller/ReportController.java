package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.annotation.IsAdmin;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.Report;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.scan.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reports")
@CrossOrigin
public class ReportController {
    @Autowired
    private ReportService reportService;

    @IsAdmin
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Report> getAll() {
        return this.reportService.getAll();
    }

    @IsAdmin
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Report get(@PathVariable("id") Long id) {
        return this.reportService.get(id);
    }
}
