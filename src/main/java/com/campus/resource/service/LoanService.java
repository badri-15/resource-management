
package com.campus.resource.service;

import com.campus.resource.entity.Loan;
import com.campus.resource.entity.LoanStatus;
import com.campus.resource.entity.Resource;
import com.campus.resource.entity.Student;
import com.campus.resource.repository.LoanRepository;
import com.campus.resource.repository.ResourceRepository;
import com.campus.resource.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final StudentRepository studentRepository;
    private final ResourceRepository resourceRepository;

    public LoanService(
            LoanRepository loanRepository,
            StudentRepository studentRepository,
            ResourceRepository resourceRepository) {

        this.loanRepository = loanRepository;
        this.studentRepository = studentRepository;
        this.resourceRepository = resourceRepository;
    }

    @Transactional
    public Loan issueResource(
            Long studentId,
            Long resourceId,
            LocalDate dueDate) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        if (resource.getAvailableCopies() <= 0) {
            throw new RuntimeException("Resource is not available");
        }

        resource.setAvailableCopies(
                resource.getAvailableCopies() - 1
        );

        resourceRepository.save(resource);

        Loan loan = new Loan();
        loan.setStudent(student);
        loan.setResource(resource);
        loan.setIssueDate(LocalDate.now());
        loan.setDueDate(dueDate);
        loan.setStatus(LoanStatus.ISSUED);

        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    @Transactional
    public Loan returnResource(Long loanId) {

        Loan loan = getLoanById(loanId);

        if (loan.getStatus() == LoanStatus.RETURNED) {
            throw new RuntimeException("Resource already returned");
        }

        Resource resource = loan.getResource();

        resource.setAvailableCopies(
                resource.getAvailableCopies() + 1
        );

        resourceRepository.save(resource);

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);

        return loanRepository.save(loan);
    }

    public List<Loan> getLoansByStatus(LoanStatus status) {
        return loanRepository.findByStatus(status);
    }

    public List<Loan> getLoansByStudent(Long studentId) {
        return loanRepository.findByStudentStudentId(studentId);
    }

    public List<Loan> getLoansByResource(Long resourceId) {
        return loanRepository.findByResourceResourceId(resourceId);
    }

    @Transactional
    public int updateOverdueLoans() {

        List<Loan> issuedLoans =
                loanRepository.findByStatus(LoanStatus.ISSUED);

        int updatedCount = 0;

        for (Loan loan : issuedLoans) {

            if (loan.getDueDate().isBefore(LocalDate.now())) {
                loan.setStatus(LoanStatus.OVERDUE);
                loanRepository.save(loan);
                updatedCount++;
            }
        }

        return updatedCount;
    }
}