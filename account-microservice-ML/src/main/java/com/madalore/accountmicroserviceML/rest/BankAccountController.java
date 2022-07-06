package com.madalore.accountmicroserviceML.rest;

import com.madalore.accountmicroserviceML.utils.BankAccountUtils;
import com.madalore.accountmicroserviceML.dto.BankAccountDTO;
import com.madalore.accountmicroserviceML.entity.State;
import com.madalore.accountmicroserviceML.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class BankAccountController {

    private BankAccountService bankAccountService;

    private BankAccountUtils bankAccountUtils = new BankAccountUtils();

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    //prints out all bank accounts associated with a certain user
    @GetMapping("users/{userId}/accounts")
    public List<BankAccountDTO> findAllByUserId(@PathVariable int userId) {

        int inactiveAccounts = 0;
        List<BankAccountDTO> bankAccountDTOList = bankAccountService.findAllByUserId(userId);

        for (BankAccountDTO bankAccountDTO : bankAccountDTOList) {
            if(bankAccountDTO.getState().toString().equals("INACTIVE")){
                inactiveAccounts++;
            }
        }
        if(inactiveAccounts == bankAccountDTOList.size()){
            throw new RuntimeException("Non hai nessun conto attivo, contatta il supporto!");
        }

        return bankAccountDTOList;

    }

    //prints out all requests made by users, so that the employee can accept or deny them
    @GetMapping("/accounts/requests")
    public List<BankAccountDTO> findAllByRequest() {
        return bankAccountService.findAllByRequest();
    }

    //Activate a specific account passing in its id(theId)
    @GetMapping("/activation/accounts/{theId}")
    public BankAccountDTO activateBankAccount(@PathVariable int theId) {
        BankAccountDTO bankAccountDTO = bankAccountService.findBankAccountById(theId);
        bankAccountDTO.setState(State.ACTIVE);
        bankAccountDTO.setId(theId);
        bankAccountService.update(bankAccountDTO);
        return bankAccountDTO;
    }

    //Activate a specific account passing in its id(theId)
    @GetMapping("/inactivation/accounts/{theId}")
    public BankAccountDTO inactivateBankAccount(@PathVariable int theId) {
        BankAccountDTO bankAccountDTO = bankAccountService.findBankAccountById(theId);
        bankAccountDTO.setState(State.INACTIVE);
        bankAccountDTO.setId(theId);
        bankAccountService.update(bankAccountDTO);
        return bankAccountDTO;
    }

    //Requesting a closure for the bank account by the user, passing in the account's id(theId)
    // that must be firstly validated by the employee
    @GetMapping("/closure_request/accounts/{theId}")
    public BankAccountDTO closureBankAccount(@PathVariable int theId) {
        BankAccountDTO bankAccountDTO = bankAccountService.findBankAccountById(theId);
        if(bankAccountDTO.getState().toString().equals("CLOSURE_REQUEST")){
            throw new RuntimeException("Hai già richiesto la chiusura!");
        }
        bankAccountDTO.setState(State.CLOSURE_REQUEST);
        bankAccountDTO.setId(theId);
        bankAccountService.update(bankAccountDTO);
        return bankAccountDTO;
    }

    //add mapping for POST /addAccount/users/{userId},
    //adding a/another bank account to a specific user passing in his/her id(userId)
    //gets its calls only internally
    @GetMapping("/addAccount/users/{userId}")
    public BankAccountDTO addBankAccount(@PathVariable int userId) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO(bankAccountUtils.generateIban()
                , State.ACTIVATION_REQUEST, 0.00, userId, bankAccountUtils.getAccountNumber());
        bankAccountService.save(bankAccountDTO);
        return bankAccountDTO;
    }

    //gets the account by its id
    @GetMapping("/{accountId}")
    public BankAccountDTO getBankAccountById(@PathVariable int accountId){
        return bankAccountService.findBankAccountById(accountId);
    }

    //Open a new account providing an account's number of the same user,
    // and an initial amount from the old account into the new one
    @RequestMapping(value = "{userId}/addAccount/{accountNumber}/{amountIniziale}", method = RequestMethod.GET)
    public BankAccountDTO addAnotherAccount(@PathVariable("userId") int userId,
                                            @PathVariable("accountNumber") String accountNumber,
                                            @PathVariable("amountIniziale") double amountIniziale) {

        List<BankAccountDTO> bankAccountDTOList = bankAccountService.findAllByUserId(userId);
        if (bankAccountDTOList.size() > 1) {
            throw new RuntimeException("Non puoi avere più di due conti!");
        }

        if(amountIniziale == 0){
            throw new RuntimeException("l'importo passato non può essere pari a 0!");
        }

        if (accountNumber.equals(bankAccountDTOList.get(0).getAccountNumber())) {
            if (amountIniziale <= bankAccountDTOList.get(0).getBalance()) {
                BankAccountDTO bankAccountDTO1 = new BankAccountDTO(bankAccountUtils.generateIban()
                        , State.ACTIVATION_REQUEST, 0.00, userId, bankAccountUtils.getAccountNumber());
                bankAccountDTOList.get(0).setBalance(bankAccountDTOList.get(0).getBalance() - amountIniziale);
                bankAccountDTO1.setBalance(bankAccountDTO1.getBalance() + amountIniziale);
                bankAccountService.update(bankAccountDTOList.get(0));
                bankAccountService.save(bankAccountDTO1);
                return bankAccountService.findBankAccountByAccountNumber(bankAccountDTO1.getAccountNumber());
            } else {
                throw new RuntimeException("Importo iniziale errato, prova a contattare il supporto!");
            }
        } else {
            throw new RuntimeException("Dovrai indicare un tuo numero di conto già esistente!");
        }
    }

}
