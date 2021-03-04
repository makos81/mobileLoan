package com.mobileloan.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private LocalDateTime loanStartDateTime;
    private LocalDateTime loanDueDate;
}
