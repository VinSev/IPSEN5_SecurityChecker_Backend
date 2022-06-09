package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.ScanCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScanCategoryRepository extends JpaRepository<ScanCategory, String> {
}
