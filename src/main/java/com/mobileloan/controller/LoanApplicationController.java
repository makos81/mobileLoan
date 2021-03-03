package com.mobileloan.controller;

import com.mobileloan.domain.LoanDataDto;
import com.mobileloan.service.LoanApplicationService;
import com.mobileloan.service.LoanTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/loanApplication")
public class LoanApplicationController {
        @Autowired
        private LoanApplicationService loanApplicationService;

        @RequestMapping(method = RequestMethod.POST, name = "applyForALoan", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity applyForALoan(@RequestBody LoanDataDto loanDataDto){
                if(loanApplicationService.saveLoanApplication(loanDataDto)){
                        return new ResponseEntity(HttpStatus.OK);
                }else{
                        return new ResponseEntity(new ApiError("Bad loan data"), HttpStatus.BAD_REQUEST);
                }
        }

}
