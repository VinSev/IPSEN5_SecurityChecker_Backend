package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.TipsDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipsService {
    @Autowired
    private TipsDao tipsDao;

    public List<Tip> getAll() {
        return this.tipsDao.getAll();
    }

    public Tip get(Long id) {
        return this.tipsDao.get(id);
    }

    public Tip create(Tip tip) {
        return this.tipsDao.create(tip);
    }

    public void update(Tip tip) {
        this.tipsDao.update(tip);
    }

    public void delete(Tip tip) {
        this.tipsDao.delete(tip);
    }
}
