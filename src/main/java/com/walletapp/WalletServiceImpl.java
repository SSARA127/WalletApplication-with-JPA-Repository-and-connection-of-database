package com.walletapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService{
    @Autowired
    private WalletJpaRepository walletJpaRepository;

    @Override
    public WalletDto registerWallet(WalletDto wallet) throws WalletException {
        System.out.println("Wallet register successfully!");
        return this.walletJpaRepository.save(wallet);
    }

    @Override
    public WalletDto getWalletById(Integer walletId, String password) throws WalletException {
        Optional<WalletDto> walletOptional = this.walletJpaRepository.findById(walletId);
        if(walletOptional.isEmpty())
            throw new WalletException("Wallet Id does not exists.");
        if(!walletOptional.get().getPassword().equals(password))
            throw new WalletException("Incorrect password.");
        System.out.println("Wallet found successfully!");
        return walletOptional.get();
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet, String password) throws WalletException {
        Optional<WalletDto> walletOptional = this.walletJpaRepository.findById(wallet.getId());
        if(walletOptional.isEmpty())
            throw new WalletException("Wallet Id does not exists.");
        if(!walletOptional.get().getPassword().equals(password))
            throw new WalletException("Incorrect password.");
        System.out.println("Wallet updated successfully!");
        return this.walletJpaRepository.save(wallet);
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId, String password) throws WalletException {
        Optional<WalletDto> walletOptional = this.walletJpaRepository.findById(walletId);
        if(walletOptional.isEmpty())
            throw new WalletException("Wallet Id does not exists.");
        if(!walletOptional.get().getPassword().equals(password))
            throw new WalletException("Incorrect password.");
        WalletDto foundWallet = walletOptional.get();
        this.walletJpaRepository.delete(foundWallet);
        System.out.println("Wallet deleted successfully!");
        return foundWallet;
    }

    @Override
    public WalletDto addFundsToWalletById(Integer walletId, Double amount, String password) throws WalletException {
        Optional<WalletDto> walletOptional = this.walletJpaRepository.findById(walletId);
        if(walletOptional.isEmpty())
            throw new WalletException("Wallet Id does not exists.");
        if(!walletOptional.get().getPassword().equals(password))
            throw new WalletException("Incorrect password.");
        Double current_amount=walletOptional.get().getBalanceamount();
        current_amount+=amount;
        walletOptional.get().setBalanceamount(current_amount);
        this.walletJpaRepository.save(walletOptional.get());
        System.out.println("Successfully fund added to wallet!");
        return walletOptional.get();
    }

    @Override
    public WalletDto withdrawFundsFromWalletById(Integer walletId, Double amount, String password) throws WalletException {
        Optional<WalletDto> walletOptional = this.walletJpaRepository.findById(walletId);
        if(walletOptional.isEmpty())
            throw new WalletException("Wallet Id does not exists.");
        if(!walletOptional.get().getPassword().equals(password))
            throw new WalletException("Incorrect password.");
        if(walletOptional.get().getBalanceamount()<amount)
            throw new WalletException("Amount is Insufficient to withdraw from wallet");
        Double current_amount=walletOptional.get().getBalanceamount();
        current_amount-=amount;
        walletOptional.get().setBalanceamount(current_amount);
        this.walletJpaRepository.save(walletOptional.get());
        System.out.println("Successfully fund withdrawn from wallet!");
        return walletOptional.get();
    }

    @Override
    public String fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount, String password) throws WalletException {
        Optional<WalletDto> fromwalletOptional = this.walletJpaRepository.findById(fromWalletId);
        Optional<WalletDto> towalletOptional = this.walletJpaRepository.findById(toWalletId);
        if(fromwalletOptional.isEmpty())
            throw new WalletException("FromWallet Id does not exists.");
        if(towalletOptional.isEmpty())
            throw new WalletException("ToWallet Id does not exists.");
        if(!(fromwalletOptional.get().getPassword().equals(password) && towalletOptional.get().getPassword().equals(password)))
            throw new WalletException("Incorrect password.");
        if(fromwalletOptional.get().getBalanceamount()<amount)
            throw new WalletException("Amount is Insufficient to transfer from FromWallet");
        Double fromwalletamount=fromwalletOptional.get().getBalanceamount();
        Double towalletamount=towalletOptional.get().getBalanceamount();
        fromwalletamount-=amount;
        fromwalletOptional.get().setBalanceamount(fromwalletamount);
        towalletamount+=amount;
        towalletOptional.get().setBalanceamount(towalletamount);
        this.walletJpaRepository.save(fromwalletOptional.get());
        this.walletJpaRepository.save(towalletOptional.get());
        System.out.println("From Wallet : "+fromwalletOptional.get());
        System.out.println("To Wallet : "+towalletOptional.get());
        return "Amount Transfered Successfully";
    }

    @Override
    public List<WalletDto> getAllWallets(String password) throws WalletException {

        return this.walletJpaRepository.findAll();
    }


}
