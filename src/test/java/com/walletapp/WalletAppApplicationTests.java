package com.walletapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class WalletAppApplicationTests {
//
//	WalletDto registerWallet(WalletDto wallet)throws WalletException ;
//	WalletDto getWalletById(Integer walletId) throws WalletException;
//	WalletDto updateWallet(WalletDto wallet)throws WalletException;
//	WalletDto deleteWalletById(Integer walletId)throws WalletException;
//
//
//	Double addFundsToWalletById(Integer walletId,Double amount)throws WalletException;
//	Double withdrawFundsFromWalletById(Integer walletById,Double amount) throws WalletException;
//	Boolean fundTransfer(Integer fromWalletId,Integer toWalletId,Double amount)throws WalletException;
//
//	List<WalletDto> getAllWallets();

	@Autowired
	private WalletService walletService;
	@Autowired
	private CollectionWalletRepositoryImpl collectionWalletRepository;
	LocalDate n=LocalDate.now();
	@BeforeEach
	public void init() throws WalletException {
		this.walletService.registerWallet(new WalletDto(1, "saravanan","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,35000.00));
		this.walletService.registerWallet(new WalletDto(2, "saravanan","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,3000.00));
	}
	@Test
	void registerWalletTest() throws WalletException {
		WalletDto wallet = this.walletService.registerWallet(new WalletDto(1, "saravanan","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,35000.00));
		assertEquals("saravanan",wallet.getName_Of_Holder());
	}
	@Test
	void updateWalletTestold() throws WalletException {
		WalletDto oldwallet=this.walletService.registerWallet(new WalletDto(1, "saravanan","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,35000.00));
		WalletDto updatedwallet=this.walletService.updateWallet(new WalletDto(1, "santhosh","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,35000.00),"Sara@123");
		WalletDto d=walletService.getWalletById(1,"Sara@123");
		assertEquals("santhosh",d.getName_Of_Holder());
	}
	@Test
	void updateWalletTest() throws WalletException {
		WalletDto updatedwallet=this.walletService.updateWallet(new WalletDto(1, "santhosh","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,35000.00),"Sara@123");
		WalletDto d=walletService.getWalletById(1,"Sara@123");
		assertEquals("santhosh",d.getName_Of_Holder());
	}

	@Test
	void getWalletByIdTest() throws WalletException {
		WalletDto d=walletService.getWalletById(1,"Sara@123");
		assertEquals("saravanan",d.getName_Of_Holder());
	}
	@Test
	void getWalletByIdThrowsExceptionTest() {
		assertThrows(WalletException.class,()->this.walletService.getWalletById(1000,"Sara@123"));
	}
	@Test
	void deleteWalletByIdTest() throws WalletException {
		WalletDto d=walletService.deleteWalletById(1,"Sara@123");
		assertEquals("saravanan",d.getName_Of_Holder());
	}
	@Test
	void deleteWalletByIdThrowsExceptionTest() {
		assertThrows(WalletException.class,()->this.walletService.deleteWalletById(100,"Sara@123"));
	}

	@Test
	void addFundsToWalletByIdTest()throws WalletException{
		WalletDto wallet=this.walletService.addFundsToWalletById(1,500.00,"Sara@123");
		assertEquals(35500,wallet.getBalanceamount());
	}
	@Test
	void addFundsToWalletByIdTestException()throws WalletException{
		WalletDto wallet=this.walletService.addFundsToWalletById(1,500.00,"Sara@123");
		assertEquals(35500,wallet.getBalanceamount());
	}
	@Test
	void withdraFundsToWalletByIdTest()throws WalletException{
		WalletDto wallet=this.walletService.withdrawFundsFromWalletById(1,1000.00,"Sara@123");
		assertEquals(34000,wallet.getBalanceamount());
	}
	@Test
	void fundtransferTest()throws WalletException {
		String wallet = this.walletService.fundTransfer(1, 2, 1000.00, "Sara@123");
		WalletDto w = this.walletService.getWalletById(1, "Sara@123");
		assertEquals(34000, w.getBalanceamount());
	}


}
