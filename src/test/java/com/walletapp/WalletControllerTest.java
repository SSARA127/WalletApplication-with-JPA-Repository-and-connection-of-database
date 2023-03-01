package com.walletapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WalletControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    LocalDate n = LocalDate.now();
    @BeforeEach
    public void init(){
        this.restTemplate.postForObject("http://localhost:" + port + "/v1/addwallet",new WalletDto(1, "saravanan","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,35000.00),WalletDto.class);
        this.restTemplate.postForObject("http://localhost:" + port + "/v1/addwallet",new WalletDto(2, "saravanan","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,350.00),WalletDto.class);
    }
    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/v1/message",
                String.class)).contains("Welcome to wallet my app!");
    }

    @Test
    public void getWalletByIdTest() throws Exception {
        WalletDto foundWallet =this.restTemplate.getForObject("http://localhost:" + port + "/v1/getwallet/1/Sara@123", WalletDto.class);
        assertEquals("saravanan",foundWallet.getName_Of_Holder());
    }

    @Test
    public void getWalletByIdExceptionTest() throws Exception {
        String walletExceptionMessage =this.restTemplate.getForObject("http://localhost:" + port + "/v1/getwallet/3/Sara@123", String.class);
        assertEquals("Wallet Id does not exists.",walletExceptionMessage);
    }
    @Test
    public void addResourceTest() throws WalletException{
        WalletDto d=this.restTemplate.postForObject("http://localhost:" + port + "/v1/addwallet",new WalletDto(1, "saravanan","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,35000.00),WalletDto.class);
        assertEquals("saravanan",d.getName_Of_Holder());
    }
    @Test
    public void replaceResourceTest() throws WalletException{
        this.restTemplate.put("http://localhost:" + port + "/v1/updatewallet/Sara@123",new WalletDto(1, "santhosh","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,35000.00),WalletDto.class);
        WalletDto foundWallet =this.restTemplate.getForObject("http://localhost:" + port + "/v1/getwallet/1/Sara@123", WalletDto.class);
        assertEquals("santhosh",foundWallet.getName_Of_Holder());
    }
    @Test
    public void deleteResourceTest() throws WalletException{
        this.restTemplate.delete("http://localhost:" + port + "/v1/deletewallet/1/Sara@123");
        String walletExceptionMessage =this.restTemplate.getForObject("http://localhost:" + port + "/v1/getwallet/3/Sara@123", String.class);
        assertEquals("Wallet Id does not exists.",walletExceptionMessage);
    }
    @Test
    public void addFundsToResourceTest()throws WalletException{
        WalletDto wallet=this.restTemplate.postForObject("http://localhost:" + port + "/v1/addfundstowallet/1/amount/100/password/Sara@123",new WalletDto(1, "saravanan","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,35000.00),WalletDto.class);
        assertEquals(35100,wallet.getBalanceamount());
    }
    @Test
    public void withdrawFundsFromResourceTest() throws WalletException{
        WalletDto wallet=this.restTemplate.postForObject("http://localhost:" + port + "/v1/withdrawfundsfromwallet/1/amount/1000/password/Sara@123",new WalletDto(1, "saravanan","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,35000.00),WalletDto.class);
        assertEquals(34000,wallet.getBalanceamount());
    }
    @Test
    public void TransferfundsfromResourceTest()throws WalletException{
        String wallet=this.restTemplate.postForObject("http://localhost:" + port + "/v1/transferfundsBywallet/fromwalletId/1/towalletId/2/amount/100/password/Sara@123",new WalletDto(1, "saravanan","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,35000.00),String.class);
        assertEquals("Amount Transfered Successfully",wallet);
    }
    @Test
    public void TransferfundsfromResourcefromwalletTest()throws WalletException{
        String wallet=this.restTemplate.postForObject("http://localhost:" + port + "/v1/transferfundsBywallet/fromwalletId/1/towalletId/2/amount/100/password/Sara@123",new WalletDto(1, "saravanan","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,35000.00),String.class);
        WalletDto foundWallet =this.restTemplate.getForObject("http://localhost:" + port + "/v1/getwallet/1/Sara@123", WalletDto.class);
        assertEquals(34900,foundWallet.getBalanceamount());
    }
    @Test
    public void TransferfundsfromResourcetowalletTest()throws WalletException{
        String wallet=this.restTemplate.postForObject("http://localhost:" + port + "/v1/transferfundsBywallet/fromwalletId/1/towalletId/2/amount/100/password/Sara@123",new WalletDto(1, "saravanan","googlepay", "saravanan@gmail.com", "Sara@123", "9384196731", n,35000.00),String.class);
        WalletDto foundWallet =this.restTemplate.getForObject("http://localhost:" + port + "/v1/getwallet/2/Sara@123", WalletDto.class);
        assertEquals(450,foundWallet.getBalanceamount());
    }

}
