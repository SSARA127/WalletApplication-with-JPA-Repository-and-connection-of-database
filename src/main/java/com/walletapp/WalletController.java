package com.walletapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1") //
@CrossOrigin(value="http://localhost:4200/")
public class WalletController {
    @Autowired
    private WalletService walletService ;

    @Autowired
    private WalletJpaRepository walletJpaRepository;
    @GetMapping("/message")
    public String greet(){
        return "Welcome to wallet my app!";
    }

    @GetMapping("/getwallet/{id}/{password}")
    @ResponseStatus(value=HttpStatus.CREATED)
    public WalletDto getWalletById(@PathVariable Integer id,@PathVariable String password) throws WalletException{
        return walletService.getWalletById(id,password);
    }
    @PostMapping("/addwallet")
    @ResponseStatus(value=HttpStatus.CREATED)
    public WalletDto addResource(@Valid @RequestBody WalletDto wallet) throws WalletException {
        return walletService.registerWallet(wallet);
    }

    @PutMapping("/updatewallet/{password}")
    @ResponseStatus(value=HttpStatus.CREATED)
    public WalletDto replaceResource(@Valid @RequestBody WalletDto wallet,@PathVariable String password) throws WalletException {
        return walletService.updateWallet(wallet,password);
    }

    @DeleteMapping("/deletewallet/{walletId}/{password}")
    public WalletDto deleteResource(@PathVariable("walletId") Integer walletId,@PathVariable String password ) throws WalletException {

        return walletService.deleteWalletById(walletId, password);
    }
    @PatchMapping("/patchwallet/{id}/name/{walletName}/{password}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String updateResourceName(@PathVariable("id") Integer walletId,@PathVariable("walletName") String walletName,@PathVariable String password){
        return "Patch !"+walletId+":"+walletName;
    }
    @PostMapping("/addfundstowallet/{id}/amount/{amount}/password/{password}")
    public WalletDto addFundsToResource(@PathVariable Integer id,@PathVariable Double amount,@PathVariable String password)throws WalletException{
        return walletService.addFundsToWalletById(id,amount,password);
    }
    @PostMapping("/withdrawfundsfromwallet/{id}/amount/{amount}/password/{password}")
    public WalletDto withdrawFundsFromResource(@PathVariable Integer id,@PathVariable Double amount,@PathVariable String password) throws WalletException{
        return walletService.withdrawFundsFromWalletById(id,amount,password);
    }
    @PostMapping("/transferfundsBywallet/fromwalletId/{fromwalletId}/towalletId/{towalletId}/amount/{amount}/password/{password}")
    public boolean TransferfundsfromResource(@PathVariable Integer fromwalletId,@PathVariable Integer towalletId,@PathVariable Double amount,@PathVariable String password)throws WalletException{
        return walletService.fundTransfer(fromwalletId,towalletId,amount,password);
    }
    @GetMapping("/getAllwallet/password/{password}")
    public List<WalletDto> getAllWallets(@PathVariable String password)throws WalletException{
        return walletService.getAllWallets(password);
    }

    @GetMapping("findallwalletbynameofholder/nameofholder/{nameofholder}")
    public List<WalletDto> getAllWalletHavingNameOfHolder(@PathVariable String nameofholder){
        return this.walletJpaRepository.findByNameofholder(nameofholder);
    }
    @GetMapping("findallwalletbycontainingnameofholder/contain/{nameofholder}")
    public List<WalletDto> getAllWalletContainingNameOfHolder(@PathVariable("nameofholder") String nameofholder){
        return this.walletJpaRepository.findByNameofholderContaining(nameofholder);
    }

    @GetMapping("getwalletbyamountbetween/Amount/{minAmount}/{maxAmount}")
    public List<WalletDto> findAllWalletHavingAmountBetween(@PathVariable("minAmount") Double minAmount,
                                                                 @PathVariable("maxAmount")Double maxAmount){
        return this.walletJpaRepository.findByBalanceamountBetweenOrderByBalanceamountDesc(minAmount,maxAmount);
    }
    @GetMapping("getwalletbybalanceamount/{amount}")
    public List<WalletDto> findwalletByBalanceamount(Double amount){
        return this.walletJpaRepository.findByBalanceamount(amount);
    }

    @GetMapping("custom/wallet/{nameofholder}")
    public List<WalletDto> findAllWalletHavingNameOfHolder(@PathVariable("nameofholder") String nameofholder){
        return this.walletJpaRepository.getAllByNameofholderLike("%"+nameofholder+"%");
    }

}
