package com.example.shop_system.controller;

import com.example.shop_system.entity.Merchant;
import com.example.shop_system.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/merchants")
@CrossOrigin(origins = "http://localhost:8086", allowCredentials = "true", maxAge = 3600)
@RequiredArgsConstructor
public class AdminMerchantController {
    @Autowired
    private  MerchantService merchantService;

    // 查询所有商家
    @GetMapping
    public ResponseEntity<List<Merchant>> getAllMerchants() {
        return ResponseEntity.ok(merchantService.getAllMerchants());
    }

    // 根据 ID 查询商家
    @GetMapping("/{id}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable Long id) {
        return ResponseEntity.ok(merchantService.getMerchantById(id));
    }

    // 添加商家
    @PostMapping
    public ResponseEntity<String> addMerchant(@RequestBody Merchant merchant) {
        if(merchant.getMerchantName()!=null&&merchant.getContactInfo()!=null&&merchant.getId()!=null&&merchant.getStatus()!=null&&merchant.getUserId()!=null)
        merchantService.addMerchant(merchant);
        return ResponseEntity.ok("Merchant added successfully");
    }

    // 删除商家
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMerchant(@PathVariable Long id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.ok("Merchant deleted successfully");
    }

    // 更新商家状态
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateMerchantStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        merchantService.updateMerchantStatus(id, status);
        return ResponseEntity.ok("Merchant status updated successfully");
    }
}