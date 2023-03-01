package com.walletapp;

import org.springframework.context.annotation.PropertySource;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class WalletDto { // POJO
    @NotNull(message = "Id can't be null")
    @Id
    private Integer id;

    @NotBlank(message = "Name of holder can't be null, it should contain chars")
    @Pattern(regexp = "[a-zA-Z]{3,16}", message = "Name of holder should contain min 3 & max 16 chars , no digits and special chars allowed.")
    private String name_of_holder;

    @NotBlank(message = "Name of wallet can't be null, it should contain chars")
    @Pattern(regexp = "[a-zA-Z]{3,16}", message = "Name of wallet should contain min 3 & max 16 chars , no digits and special chars allowed.")
    private String name_of_wallet;
    @Email(message = "Please provide valid email. e.g name@ford.com")
    private String email;
    @NotNull(message = "password can't be null")
    @Pattern(regexp = "[0-9a-zA-Z-/@#!*$%^&]{4,16}",message = "Password  should contain min 4 & max 16 letters with characters and digit")
    private String password;
    @Pattern(regexp = "[0-9]{10}",message = "Tel no should contain only 10 digits")
    private String phoneNumber;
    @NotNull(message = "Amount can't be null")
    private Double balanceamount;
    @FutureOrPresent(message = "wallet data can't be in past")
    private LocalDate date_of_wallet_creation;

    public WalletDto() {

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDate_Of_Wallet_Creation() {
        return date_of_wallet_creation;
    }

    public void setDate_Of_Wallet_Creation(LocalDate date_of_wallet_creation) {
        this.date_of_wallet_creation = date_of_wallet_creation;
    }
    public Double getBalanceamount() {
        return balanceamount;
    }

    public void setBalanceamount(Double balanceamount) {
        this.balanceamount = balanceamount;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_Of_Holder() {
        return name_of_holder;
    }

    public void setName_Of_Holder(String name_of_holder) {
        this.name_of_holder = name_of_holder;
    }
    public String getName_Of_Wallet() {
        return name_of_wallet;
    }

    public void setName_Of_Wallet(String name_of_wallet) {
        this.name_of_wallet = name_of_wallet;
    }


    public WalletDto(Integer id, String name_of_holder,String name_of_wallet, String email, String password, String phoneNumber, LocalDate date_of_wallet_creation, Double balanceamount) {
        this.id = id;
        this.name_of_holder = name_of_holder;
        this.name_of_wallet=name_of_wallet;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.date_of_wallet_creation = date_of_wallet_creation;
        this.balanceamount = balanceamount;
    }
    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", name_of_holder='" + name_of_holder + '\'' +
                ", name_of_wallet='" + name_of_wallet +
                ", email="+email+
                ",password="+password+
                ",phoneNumber="+phoneNumber+
                ",Date_of_wallet_creation="+date_of_wallet_creation+
                ",Amount=" + balanceamount +
                '}';
    }
}
