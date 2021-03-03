package com.mobileloan.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDataDto {
    private int id;
    private int loanTerm;
    private int loanAmount;
    private LocalDateTime loanApplicationDateTime;
    private boolean isGrantedLoan;
    private String applicationForALoanRejectionReason;
}
