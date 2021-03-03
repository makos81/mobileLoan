package com.mobileloan.repository;

import com.mobileloan.domain.LoanData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanApplicationRepository extends CrudRepository<LoanData, Integer> {
}
