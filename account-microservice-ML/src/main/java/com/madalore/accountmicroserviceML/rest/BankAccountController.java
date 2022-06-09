package com.madalore.accountmicroserviceML.rest;

import com.madalore.accountmicroserviceML.utils.BankAccountUtils;
import com.madalore.accountmicroserviceML.dto.BankAccountDTO;
import com.madalore.accountmicroserviceML.entity.State;
import com.madalore.accountmicroserviceML.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankAccountController {

    private BankAccountService bankAccountService;

    private BankAccountUtils bankAccountUtils = new BankAccountUtils();

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("users/{userId}/accounts")
    public List<BankAccountDTO> findAllByUserId(@PathVariable int userId) {
        return bankAccountService.findAllByUserId(userId);
    }

    @GetMapping("/accounts/requests")
    public List<BankAccountDTO> findAllByRequest() {
        return bankAccountService.findAllByRequest();
    }

    @DeleteMapping("/accounts/{accountId}")
    public String deleteBankAccount(@PathVariable int accountId) {

        BankAccountDTO bankAccountDTO = bankAccountService.findBankAccountById(accountId);

        if (bankAccountDTO == null) {
            throw new RuntimeException("Account with id number: " + accountId + "can't be found");
        }

        bankAccountService.deleteById(accountId);

        return "Deleted account id - " + accountId;
    }

    @PutMapping("accounts/activation/{theId}")
    public BankAccountDTO activateBankAccount(@PathVariable int theId) {
        BankAccountDTO bankAccountDTO = bankAccountService.findBankAccountById(theId);
        bankAccountDTO.setState(State.ACTIVE);
        bankAccountDTO.setId(theId);
        bankAccountService.update(bankAccountDTO);
        return bankAccountDTO;
    }

    @PutMapping("accounts/closure_request/{theId}")
    public BankAccountDTO closureBankAccount(@PathVariable int theId) {
        BankAccountDTO bankAccountDTO = bankAccountService.findBankAccountById(theId);
        bankAccountDTO.setState(State.CLOSURE_REQUEST);
        bankAccountDTO.setId(theId);
        bankAccountService.update(bankAccountDTO);
        return bankAccountDTO;
    }

    @PutMapping("accounts/activation_request/{theId}")
    public BankAccountDTO activationRequestBankAccount(@PathVariable int theId) {
        BankAccountDTO bankAccountDTO = bankAccountService.findBankAccountById(theId);
        bankAccountDTO.setState(State.ACTIVATION_REQUEST);
        bankAccountDTO.setId(theId);
        bankAccountService.update(bankAccountDTO);
        return bankAccountDTO;
    }

//    @RequestMapping(value = "/conti/{utenteId}/addAccount/{numeroConto}",method = RequestMethod.POST)
//    public Conto addAccounts(@PathVariable("utenteId") int utenteId,@PathVariable("numeroConto") String numeroConto){
////        UtenteController utenteController = new UtenteController(utenteService);
//        Utente utente = utenteService.findById(utenteId);
//
//        List<ContoDTO> contoList = contoService.findAll();
//        for (ContoDTO conto : contoList) {
//            if(conto.getNumeroConto().equals(numeroConto)){
//                if(conto.getUtente().getId() == utenteId){
//                    Conto conto1 = new Conto("IT" + generaIban()
//                            , "inattivo", 0.00, utente, getNumeroConto());
//                    contoService.save(conto1);
//                    return conto1;
//                }
//            }
//        }
//        throw new RuntimeException("Either your id or account's number is incorrect!");
//    }


    //add mapping for POST /users - add new users
    @PostMapping("addAccount/users/{userId}")
    public BankAccountDTO addBankAccount(@PathVariable int userId) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO(bankAccountUtils.generateIban()
                , State.INACTIVE, 0.00, userId, bankAccountUtils.getAccountNumber());
        bankAccountService.save(bankAccountDTO);
        return bankAccountDTO;
    }

    @RequestMapping(value = "accounts/{theId}/update_deposit/{amount}", method = RequestMethod.PUT)
    public BankAccountDTO updateDepositBankAccount
            (@PathVariable("theId") int theId, @PathVariable("amount") double amount) {
        BankAccountDTO bankAccountDTO = bankAccountService.findBankAccountById(theId);
        if (bankAccountDTO.getState().equals(State.ACTIVE)) {
            bankAccountDTO.setBalance(bankAccountDTO.getBalance() + amount);
            bankAccountDTO.setId(theId);
            bankAccountService.update(bankAccountDTO);
            return bankAccountDTO;
        } else {
            throw new RuntimeException("Your account is not in an active status, please contact the support!");
        }
    }

    @RequestMapping(value = "transfer/{originAccount}/to/{destinationAccount}/{amount}", method = RequestMethod.PUT)
    public String updateBalanceAfterTransfer
            (@PathVariable("originAccount") String originAccount,
             @PathVariable("destinationAccount") String destinationAccount,
             @PathVariable("amount") double amount){

        BankAccountDTO originBankAccountDTO = bankAccountService.findBankAccountByAccountNumber(originAccount);
        BankAccountDTO destinationBankAccountDTO = bankAccountService.findBankAccountByAccountNumber(destinationAccount);

        updateWithdrawalBankAccount(originBankAccountDTO.getId(),amount);
        updateDepositBankAccount(destinationBankAccountDTO.getId(),amount);

        return "The Transfer was successful";

    }


    @RequestMapping(value = "accounts/{theId}/update_withdrawal/{amount}", method = RequestMethod.PUT)
    public BankAccountDTO updateWithdrawalBankAccount
            (@PathVariable("theId") int theId, @PathVariable("amount") double amount) {
        BankAccountDTO bankAccountDTO = bankAccountService.findBankAccountById(theId);
        if (!bankAccountDTO.getState().equals(State.ACTIVE)) {
            throw new RuntimeException("Your account is not in an active status, please contact the support!");
        }
        double balanceToCheck = bankAccountDTO.getBalance();
        if (balanceToCheck >= amount) {
            bankAccountDTO.setBalance(balanceToCheck - amount);
            bankAccountDTO.setId(theId);
            bankAccountService.update(bankAccountDTO);
            return bankAccountDTO;
        } else if (balanceToCheck < amount) {
            throw new RuntimeException("You have insufficient balance");
        }
        throw new RuntimeException("Something went off, please contact the support!");
    }
}
