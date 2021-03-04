package com.mobileloan.controller;

import com.mobileloan.domain.LoanDataDto;
import com.mobileloan.service.LoanApplicationService;
import com.mobileloan.validator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/loanApplication")
public class LoanApplicationController {
        @Autowired
        private LoanApplicationService loanApplicationService;

        @RequestMapping(method = RequestMethod.POST, name = "applyForALoan", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity applyForALoan(@RequestBody LoanDataDto loanDataDto){
                try{
                        loanApplicationService.saveLoanApplication(loanDataDto);
                }catch(WrongLoanDataException| LoanApplicationDoesntMeetLoanTermsException|
                        LoanApplicationDuringInactivityHoursException e){
                        return new ResponseEntity(new ApiError(e.toString()), HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity(HttpStatus.OK);
        }

        @RequestMapping(method = RequestMethod.POST, name = "applyForALoan")
        public ResponseEntity extendALoan(@RequestParam int idLoan, int terms){
                try{
                        loanApplicationService.extendALoan(idLoan, terms);
                }catch(InvalidTermsException | LoanNotFoundException e){
                        return new ResponseEntity(new ApiError(e.toString()), HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity(HttpStatus.OK);
        }


}
