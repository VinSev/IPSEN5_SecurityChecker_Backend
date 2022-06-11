package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Scan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.SubScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.ApiScan;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExecuteScanService {

    private List<Object> listOFApiScan;

   public void addApiScan(ApiScan newObject){
        this.listOFApiScan.add(newObject);
   }
    public void removeApiScan(ApiScan newObject){
        this.listOFApiScan.remove(newObject);
    }


   public Map<String,Object> ExecuteAllApiScans(Scan scan){
       Map<String, Object> MapSubScan = new HashMap<>();
       int scanNumber = 0;
//       for(SubScan subScan :scan.getScanResult().getScanResults() {
//           MapSubScan.put(subScan.getName(), this.listOFApiScan.get(scanNumber));
//           scanNumber++;
           //            TODO return a HAshmap with andswer and Scan CAtegirty

//       }
       return MapSubScan;

   }


}
