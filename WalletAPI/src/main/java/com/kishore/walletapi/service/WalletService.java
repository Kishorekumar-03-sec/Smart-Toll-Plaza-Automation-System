package com.kishore.walletapi.service;

import com.kishore.walletapi.dto.DeductRequest;
import com.kishore.walletapi.dto.RechargeRequest;
import com.kishore.walletapi.dto.WalletRequest;
import com.kishore.walletapi.entity.Wallet;
import com.kishore.walletapi.exception.DuplicateFasTagException;
import com.kishore.walletapi.exception.FasTagNotFoundException;
import com.kishore.walletapi.exception.InsufficientBalanceException;
import com.kishore.walletapi.exception.InvalidAmountException;
import com.kishore.walletapi.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public ResponseEntity<Wallet> createWallet(WalletRequest walletRequest) {

        log.info("Received request to create a new wallet");

        if (walletRepository.existsByFastagId(walletRequest.getFastagId())) {
            log.warn("Duplicate FASTag ID: {}", walletRequest.getFastagId());
            throw new DuplicateFasTagException("FASTag ID must be unique");
        }

        Wallet wallet = new Wallet();
        wallet.setFastagId(walletRequest.getFastagId());
        wallet.setBalance(0);

        wallet = walletRepository.save(wallet);

        log.info("Wallet created successfully with id {}", wallet.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(wallet);
    }

    public ResponseEntity<Wallet> recharge(RechargeRequest rechargeRequest) {

        log.info("Received request to recharge wallet with FASTag ID {}",
                rechargeRequest.getFastagId());

        if (!walletRepository.existsByFastagId(rechargeRequest.getFastagId())) {
            log.warn("FASTag ID {} not found", rechargeRequest.getFastagId());
            throw new FasTagNotFoundException("Requested FASTag ID not found");
        }

        if (rechargeRequest.getAmount() <= 100) {
            log.warn("Recharge amount must be greater than 100");
            throw new InvalidAmountException("Amount must be greater than 100");
        }

        Wallet wallet = walletRepository.findByFastagId(rechargeRequest.getFastagId());

        wallet.setBalance(wallet.getBalance() + rechargeRequest.getAmount());

        wallet = walletRepository.save(wallet);

        log.info("Wallet {} recharged with Rs. {}",
                wallet.getId(),
                rechargeRequest.getAmount());

        return ResponseEntity.ok(wallet);
    }

    public ResponseEntity<Wallet> deduct(DeductRequest deductRequest) {

        log.info("Received request to deduct amount from wallet with FASTag ID {}",
                deductRequest.getFastagId());

        if (!walletRepository.existsByFastagId(deductRequest.getFastagId())) {
            log.warn("FASTag ID {} not found", deductRequest.getFastagId());
            throw new FasTagNotFoundException("Requested FASTag ID not found");
        }

        Wallet wallet = walletRepository.findByFastagId(deductRequest.getFastagId());

        if (wallet.getBalance() < deductRequest.getAmount()) {
            log.warn("Insufficient balance");

            throw new InsufficientBalanceException(
                    "Current Balance Rs. " + wallet.getBalance()
                            + " is less than required amount Rs. "
                            + deductRequest.getAmount());
        }

        wallet.setBalance(wallet.getBalance() - deductRequest.getAmount());

        wallet = walletRepository.save(wallet);

        log.info("Wallet {} deducted Rs. {}",
                wallet.getId(),
                deductRequest.getAmount());

        return ResponseEntity.ok(wallet);
    }

    public ResponseEntity<Wallet> getWalletByFastagId(String fastagId) {

        log.info("Received request to get wallet by FASTag ID {}", fastagId);

        if (!walletRepository.existsByFastagId(fastagId)) {
            log.warn("Wallet not found for FASTag ID {}", fastagId);
            throw new FasTagNotFoundException("Requested FASTag ID not found");
        }

        return ResponseEntity.ok(walletRepository.findByFastagId(fastagId));
    }

    public ResponseEntity<List<Wallet>> getAllWallets() {

        log.info("Received request to fetch all wallets");

        List<Wallet> wallets = walletRepository.findAll();

        log.info("Total wallets found: {}", wallets.size());

        return ResponseEntity.ok(wallets);
    }

    public ResponseEntity<String> deleteWalletById(int id) {

        log.info("Request received to delete wallet with id {}", id);

        Wallet wallet = walletRepository.findById(id).orElse(null);

        if (wallet == null) {
            log.warn("Wallet with id {} not found", id);
            throw new FasTagNotFoundException("Wallet with id " + id + " not found");
        }

        walletRepository.delete(wallet);

        log.info("Wallet deleted successfully with id {}", id);

        return ResponseEntity.ok("Wallet with id " + id + " successfully deleted");
    }
}