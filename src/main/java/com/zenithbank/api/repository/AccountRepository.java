package com.zenithbank.api.repository;

import com.zenithbank.api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Account entity.
 * Spring Data JPA handles the implementation.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    // Custom query methods can be added here if needed
}
