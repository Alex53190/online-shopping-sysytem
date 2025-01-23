package com.example.shop_system.service;

import com.example.shop_system.entity.Merchant;
import com.example.shop_system.mapper.MerchantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {
    @Autowired
    private  MerchantMapper merchantMapper;

    // 查询所有商家
    public List<Merchant> getAllMerchants() {
        return merchantMapper.findAllMerchants();
    }

    // 根据 ID 查询商家
    public Merchant getMerchantById(Long id) {
        return merchantMapper.findMerchantById(id);
    }

    // 添加商家
    public void addMerchant(Merchant merchant) {
        merchant.setStatus("PENDING");
        merchantMapper.addMerchant(merchant);
    }

    // 删除商家
    public void deleteMerchant(Long id) {
        merchantMapper.deleteMerchant(id);
    }

    // 更新商家状态
    public void updateMerchantStatus(Long id, String status) {
        merchantMapper.updateMerchantStatus(id, status);
    }
}