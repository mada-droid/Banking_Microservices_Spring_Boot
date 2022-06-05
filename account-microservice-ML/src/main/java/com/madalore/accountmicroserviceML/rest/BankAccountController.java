package com.madalore.accountmicroserviceML.rest;

import com.madalore.accountmicroserviceML.utils.BankAccountUtils;
import com.madalore.accountmicroserviceML.dto.BankAccountDTO;
import com.madalore.accountmicroserviceML.dto.UserDTO;
import com.madalore.accountmicroserviceML.entity.State;
import com.madalore.accountmicroserviceML.service.BankAccountService;
import com.madalore.accountmicroserviceML.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankAccountController {

    @Autowired
    private UserService userService;

    private BankAccountService bankAccountService;

    private BankAccountUtils bankAccountUtils = new BankAccountUtils();

//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Bean
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/{userId}/accounts")
    public List<BankAccountDTO> findAllByUserId(@PathVariable int userId){
        return bankAccountService.findAllByUserId(userId);
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> findAll() {
        return bankAccountService.findAll();
    }


    @GetMapping("/{accountState}/accounts")
    public List<BankAccountDTO> findAllByStato(@PathVariable String accountState){
        return bankAccountService.findAllByState(accountState);
    }

    @GetMapping("/{userEmail}/accounts")
    public List<BankAccountDTO> findAllByUserEmail(@PathVariable String userEmail){
        return bankAccountService.findAllByUserEmail(userEmail);
    }

    @PostMapping("/accounts/{userId}/addAccount")
    public BankAccountDTO addBankAccount(@PathVariable int userId) {

        UserDTO userDTO = userService.findUserById(userId);

        BankAccountDTO bankAccountDTO = new BankAccountDTO( bankAccountUtils.generateIban()
                , State.INACTIVE, 0.00, userDTO, bankAccountUtils.getAccountNumber());

        bankAccountService.save(bankAccountDTO);
        return bankAccountDTO;
    }

    @PutMapping("/accounts")
    public BankAccountDTO updateBankAccount(@RequestBody BankAccountDTO bankAccountDTO) {
        bankAccountService.save(bankAccountDTO);
        return bankAccountDTO;
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
    public BankAccountDTO attivareBankAccount(@PathVariable int theId) {
        BankAccountDTO bankAccountDTO = bankAccountService.findBankAccountById(theId);
        bankAccountDTO.setState(State.ACTIVE);
        bankAccountService.save(bankAccountDTO);
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
    @PostMapping("users")
    public UserDTO addBankAccount(@RequestBody UserDTO userDTO) {

        BankAccountDTO bankAccountDTO = new BankAccountDTO(bankAccountUtils.generateIban()
                , State.INACTIVE, 0.00, userDTO,bankAccountUtils.getAccountNumber());
//        userService.save(userDTO);
        bankAccountService.save(bankAccountDTO);
        return userDTO;

    }



}
