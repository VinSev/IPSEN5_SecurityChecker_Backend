package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipsService {
    @Autowired
    private TipsService tipsService;

    public List<Tip> getAll() {
        return this.tipsService.getAll();
    }

    public Tip get(Integer id) {
        return this.tipsService.get(id);
    }

    public Tip create(Tip tip) {
        return this.tipsService.create(tip);
    }

    public void update(Tip tip) {
        this.tipsService.update(tip);
    }

    public void delete(Tip tip) {
        this.tipsService.delete(tip);
    }
}
