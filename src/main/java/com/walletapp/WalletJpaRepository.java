package com.walletapp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletJpaRepository extends JpaRepository<WalletDto,Integer> {
    //Creation of custom queries by method name using keyWords

      List<WalletDto> findByNameofholder(String name_of_holder);
      List<WalletDto> findByNameofholderContaining(String name_of_holder);
      List<WalletDto> findByBalanceamount(Double amount);

      List<WalletDto> findByBalanceamountBetweenOrderByBalanceamountDesc(Double minBalanceAmount,Double maxBalanceAmount);

      @Query("SELECT wallet FROM WalletDto wallet")
      List<WalletDto> getAllWallets();

      @Query("SELECT wallet FROM WalletDto wallet WHERE wallet.nameofholder LIKE :nameofholder")
      List<WalletDto> getAllByNameofholderLike(String nameofholder);



}
