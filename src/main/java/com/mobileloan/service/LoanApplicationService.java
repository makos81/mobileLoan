package com.mobileloan.service;

import com.mobileloan.domain.LoanData;
import com.mobileloan.domain.LoanDataDto;
import com.mobileloan.domain.LoanTerms;
import com.mobileloan.domain.LoanTermsDto;
import com.mobileloan.mapper.LoanApplicationMapper;
import com.mobileloan.mapper.LoanTermsMapper;
import com.mobileloan.repository.LoanApplicationRepository;
import com.mobileloan.repository.LoanTermsRepository;
import com.mobileloan.validator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoanApplicationService {
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;
    @Autowired
    private LoanApplicationMapper loanApplicationMapper;
    @Autowired
    private LoanApplicationValidator loanApplicationValidator;

    public boolean saveLoanApplication(LoanDataDto loanDataDto) throws WrongLoanDataException, LoanApplicationDoesntMeetLoanTermsException,
            LoanApplicationDuringInactivityHoursException{
        LoanData loanData = loanApplicationMapper.mapToLoanData(loanDataDto);
        try{
            loanApplicationValidator.validateLoan(loanData);
        }catch(WrongLoanDataException e) {
            setRejectionInformation(loanData, e.toString());
            throw new WrongLoanDataException();
        }catch(LoanApplicationDoesntMeetLoanTermsException e){
            setRejectionInformation(loanData, e.toString());
            throw new LoanApplicationDoesntMeetLoanTermsException();
        }catch (LoanApplicationDuringInactivityHoursException e){
            setRejectionInformation(loanData, e.toString());
            throw new LoanApplicationDuringInactivityHoursException();
        }
        setDueDateAndSaveLoanApplication(loanData);
        loanApplicationRepository.save(loanData);
        return true;
    }

    private void setRejectionInformation(LoanData loanData, String rejectionReason){
        loanData.setApplicationForALoanRejectionReason(rejectionReason);
        loanData.setGrantedLoan(false);
    }

    private void setStartAndDueInformation(LoanData loanData){
        loanData.setLoanStartDateTime(LocalDateTime.now().plusDays(1));
        loanData.setLoanDueDate(loanData.getLoanStartDateTime().plusDays(loanData.getLoanTerm() + 1));
    }

    public void extendALoan(int idLoan, int terms) throws LoanNotFoundException, InvalidTermsException {
        Optional<LoanData> loanDataOptional = loanApplicationRepository.findById(idLoan);
        LoanData loanData = loanDataOptional.orElseThrow(()->new LoanNotFoundException());
        if(validateTerms(terms)){
            setNewDueDate(loanData, terms);
        }else{
            throw new InvalidTermsException();
        }
    }

    private boolean validateTerms(int terms){
        return terms > 0;
    }

    private void setNewDueDate(LoanData loanData, int terms){
        loanData.setLoanDueDate(loanData.getLoanDueDate().plusDays(terms));
    }

    private void setDueDateAndSaveLoanApplication(LoanData loanData){
        setStartAndDueInformation(loanData);
        loanApplicationRepository.save(loanData);
    }
}
