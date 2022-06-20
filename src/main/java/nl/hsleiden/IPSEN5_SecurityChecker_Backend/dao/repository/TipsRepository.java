package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Tip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipsRepository extends JpaRepository<Tip, Long> {
}
