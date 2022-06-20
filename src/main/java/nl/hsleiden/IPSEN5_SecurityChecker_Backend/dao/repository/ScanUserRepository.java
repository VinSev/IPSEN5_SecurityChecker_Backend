package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScanUserRepository extends JpaRepository<ScanUser, Long> {
}
