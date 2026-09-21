
package com.campus.resource.controller;

import com.campus.resource.entity.Loan;
import com.campus.resource.entity.LoanStatus;
import com.campus.resource.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/issue")
    public ResponseEntity<Loan> issueResource(
            @RequestParam Long studentId,
            @RequestParam Long resourceId,
            @RequestParam LocalDate dueDate) {

        Loan loan = loanService.issueResource(
                studentId,
                resourceId,
                dueDate
        );

        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Loan> returnResource(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.returnResource(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Loan>> getLoansByStatus(
            @PathVariable LoanStatus status) {

        return ResponseEntity.ok(
                loanService.getLoansByStatus(status)
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Loan>> getLoansByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                loanService.getLoansByStudent(studentId)
        );
    }

    @GetMapping("/resource/{resourceId}")
    public ResponseEntity<List<Loan>> getLoansByResource(
            @PathVariable Long resourceId) {

        return ResponseEntity.ok(
                loanService.getLoansByResource(resourceId)
        );
    }

    @PutMapping("/update-overdue")
    public ResponseEntity<String> updateOverdueLoans() {

        int updatedCount = loanService.updateOverdueLoans();

        return ResponseEntity.ok(
                updatedCount + " loan(s) marked as overdue"
        );
    }
}