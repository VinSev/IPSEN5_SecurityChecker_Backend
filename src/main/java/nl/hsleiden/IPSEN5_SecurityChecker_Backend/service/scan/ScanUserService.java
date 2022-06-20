package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.scan;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ScanUserDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScanUserService {
    @Autowired
    private ScanUserDao scanUserDao;

    public List<ScanUser> getAll() {
        return this.scanUserDao.getAll();
    }

    public ScanUser get(Long id) {
        return this.scanUserDao.get(id);
    }

    public ScanUser create(ScanUser scanUser) {
        return this.scanUserDao.create(scanUser);
    }

    public void update(ScanUser scanUser) {
        this.scanUserDao.update(scanUser);
    }

    public void delete(ScanUser scanUser) {
        this.scanUserDao.delete(scanUser);
    }
}
