package com.kishore.walletapi.controller;

import com.kishore.walletapi.dto.DeductRequest;
import com.kishore.walletapi.dto.RechargeRequest;
import com.kishore.walletapi.dto.WalletRequest;
import com.kishore.walletapi.entity.Wallet;
import com.kishore.walletapi.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping
    public ResponseEntity<List<Wallet>> getWallets() {
        return walletService.getAllWallets();
    }

    @GetMapping("/{fastagId}")
    public ResponseEntity<Wallet> getWallet(@PathVariable String fastagId) {
        return walletService.getWalletByFastagId(fastagId);
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@Valid @RequestBody WalletRequest walletRequest) {
        return walletService.createWallet(walletRequest);
    }

    @PutMapping("/recharge")
    public ResponseEntity<Wallet> recharge(@Valid @RequestBody RechargeRequest rechargeRequest) {
        return walletService.recharge(rechargeRequest);
    }

    @PutMapping("/deduct")
    public ResponseEntity<Wallet> deduct(@Valid @RequestBody DeductRequest deductRequest) {
        return walletService.deduct(deductRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWallet(@PathVariable int id) {
        return walletService.deleteWalletById(id);
    }
}