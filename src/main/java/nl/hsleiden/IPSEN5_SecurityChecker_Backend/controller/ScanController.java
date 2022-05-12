package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/scans")
@CrossOrigin
public class ScanController {
    @Autowired
    private ScanService scanService;

    @PostMapping({"/save"})
    public Scan saveScan(@ModelAttribute Scan scan) {
        return scanService.saveScan(scan);
    }

}
