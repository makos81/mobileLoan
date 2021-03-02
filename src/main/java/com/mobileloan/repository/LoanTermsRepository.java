package com.mobileloan.repository;

import com.mobileloan.domain.LoanTerms;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanTermsRepository extends CrudRepository<LoanTerms, Long> {
    @Query
    public LoanTerms getLatestTerm();
}
