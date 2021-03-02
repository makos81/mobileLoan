package com.mobileloan.service;

import com.mobileloan.domain.LoanTerms;
import com.mobileloan.domain.LoanTermsDto;
import com.mobileloan.mapper.LoanTermsMapper;
import com.mobileloan.repository.LoanTermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LoanTermsService {
    @Autowired
    private LoanTermsRepository loanTermsRepository;
    @Autowired
    private LoanTermsMapper loanTermsMapper;

    public LoanTerms saveLoanTerms(LoanTermsDto loanTermsDto){
        LoanTerms loanTerms = loanTermsMapper.mapToLoanTerms(loanTermsDto);
        LoanTerms savedLoanTerms = loanTermsRepository.save(loanTerms);
        return savedLoanTerms;
    }

    public LoanTerms getLatestTerm(){
        return loanTermsRepository.getLatestTerm();
    }
}
