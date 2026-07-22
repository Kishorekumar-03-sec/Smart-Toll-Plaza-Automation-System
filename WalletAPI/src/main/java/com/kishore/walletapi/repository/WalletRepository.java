package com.kishore.walletapi.repository;

import com.kishore.walletapi.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Wallet findByFastagId(String fastagId);
    boolean  existsByFastagId(String fastagId);
}
