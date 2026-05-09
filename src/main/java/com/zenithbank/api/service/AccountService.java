package com.zenithbank.api.service;

import com.zenithbank.api.dto.AccountDTO;
import com.zenithbank.api.model.Account;
import com.zenithbank.api.repository.AccountRepository;
import com.zenithbank.api.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for handling Banking Account business logic.
 * Ensures data integrity and converts between Entities and DTOs.
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    /**
     * Retrieve all accounts from Mainframe DB2.
     */
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a single account by ID.
     */
    public AccountDTO getAccountById(String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + id));
        return convertToDTO(account);
    }

    /**
     * Create a new account in DB2.
     */
    @Transactional
    public AccountDTO createAccount(AccountDTO dto) {
        Account account = convertToEntity(dto);
        account.setLastUpdatedTimestamp(LocalDateTime.now());
        if (account.getStatus() == null) account.setStatus("A");
        
        Account savedAccount = accountRepository.save(account);
        return convertToDTO(savedAccount);
    }

    /**
     * Update an existing account.
     */
    @Transactional
    public AccountDTO updateAccount(String id, AccountDTO dto) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + id));
        
        existingAccount.setCustomerName(dto.getCustomerName());
        existingAccount.setAccountType(dto.getAccountType());
        existingAccount.setBalance(dto.getBalance());
        existingAccount.setStatus(dto.getStatus());
        existingAccount.setLastUpdatedTimestamp(LocalDateTime.now());
        
        return convertToDTO(accountRepository.save(existingAccount));
    }

    /**
     * Delete an account.
     */
    @Transactional
    public void deleteAccount(String id) {
        if (!accountRepository.existsById(id)) {
            throw new ResourceNotFoundException("Account not found with ID: " + id);
        }
        accountRepository.deleteById(id);
    }

    // --- Helper Methods ---

    private AccountDTO convertToDTO(Account entity) {
        AccountDTO dto = new AccountDTO();
        dto.setAccountId(entity.getAccountId());
        dto.setCustomerName(entity.getCustomerName());
        dto.setAccountType(entity.getAccountType());
        dto.setBalance(entity.getBalance());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    private Account convertToEntity(AccountDTO dto) {
        return Account.builder()
                .accountId(dto.getAccountId())
                .customerName(dto.getCustomerName())
                .accountType(dto.getAccountType())
                .balance(dto.getBalance())
                .status(dto.getStatus())
                .build();
    }
}
