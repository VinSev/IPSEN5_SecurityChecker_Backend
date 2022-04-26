package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
