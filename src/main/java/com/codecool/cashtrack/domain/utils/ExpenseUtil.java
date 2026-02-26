package com.codecool.cashtrack.domain.utils;

import com.codecool.cashtrack.application.DTOs.incoming.ExpenseRequestDTO;
import com.codecool.cashtrack.domain.entities.Expense;
import com.codecool.cashtrack.domain.entities.User;
import com.codecool.cashtrack.domain.exceptions.UserException;
import com.codecool.cashtrack.infrastructure.repositories.ExpenseRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ExpenseUtil {
    private final SecurityUtil securityUtil;
    private final ExpenseRepository expenseRepository;

    public ExpenseUtil(SecurityUtil securityUtil, ExpenseRepository expenseRepository) {
        this.securityUtil = securityUtil;
        this.expenseRepository = expenseRepository;
    }

    public Expense convertToEntity(ExpenseRequestDTO expenseRequestDTO) {
        User authenticatedUser = securityUtil.getAuthenticatedUser();
        return new Expense(expenseRequestDTO, authenticatedUser);
    }

    public Expense throwIfExpenseDoesNotExist(Long id) {
        Optional<Expense> oExpense = expenseRepository.findById(id);
        if (oExpense.isEmpty()) throw new UserException("Expense does not exist");
        return oExpense.get();
    }

    public boolean authenticatedUserOwnsExpense(Long id) {
        User authenticatedUser = securityUtil.getAuthenticatedUser();
        List<Expense> expenses = expenseRepository.findByUserId(authenticatedUser.getId());
        return expenses.stream().anyMatch(expense -> expense.getId().equals(id));
    }

    public void validateAndSet(Expense expense, ExpenseRequestDTO expenseRequestDTO) {
        BigDecimal cost = expenseRequestDTO.getCost();
        String description = expenseRequestDTO.getDescription();
        LocalDateTime timestamp = expenseRequestDTO.getTimestamp();
        if (isValidCost(cost)) expense.setCost(cost);
        if (isValidDescription(description)) expense.setDescription(description);
        if (isValidTimestamp(timestamp)) expense.setTimestamp(timestamp);
    }

    private boolean isValidCost(BigDecimal cost) {
        return cost.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean isValidDescription(String description) {
        return !description.trim().isEmpty();
    }

    private boolean isValidTimestamp(LocalDateTime timestamp) {
        return true;
    }
}
