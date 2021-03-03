package com.mobileloan.service;

import com.mobileloan.domain.LoanData;
import com.mobileloan.domain.LoanDataDto;
import com.mobileloan.domain.LoanTerms;
import com.mobileloan.domain.LoanTermsDto;
import com.mobileloan.mapper.LoanApplicationMapper;
import com.mobileloan.mapper.LoanTermsMapper;
import com.mobileloan.repository.LoanApplicationRepository;
import com.mobileloan.repository.LoanTermsRepository;
import com.mobileloan.validator.LoanApplicationDoesntMeetLoanTermsException;
import com.mobileloan.validator.LoanApplicationValidator;
import com.mobileloan.validator.WrongLoanDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanApplicationService {
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;
    @Autowired
    private LoanApplicationMapper loanApplicationMapper;
    @Autowired
    private LoanApplicationValidator loanApplicationValidator;

    public boolean saveLoanApplication(LoanDataDto loanDataDto){
        LoanData loanData = loanApplicationMapper.mapToLoanData(loanDataDto);
        try{
            loanApplicationValidator.validateLoan(loanData);
        }catch(WrongLoanDataException | LoanApplicationDoesntMeetLoanTermsException e) {
            setRejectionInformation(loanData, e.toString());
            loanApplicationRepository.save(loanData);
            return false;
        }
        loanApplicationRepository.save(loanData);
        return true;
    }

    private void setRejectionInformation(LoanData loanData, String rejectionReason){
        loanData.setApplicationForALoanRejectionReason(rejectionReason);
        loanData.setGrantedLoan(false);
    }
}
