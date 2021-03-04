package com.mobileloan.validator;

import com.mobileloan.domain.LoanData;
import com.mobileloan.domain.LoanTerms;
import com.mobileloan.service.LoanTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

@Component
public class LoanApplicationValidator {
    @Autowired
    private LoanTermsService loanTermsService;
    private final int INACTIVITY_START_TIME = 0;
    private final int INACTIVITY_END_TIME = 6;

    private boolean validateTermLength(int term){
        return term > 1;
    }

    private boolean validateLoanAmount(int amount){
        return amount > 0;
    }

    private boolean validateLoanApplication(LoanData loanData){
        return validateTermLength(loanData.getLoanTerm()) && validateLoanAmount(loanData.getLoanAmount());
    }

    private boolean validateTermBetweenMinAndMaxPossibleTerm(int term){
        LoanTerms loanTerms = getLatestLoanTerms();
        return term>=loanTerms.getMinLoanAmount() && term<=loanTerms.getMaxLoanAmount();
    }

    private LoanTerms getLatestLoanTerms(){
        return loanTermsService.getLatestTerm();
    }

    private boolean validateAmountBetweenMinAndMaxPossibleAmount(int amount){
        LoanTerms loanTerms = getLatestLoanTerms();
        return amount>=loanTerms.getMinLoanAmount() && amount<=loanTerms.getMaxLoanAmount();
    }

    private boolean validateLoanApplicationVersusTerms(LoanData loanData){
        return validateAmountBetweenMinAndMaxPossibleAmount(loanData.getLoanAmount())
                && validateTermBetweenMinAndMaxPossibleTerm(loanData.getLoanTerm());
    }

    private boolean validateLoanApplicationTimeVersusInactivityTime(LocalDateTime localDateTime){
        return localDateTime.getHour() <= this.INACTIVITY_END_TIME && localDateTime.getHour() >= this.INACTIVITY_START_TIME;
    }

    public boolean validateLoan(LoanData loanData) throws LoanApplicationDoesntMeetLoanTermsException,
            WrongLoanDataException, LoanApplicationDuringInactivityHoursException{
        if(!validateLoanApplication(loanData)){
            throw new WrongLoanDataException();
        }
        if(!validateLoanApplicationVersusTerms(loanData)){
            throw new LoanApplicationDoesntMeetLoanTermsException();
        }
        if(!validateLoanApplicationTimeVersusInactivityTime(LocalDateTime.now())){
            throw new LoanApplicationDuringInactivityHoursException();
        }
        return true;
    }
}
