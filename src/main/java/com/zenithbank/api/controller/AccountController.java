package com.zenithbank.api.controller;

import com.zenithbank.api.dto.AccountDTO;
import com.zenithbank.api.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Banking Account operations.
 * Provides endpoints for CRUD operations on Mainframe DB2.
 */
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Tag(name = "Accounts", description = "Mainframe-backed Banking Account API")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    @Operation(summary = "Get all accounts", description = "Retrieves a list of all bank accounts from Mainframe DB2")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by ID", description = "Retrieves specific account details from DB2")
    @ApiResponse(responseCode = "200", description = "Account found")
    @ApiResponse(responseCode = "404", description = "Account not found")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable String id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping
    @Operation(summary = "Create account", description = "Creates a new account entry in Mainframe DB2")
    @ApiResponse(responseCode = "201", description = "Account created successfully")
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO dto) {
        return new ResponseEntity<>(accountService.createAccount(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update account", description = "Updates an existing account in DB2")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable String id, @Valid @RequestBody AccountDTO dto) {
        return ResponseEntity.ok(accountService.updateAccount(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete account", description = "Removes an account from DB2")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
    }
}
