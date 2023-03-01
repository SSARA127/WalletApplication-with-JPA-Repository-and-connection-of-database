package com.walletapp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletJpaRepository extends JpaRepository<WalletDto,Integer> {
    //Creation of custom queries by method name using keyWords

    List<WalletDto> findByName_of_holder(String name_of_holder);
    List<WalletDto> findByName_Of_HolderContaining(String name_of_holder);
    List<WalletDto> findByBalanceamountOrderByBalanceamountDesc(Double minBalanceAmount,Double maxBalanceAmount);

    @Query("SELECT wallet FROM WalletDto wallet")
    List<WalletDto> getAllWallets();

    @Query("SELECT wallet FROM WalletDto wallet WHERE wallet.name_of_holder LIKE :name_of_holder")
    List<WalletDto> getAllByName_Of_HolderLike(String name_of_holder);



}
