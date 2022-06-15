package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;


import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/scan")
@CrossOrigin(origins = {"http://4200","*"})
public class ScanController {

    @Autowired
    public final ScanDao scanDao;


    public ScanController(ScanDao scanDao) {
        this.scanDao = scanDao;
    }

    @PostMapping({"/add"})
    @ResponseBody
    public Scan addScann(@RequestBody Scan scan) {
        return scanDao.create(scan);
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
    public Scan addScan(@RequestBody Scan newScan) {
        System.out.println("Scan Added");
        return scanDao.create(newScan);
    }

    @DeleteMapping("/del")
    public void deleteScan(@RequestBody Scan scan) {
        scanDao.delete(scan);
    }


}
