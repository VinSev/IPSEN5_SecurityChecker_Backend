package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ApiScan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubScanRepository extends JpaRepository<ApiScan, String> {
}
