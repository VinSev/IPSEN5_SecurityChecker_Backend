package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;


import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ResultDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanResult;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.PdfService;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/result")
@CrossOrigin(origins = {"http://4200","*"})
public class ResultController {

    @Autowired
    public final ResultDao resultDAO;
    @Autowired
    public final ResultService resultService;

    @Autowired
    public final PdfService pdfService;

    public ResultController(ResultDao resultDAO, ResultService resultService, PdfService pdfService) {
        this.resultService = resultService;
        this.resultDAO = resultDAO;
        this.pdfService = pdfService;
    }

    @PostMapping({"/makeResult"})
    @ResponseBody
    public ScanResult startScan(@RequestBody Scan scan) {
         ScanResult  scanResult = resultService.createNewResult(scan);
         scan.setScanResult(scanResult);
        return scanResult;

    }

    @GetMapping(value = "/all")
    public List<ScanResult> getAllResults() {
        return resultDAO.getAll();
    }

    @GetMapping(value = "/{id}")
    public ScanResult getResult(@PathVariable final Long id) {
        return resultDAO.get(id);
    }

    @PutMapping(value = "/new")
    public ScanResult addResult(@RequestBody ScanResult newScanResult) {
        return resultDAO.create(newScanResult);
    }

    @DeleteMapping("/del")
    public void deleteResult(@RequestBody ScanResult scanResult) {
        resultDAO.delete(scanResult);
    }


}
