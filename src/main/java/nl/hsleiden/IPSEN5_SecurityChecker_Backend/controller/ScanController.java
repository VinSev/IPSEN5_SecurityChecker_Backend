package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.certificate.CertificateScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.header.HeaderScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.seo.SeoScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.wordpress.WordPressVulnerabilityScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.scan.ScanReportService;
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
    @Autowired
    private ScanReportService scanReportService;

    @PostMapping("/header/{website}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ScanReport headerScan(@PathVariable("website") String website,
                                 @RequestBody ScanReport scanReport) throws IOException, InterruptedException {
        scanReport.setResult(scanService.scan(website, scanReport, new HeaderScan()));
        return scanReport;
    }

    @PostMapping("/certificate/{website}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ScanReport certificateScan(@PathVariable("website") String website,
                                      @RequestBody ScanReport scanReport) throws IOException, InterruptedException {
        scanReport.setResult(scanService.scan(website, scanReport, new CertificateScan()));
        return scanReport;
    }

    // TODO: Werkt niet
    @PostMapping("/vulnerability/{website}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ScanReport vulnerabilityScan(@PathVariable("website") String website,
                                        @RequestBody ScanReport scanReport) throws IOException, InterruptedException {
        scanReport.setResult(scanService.scan(website, scanReport, new WordPressVulnerabilityScan()));
        return scanReport;
    }

    // TODO: Change the xss & injection scan to an Abstract Scan
    @PostMapping("/xss-and-injection/{website}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ScanReport xssAndInjectionScan(@PathVariable("website") String website,
                                          @RequestBody ScanReport scanReport) throws IOException, InterruptedException {
        scanReport.setResult(scanService.scan(website, scanReport, null));
        return scanReport;
    }

    @PostMapping("/seo/{website}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ScanReport seoScan(@PathVariable("website") String website,
                              @RequestBody ScanReport scanReport) throws IOException, InterruptedException {
        scanReport.setResult(scanService.scan(website, scanReport, new SeoScan()));
        return scanReport;
    }
}
