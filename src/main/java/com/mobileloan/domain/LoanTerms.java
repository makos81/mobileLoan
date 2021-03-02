package com.mobileloan.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LOAN_TERMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(
        name = "LoanTerms.getLatestTerm",
        query = "SELECT * FROM LOAN_TERMS ORDER BY loan_term_creation_datetime desc limit 1",
        resultClass = LoanTerms.class
)
public class LoanTerms {
    @Id
    @GeneratedValue
    @NotNull
    private long id;
    @Column(name = "max_loan_amount")
    private long minLoanAmount;
    @Column(name = "interest_rate")
    private long maxLoanAmount;
    @Column(name = "min_loan_amount")
    private double interestRate;
    @Column(name = "min_months_loan_duration")
    private int minMonthsLoanDuration;
    @Column(name = "max_months_loan_duration")
    private int maxMonthsLoanDuration;
    @Column(name = "loan_term_creation_datetime")
    private LocalDateTime loanTermCreationDatetime;
}
