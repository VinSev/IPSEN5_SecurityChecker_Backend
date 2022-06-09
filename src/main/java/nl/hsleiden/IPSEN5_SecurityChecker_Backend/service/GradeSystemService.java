package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import java.util.HashMap;
import java.util.List;

public class GradeSystemService {

    private int calculateGrade(HashMap<String, String> scanComponents) {
        int passed = 0;
        List<String> values = (List<String>) scanComponents.values();
        for(String value: values){
            if(Integer.parseInt(value) > 5.5){
                passed+=1;
            }
        }
        return scanComponents.size()/ passed * scanComponents.size();
    }

}
