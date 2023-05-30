package com.walletapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class WalletDto { // POJO
    @NotNull(message = "Id can't be null")
    @Id
    private Integer id;

    @NotBlank(message = "Name of holder can't be null, it should contain chars")
    @Pattern(regexp = "[a-zA-Z ]{3,16}", message = "Name of holder should contain min 3 & max 16 chars , no digits and special chars allowed.")
    private String nameofholder;

    @NotBlank(message = "Name of wallet can't be null, it should contain chars")
    @Pattern(regexp = "[a-zA-Z ]{3,16}", message = "Name of wallet should contain min 3 & max 16 chars , no digits and special chars allowed.")
    private String nameofwallet;
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
    private LocalDate dateofwalletcreation;

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
    public String getNameOfHolder() {
        return nameofholder;
    }

    public void setNameOfHolder(String nameofholder) {
        this.nameofholder = nameofholder;
    }
    public String getNameOfWallet() {
        return nameofwallet;
    }

    public void setNameOfWallet(String nameofwallet) {
        this.nameofwallet = nameofwallet;
    }

    public LocalDate getDateOfWalletCreation() {
        return dateofwalletcreation;
    }

    public void setDateOfWalletCreation(LocalDate dateofwalletcreation) {
        this.dateofwalletcreation = dateofwalletcreation;
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



    public WalletDto(Integer id, String nameofholder,String nameofwallet, String email, String password, String phoneNumber, LocalDate dateofwalletcreation, Double balanceamount) {
        this.id = id;
        this.nameofholder = nameofholder;
        this.nameofwallet=nameofwallet;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dateofwalletcreation = dateofwalletcreation;
        this.balanceamount = balanceamount;
    }
    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", name_of_holder='" + nameofholder + '\'' +
                ", name_of_wallet='" + nameofwallet +
                ", email="+email+
                ",password="+password+
                ",phoneNumber="+phoneNumber+
                ",Date_of_wallet_creation="+dateofwalletcreation+
                ",Amount=" + balanceamount +
                '}';
    }
}
