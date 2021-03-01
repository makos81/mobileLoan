package com.mobileloan.validator;

import com.mobileloan.domain.LoanTerms;
import com.mobileloan.domain.LoanTermsDto;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class LoanTermsValidator {
    private boolean isMaxLoanAmountValid(long maxLoanAmount){
        return maxLoanAmount > 0;
    }

    private boolean isMinLoanAmountValid(long minLoanAmount){
        return minLoanAmount > 0;
    }

    private boolean isMinLoanAmountSmallerThanMaxAmount(long minLoanAmount, long maxLoanAmount){
        return minLoanAmount < maxLoanAmount;
    }

    private boolean interestRateValidPercent(double interestRate){
        return interestRate < 100 && interestRate > 0;
    }

    private boolean isMaxMonthsLoanDurationValid(int maxMonthsLoanDuration){
        return maxMonthsLoanDuration > 0;
    }

    private boolean isMinMonthsLoanDurationValid(int minMonthsLoanDuration){
        return minMonthsLoanDuration > 0;
    }

    private boolean isMinLoanMonthsLoanDurationSmallerThanMaxAmount(int minMonthsLoanDuration, int maxMonthsLoanDuration){
        return minMonthsLoanDuration < maxMonthsLoanDuration;
    }

    public boolean areLoanTermsValid(LoanTermsDto loanTermsDto){
        return isMaxLoanAmountValid(loanTermsDto.getMaxLoanAmount())&&
                isMinLoanAmountValid(loanTermsDto.getMinLoanAmount()) &&
                isMinLoanAmountSmallerThanMaxAmount(loanTermsDto.getMinLoanAmount(), loanTermsDto.getMaxLoanAmount()) &&
                interestRateValidPercent(loanTermsDto.getInterestRate()) &&
                isMaxMonthsLoanDurationValid(loanTermsDto.getMaxMonthsLoanDuration()) &&
                isMinMonthsLoanDurationValid(loanTermsDto.getMinMonthsLoanDuration()) &&
                isMinLoanMonthsLoanDurationSmallerThanMaxAmount(loanTermsDto.getMinMonthsLoanDuration(),
                        loanTermsDto.getMaxMonthsLoanDuration());
    }

}
