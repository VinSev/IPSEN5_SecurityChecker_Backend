package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
