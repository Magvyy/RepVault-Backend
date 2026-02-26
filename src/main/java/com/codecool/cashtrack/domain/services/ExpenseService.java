package com.codecool.cashtrack.domain.services;

import com.codecool.cashtrack.application.DTOs.incoming.ExpenseRequestDTO;
import com.codecool.cashtrack.application.DTOs.outgoing.ExpenseResponseDTO;
import com.codecool.cashtrack.application.DTOs.outgoing.ResponseDTO;
import com.codecool.cashtrack.domain.utils.ExpenseUtil;
import com.codecool.cashtrack.domain.entities.Expense;
import com.codecool.cashtrack.infrastructure.repositories.ExpenseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    private final ExpenseUtil expenseUtil;
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseUtil expenseUtil, ExpenseRepository expenseRepository) {
        this.expenseUtil = expenseUtil;
        this.expenseRepository = expenseRepository;
    }

    public ResponseEntity<ExpenseResponseDTO> createExpense(ExpenseRequestDTO expenseRequestDTO) {
        Expense expense = expenseUtil.convertToEntity(expenseRequestDTO);
        expenseRepository.save(expense);
        return ResponseEntity.ok(new ExpenseResponseDTO(expense));
    }

    public ResponseEntity<ExpenseResponseDTO> getExpense(Long id) {
        Expense expense = expenseUtil.throwIfExpenseDoesNotExist(id);
        return ResponseEntity.ok(new ExpenseResponseDTO(expense));
    }

    public ResponseEntity<ExpenseResponseDTO> updateExpense(Long id, ExpenseRequestDTO expenseRequestDTO) {
        Expense expense = expenseUtil.throwIfExpenseDoesNotExist(id);
        if (expenseUtil.authenticatedUserOwnsExpense(id)) throw new AccessDeniedException("Unauthorized expense update");
        expenseUtil.validateAndSet(expense, expenseRequestDTO);
        expense = expenseRepository.save(expense);
        return ResponseEntity.ok(new ExpenseResponseDTO(expense));
    }

    public ResponseEntity<ResponseDTO> deleteExpense(Long id) {
        Expense expense = expenseUtil.throwIfExpenseDoesNotExist(id);
        if (expenseUtil.authenticatedUserOwnsExpense(id)) throw new AccessDeniedException("Unauthorized expense update");
        expenseRepository.delete(expense);
        return ResponseEntity.ok(new ResponseDTO("Expense deletion successful"));
    }
}
