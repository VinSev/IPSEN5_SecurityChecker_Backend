package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScanReportRepository extends JpaRepository<ScanReport, Long> {
}
