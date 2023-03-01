package com.walletapp;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CollectionWalletRepositoryImpl implements CollectionWalletRepository{
    Map<Integer,WalletDto> walletDtoMap = new HashMap<>();
    @Override
    public WalletDto createWallet(WalletDto newWallet) {

        walletDtoMap.put(newWallet.getId(),newWallet);
        return newWallet;
    }

    @Override
    public WalletDto getWalletById(Integer walletId) {
        return walletDtoMap.get(walletId);
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet) {
        return   walletDtoMap.replace(wallet.getId(),wallet);
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId) {
        return walletDtoMap.remove(walletId);
    }
}
