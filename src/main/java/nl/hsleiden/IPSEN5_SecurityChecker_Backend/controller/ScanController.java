package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.certificate.CertificateScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.header.HeaderScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.seo.SeoScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.wordpress.WordPressVulnerabilityScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.scan.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/scan")
@CrossOrigin
public class ScanController {
    @Autowired
    private ScanService scanService;

    @PostMapping("/header/{website}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ScanReport headerScan(@RequestParam("website") String website,
                                 @RequestBody ScanReport scanReport) throws IOException, InterruptedException {
        return scanService.scan(website, scanReport, new HeaderScan());
    }

    @PostMapping("/certificate/{website}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ScanReport certificateScan(@RequestParam("website") String website,
                                      @RequestBody ScanReport scanReport) throws IOException, InterruptedException {
        return scanService.scan(website, scanReport, new CertificateScan());
    }

    @PostMapping("/vulnerability/{website}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ScanReport vulnerabilityScan(@RequestParam("website") String website,
                                        @RequestBody ScanReport scanReport) throws IOException, InterruptedException {
        return scanService.scan(website, scanReport, new WordPressVulnerabilityScan());
    }

    // TODO: Change the xss & injection scan to an Abstract Scan
    @PostMapping("/xss-and-injection/{website}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ScanReport xssAndInjectionScan(@RequestParam("website") String website,
                                          @RequestBody ScanReport scanReport) throws IOException, InterruptedException {
        return scanService.scan(website, scanReport, null);
    }

    @PostMapping("/seo/{website}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ScanReport seoScan(@RequestParam("website") String website,
                              @RequestBody ScanReport scanReport) throws IOException, InterruptedException {
        return scanService.scan(website, scanReport, new SeoScan());
    }
}
