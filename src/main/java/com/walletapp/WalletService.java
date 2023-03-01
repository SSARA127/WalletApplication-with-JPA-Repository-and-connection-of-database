package com.walletapp;

import java.util.List;

public interface WalletService {

    WalletDto registerWallet(WalletDto wallet)throws WalletException ;
    WalletDto getWalletById(Integer walletId,String password) throws WalletException;
    WalletDto updateWallet(WalletDto wallet,String password)throws WalletException;
    WalletDto deleteWalletById(Integer walletId,String password)throws WalletException;


    WalletDto addFundsToWalletById(Integer walletId,Double amount,String password)throws WalletException;
    WalletDto withdrawFundsFromWalletById(Integer walletById,Double amount,String password) throws WalletException;
    String fundTransfer(Integer fromWalletId,Integer toWalletId,Double amount,String password)throws WalletException;

    List<WalletDto> getAllWallets(String password)throws WalletException;
}
