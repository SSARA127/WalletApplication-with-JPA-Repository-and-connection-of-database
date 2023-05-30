package com.walletapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.BDDMockito.given;
@SpringBootTest
public class WalletServiceByMockRepository {
    @Autowired
    private WalletService walletService;
    @MockBean
    private CollectionWalletRepository collectionWalletRepository;
    LocalDate n=LocalDate.now();
    @Test
    public void testServiceWithOutActualRepository() throws WalletException{
        WalletDto wallet=this.walletService.registerWallet(new WalletDto(1, "saravanan","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,35000.00));
        given(this.collectionWalletRepository.getWalletById(1))
                .willReturn(wallet);
        assertEquals("saravanan",this.walletService.getWalletById(1,"Sara@123").getNameOfHolder());
    }

    @Test
    public void testGetWalletThrowsExceptionTest() throws WalletException{

        given(this.collectionWalletRepository.getWalletById(100))
                .willReturn(null);
        assertThrows(WalletException.class,()->walletService.getWalletById(100,"Sara@123"));
    }
}
