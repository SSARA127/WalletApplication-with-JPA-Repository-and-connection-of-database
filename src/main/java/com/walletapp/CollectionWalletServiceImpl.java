package com.walletapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Service
public class CollectionWalletServiceImpl implements WalletService{

    @Autowired
    private CollectionWalletRepository collectionWalletRepository;
    @Autowired
    private CollectionWalletRepositoryImpl collectionWalletRepositoryImpl;


    @Override
    public WalletDto registerWallet(WalletDto wallet) throws WalletException {
        System.out.println("Wallet register successfully!");
        return collectionWalletRepository.createWallet(wallet);
    }

    @Override
    public WalletDto getWalletById(Integer walletId, String password) throws WalletException {
        WalletDto foundWallet = collectionWalletRepository.getWalletById(walletId);
        if(foundWallet == null)
            throw new WalletException("Wallet Id does not exists.");
        if(!foundWallet.getPassword().equals(password))
            throw new WalletException("Incorrect password.");
        System.out.println("Wallet found successfully!");
        return foundWallet;
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet, String password) throws WalletException {
        WalletDto foundWallet = collectionWalletRepository.getWalletById(wallet.getId());
        if(foundWallet == null)
            throw new WalletException("Wallet Id does not exists.");
        if(!foundWallet.getPassword().equals(password))
            throw new WalletException("Incorrect password.");
        WalletDto updatewallet=collectionWalletRepository.updateWallet(wallet);
        updatewallet=collectionWalletRepository.getWalletById(wallet.getId());
        System.out.println("Wallet updated successfully!");
        return updatewallet;
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId, String password) throws WalletException {
        WalletDto foundWallet = collectionWalletRepository.getWalletById(walletId);
        if(foundWallet == null)
            throw new WalletException("Wallet Id does not exists.");
        if(!foundWallet.getPassword().equals(password))
            throw new WalletException("Incorrect password.");
        System.out.println("Wallet deleted successfully!");
        return collectionWalletRepository.deleteWalletById(walletId);
    }

    @Override
    public WalletDto addFundsToWalletById(Integer walletId, Double amount, String password) throws WalletException {
        WalletDto foundWallet = collectionWalletRepository.getWalletById(walletId);
        if(foundWallet == null)
            throw new WalletException("Wallet Id does not exists.");
        if(!foundWallet.getPassword().equals(password))
            throw new WalletException("Incorrect password.");
        Double current_amount=foundWallet.getBalanceamount();
        current_amount+=amount;
        foundWallet.setBalanceamount(current_amount);
        System.out.println("Successfully fund added to wallet!");
        return foundWallet;
    }

    @Override
    public WalletDto withdrawFundsFromWalletById(Integer walletId, Double amount, String password) throws WalletException {
        WalletDto foundWallet = collectionWalletRepository.getWalletById(walletId);
        if(foundWallet == null)
            throw new WalletException("Wallet Id does not exists.");
        if(!foundWallet.getPassword().equals(password))
            throw new WalletException("Incorrect password.");
        if(foundWallet.getBalanceamount()<amount)
            throw new WalletException("Amount is Insufficient to withdraw from wallet");
        Double current_amount=foundWallet.getBalanceamount();
        current_amount-=amount;
        foundWallet.setBalanceamount(current_amount);
        System.out.println("Successfully fund withdrawn from wallet!");
        return foundWallet;
    }

    @Override
    public String fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount, String password) throws WalletException {
        WalletDto fromWallet = collectionWalletRepository.getWalletById(fromWalletId);
        WalletDto toWallet = collectionWalletRepository.getWalletById(toWalletId);
        if(fromWallet == null)
            throw new WalletException("FromWallet Id does not exists.");
        if(toWallet == null)
            throw new WalletException("ToWallet Id does not exists.");
        if(!fromWallet.getPassword().equals(password) || !toWallet.getPassword().equals(password) )
            throw new WalletException("Incorrect password.");
        if(fromWallet.getBalanceamount()<amount)
            throw new WalletException("Amount is Insufficient to Transfer from Fromwallet");
        Double fromwalletamount=fromWallet.getBalanceamount();
        Double towalletamount=toWallet.getBalanceamount();
        fromwalletamount-=amount;
        fromWallet.setBalanceamount(fromwalletamount);
        towalletamount+=amount;
        toWallet.setBalanceamount(towalletamount);
        System.out.println("From Wallet : "+fromWallet);
        System.out.println("To Wallet : "+toWallet);
        return "Amount Transfered Successfully";
    }

    @Override
    public List<WalletDto> getAllWallets(String password) throws WalletException {
        int k=0;
        List<WalletDto> getAllWallet = new ArrayList<>();
        for (Map.Entry<Integer,WalletDto> i : collectionWalletRepositoryImpl.walletDtoMap.entrySet()) {
            if (k == 0) {
                WalletDto foundWallet = i.getValue();
                if (!foundWallet.getPassword().equals(password))
                    throw new WalletException("Incorrect password.");
                k++;
            }
            getAllWallet.add(i.getValue());
        }
        System.out.println("All wallet printed successfully!");
        return getAllWallet;
    }
}
