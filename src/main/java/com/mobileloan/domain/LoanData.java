package com.mobileloan.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LOANS")
@Getter
@NoArgsConstructor
public class LoanData {
    @Id
    @GeneratedValue
    @NotNull
    private int id;
    @NotNull
    @Column(name = "LOAN_TERM")
    private int loanTerm;
    @NotNull
    @Column(name = "LOAN_AMOUNT")
    private int loanAmount;
    @Column(name = "LOAN_APPLICATION_DATETIME")
    private LocalDateTime loanApplicationDateTime;
    @Column(name = "IS_GRANTED_LOAN")
    private boolean isGrantedLoan;
    @Column(name = "APPLICATION_REJECTION_REASON")
    private String applicationForALoanRejectionReason;

    public LoanData(int id, int loanTerm, int loanAmount, LocalDateTime loanApplicationDateTime) {
        this.id = id;
        this.loanTerm = loanTerm;
        this.loanAmount = loanAmount;
        this.loanApplicationDateTime = loanApplicationDateTime;
    }

    public void setGrantedLoan(boolean grantedLoan) {
        isGrantedLoan = grantedLoan;
    }

    public void setApplicationForALoanRejectionReason(String applicationForALoanRejectionReason) {
        this.applicationForALoanRejectionReason = applicationForALoanRejectionReason;
    }

    public void setIsGrantedLoan(boolean isGrantedLoan) {
        this.isGrantedLoan = isGrantedLoan;
    }
}
