
package com.campus.resource.repository;

import com.campus.resource.entity.Loan;
import com.campus.resource.entity.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByStatus(LoanStatus status);

    List<Loan> findByStudentStudentId(Long studentId);

    List<Loan> findByResourceResourceId(Long resourceId);
}