package com.mobileloan.mapper;

import com.mobileloan.domain.LoanTerms;
import com.mobileloan.domain.LoanTermsDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LoanTermsMapper {
    public LoanTermsDto mapToDto(LoanTerms loanTerms){
        return new LoanTermsDto(
                loanTerms.getId(),
                loanTerms.getMinLoanAmount(),
                loanTerms.getMaxLoanAmount(),
                loanTerms.getInterestRate(),
                loanTerms.getMinMonthsLoanDuration(),
                loanTerms.getMinMonthsLoanDuration(),
                loanTerms.getLoanTermCreationDatetime()
        );
    }

    public LoanTerms mapToLoanTerms(LoanTermsDto loanTermsDto){
        return new LoanTerms(
                loanTermsDto.getId(),
                loanTermsDto.getMinLoanAmount(),
                loanTermsDto.getMaxLoanAmount(),
                loanTermsDto.getInterestRate(),
                loanTermsDto.getMinMonthsLoanDuration(),
                loanTermsDto.getMinMonthsLoanDuration(),
                LocalDateTime.now()
        );
    }
}
