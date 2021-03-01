package com.mobileloan.repository;

import com.mobileloan.domain.LoanTerms;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@SpringBootTest
public class LoanTermsRepositoryTestSuite {
    @Autowired
    private LoanTermsRepository loanTermsRepository;

    @Test
    public void savingLoanTermsTest(){
        //given
        int loanTermId = 1;
        long minLoanAmount = 100;
        long maxLoanAmount = 1000;
        double interestRate = 20;
        int minMonthsLoanDuration = 1;
        int maxMonthsLoanDuration = 4;
        LocalDateTime loanTermCreationDatetime = LocalDateTime.now();
        LoanTerms loanTerms = new LoanTerms(loanTermId, minLoanAmount, maxLoanAmount, interestRate,
                minMonthsLoanDuration, maxMonthsLoanDuration, loanTermCreationDatetime);
        long expectedLoanTermId = 1;

         //when
        loanTermsRepository.save(loanTerms);
        Iterable<LoanTerms> resultedLoanTerms = loanTermsRepository.findAll();
        List<LoanTerms> loanTermsList = new ArrayList<>();
        resultedLoanTerms.forEach(loanTermsList::add);
        //then
        Assertions.assertEquals(1, loanTermsList.size());

        loanTermsRepository.deleteAll();
    }
}
