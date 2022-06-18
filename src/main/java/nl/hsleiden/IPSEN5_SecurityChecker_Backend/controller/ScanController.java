package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;


import nl.hsleiden.IPSEN5_SecurityChecker_Backend.annotation.IsAdmin;
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

    @IsAdmin
    @PostMapping({"/add"})
    @ResponseBody
    public Scan addScann(@RequestBody Scan scan) {
        return scanDao.create(scan);
    }

    @IsAdmin
    @GetMapping(value = "/all")
    public List<Scan> getAllScans() {
        return scanDao.getAll();
    }

    @IsAdmin
    @GetMapping(value = "/{id}")
    public Scan getScan(@PathVariable final String id) {
        return scanDao.get(id);
    }

    @IsAdmin
    @PutMapping(value = "/new")
    public Scan addScan(@RequestBody Scan newScan) {
        return scanDao.create(newScan);
    }

    @IsAdmin
    @DeleteMapping("/del")
    public void deleteScan(@RequestBody Scan scan) {
        scanDao.delete(scan);
    }


}
