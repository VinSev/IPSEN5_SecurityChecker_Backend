package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ApiScan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubScanRepository extends JpaRepository<ApiScan, String> {
}
