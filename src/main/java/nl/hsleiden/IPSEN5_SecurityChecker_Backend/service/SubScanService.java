package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.SubScanDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.SubScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubScanService {
    @Autowired
    private SubScanDao subScanDao;

    public List<SubScan> giveAllPossibleSubScans(){
       return this.subScanDao.getAll();
    }

    public int getResultOfSubScan(SubScan subScan){
            String url = subScan.getUrl();
            int grade = 0;

            return grade;
    }
}
