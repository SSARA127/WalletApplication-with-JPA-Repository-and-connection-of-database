package com.walletapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WalletRepositoryTest {
    @Autowired
    private CollectionWalletRepository collectionWalletRepository;
    LocalDate n = LocalDate.now();

    @BeforeEach
    public void init() {
        this.collectionWalletRepository.createWallet(new WalletDto(1, "saravanan", "googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n, 35000.00));
    }

    @Test
    public void createWalletTest() {
        WalletDto wallet = this.collectionWalletRepository.createWallet(new WalletDto(1, "saravanan", "googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n, 35000.00));
        assertEquals("saravanan", wallet.getName_Of_Holder());
    }

    @Test
    public void getWalletByIdTest() {

        assertEquals("saravanan", collectionWalletRepository.getWalletById(1).getName_Of_Holder());
    }

    @Test
    public void deleteWalletByIdTest() {
        WalletDto deletedWallet = collectionWalletRepository.deleteWalletById(1);
        assertEquals("saravanan", deletedWallet.getName_Of_Holder());
    }

    @Test
    void updateWalletTest() {
        WalletDto updatedwallet = this.collectionWalletRepository.updateWallet(new WalletDto(1, "santhosh", "googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n, 35000.00));
        WalletDto d = collectionWalletRepository.getWalletById(1);
        assertEquals("santhosh", d.getName_Of_Holder());
    }

    @Test
    void updateWalletTest1() {
        WalletDto oldwallet = this.collectionWalletRepository.createWallet(new WalletDto(1, "saravanan", "googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n, 35000.00));
        WalletDto updatedwallet = this.collectionWalletRepository.updateWallet(new WalletDto(1, "santhosh", "googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n, 35000.00));
        WalletDto d = collectionWalletRepository.getWalletById(1);
        assertEquals("santhosh", d.getName_Of_Holder());
    }
}
