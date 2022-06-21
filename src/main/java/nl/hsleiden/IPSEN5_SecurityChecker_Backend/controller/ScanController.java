package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;


import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanCategory;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanUser;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.CreateScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/scan")
@CrossOrigin(origins = {"http://4200","*"})
public class ScanController {

    @Autowired
    public final ScanDao scanDao;

    @Autowired
    public final CreateScanService createScanService;

    public ScanController(ScanDao scanDao, CreateScanService createScanService) {
        this.scanDao = scanDao;
        this.createScanService = createScanService;
    }

    @PostMapping({"/startscan"})
    public Scan startScan(@RequestBody ScanUser user, String url) {
        System.out.println(user.toString() +"\n"+url);
        ScanUser testUser = new ScanUser("JAcob@mail.com", "Jacob","06123456789");
        ArrayList<ScanCategory> emptyList = new ArrayList<>();
        Scan scanTest = new Scan("www.google.com",emptyList, LocalDateTime.now().toString(),testUser); // TODO Chaneg back to User and Url
        return createScanService.createNewScan(scanTest);

    }
    @GetMapping(value = "/all")
    public List<Scan> getAllScans() {
        return scanDao.getAll();
    }

    @GetMapping(value = "/{id}")
    public Scan getScan(@PathVariable final String id) {
        return scanDao.get(id);
    }

    @PutMapping(value = "/new")
    public Scan addNewScan(@RequestBody Scan newScan) {
        return scanDao.create(newScan);
    }

    @DeleteMapping("/del")
    public void deleteScan(@RequestBody Scan scan) {
        scanDao.delete(scan);
    }


}
