package com.mobileloan.controller;

import com.mobileloan.domain.LoanTerms;
import com.mobileloan.domain.LoanTermsDto;
import com.mobileloan.mapper.LoanTermsMapper;
import com.mobileloan.repository.LoanTermsRepository;
import com.mobileloan.validator.LoanTermsValidator;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/loanTerms")
public class loanTermsController {
    private LoanTermsValidator loanTermsValidator;
    private LoanTermsMapper loanTermsMapper;
    private LoanTermsRepository loanTermsRepository;

    @RequestMapping(method = RequestMethod.POST, value = "defineLoanTerms")
    public void createLoanTerms(@RequestBody LoanTermsDto loanTermsDto){
        if(loanTermsValidator.areLoanTermsValid(loanTermsDto)){
            LoanTerms loanTerms = loanTermsMapper.mapToLoanTerms(loanTermsDto);
            loanTermsRepository.save(loanTerms);
        }else{
            //To do
        }
    }
}
