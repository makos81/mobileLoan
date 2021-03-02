package com.mobileloan.controller;

import com.mobileloan.domain.LoanTerms;
import com.mobileloan.domain.LoanTermsDto;
import com.mobileloan.mapper.LoanTermsMapper;
import com.mobileloan.repository.LoanTermsRepository;
import com.mobileloan.service.LoanTermsService;
import com.mobileloan.validator.LoanTermsValidator;
import org.hibernate.annotations.common.reflection.XMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/loanTerms")
public class LoanTermsController {
    @Autowired
    private LoanTermsValidator loanTermsValidator;
    @Autowired
    private LoanTermsMapper loanTermsMapper;
    @Autowired
    private LoanTermsRepository loanTermsRepository;
    @Autowired
    private LoanTermsService loanTermsService;

    @RequestMapping(method = RequestMethod.POST, value = "defineLoanTerms", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createLoanTerms(@RequestBody LoanTermsDto loanTermsDto){
        if(loanTermsValidator.areLoanTermsValid(loanTermsDto)){
            loanTermsService.saveLoanTerms(loanTermsDto);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(new ApiError("Bad request"), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateLoanTerms")
    public LoanTermsDto updateLoanTerms(@RequestParam LoanTermsDto loanTermsDto){
            LoanTerms updatedLoanTerms = loanTermsService.saveLoanTerms(loanTermsDto);
            return loanTermsMapper.mapToDto(updatedLoanTerms);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getLatestLoanTerms")
    public LoanTermsDto getLatestLoanTerms(){
        LoanTerms updatedLoanTerms = loanTermsService.getLatestTerm();
        if(updatedLoanTerms!=null){
            return loanTermsMapper.mapToDto(loanTermsService.getLatestTerm());
        }else{
            return null;
        }

    }
}
