package com.mobileloan.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Setter
public class LoanTermsDto {
    private long id;
    private long minLoanAmount;
    private long maxLoanAmount;
    private double interestRate;
    private int minMonthsLoanDuration;
    private int maxMonthsLoanDuration;
    private LocalDateTime loanTermCreationDatetime;
}
