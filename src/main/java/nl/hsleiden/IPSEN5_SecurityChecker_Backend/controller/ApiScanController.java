package nl.hsleiden.IPSEN5_SecurityChecker_Backend.controller;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ApiScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ApiScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/ApiScan")
@CrossOrigin(origins = {"http://4200","*"})
public class ApiScanController {

    @Autowired
    private ApiScanDao apiScanDao;

    @GetMapping(value = "/all")
    public List<ApiScan> getAllResults() {
        return apiScanDao.getAll();
    }

    @GetMapping(value = "/{id}")
    public ApiScan getApiScan(@PathVariable final String id) {
        return apiScanDao.get(id);
    }

    @PostMapping(value = "/new")
    public ApiScan addApiScan(@RequestBody ApiScan newApiScan) {
        return apiScanDao.create(newApiScan);
    }

    @DeleteMapping("/del")
    public void deleteApiScan(@RequestBody ApiScan apiScan) {
        apiScanDao.delete(apiScan);
    }
}
