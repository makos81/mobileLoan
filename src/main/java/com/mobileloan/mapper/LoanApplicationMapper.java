package com.mobileloan.mapper;

import com.mobileloan.domain.LoanData;
import com.mobileloan.domain.LoanDataDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LoanApplicationMapper {
    public LoanData mapToLoanData(LoanDataDto loanDataDto){
        return new LoanData(
                loanDataDto.getId(),
                loanDataDto.getLoanTerm(),
                loanDataDto.getLoanAmount(),
                LocalDateTime.now()
        );
    }

    public LoanDataDto mapToDto(LoanData loanData){
        return new LoanDataDto(
                loanData.getId(),
                loanData.getLoanTerm(),
                loanData.getLoanAmount(),
                loanData.getLoanApplicationDateTime(),
                loanData.isGrantedLoan(),
                loanData.getApplicationForALoanRejectionReason()
        );
    }
}
