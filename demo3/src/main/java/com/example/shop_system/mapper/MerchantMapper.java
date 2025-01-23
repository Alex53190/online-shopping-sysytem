package com.example.shop_system.mapper;

import com.example.shop_system.entity.Merchant;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MerchantMapper {
    @Select("SELECT * FROM merchant")
    List<Merchant> findAllMerchants();                   // 查询所有商家
    @Select("SELECT * FROM merchant WHERE id = #{id}")
    Merchant findMerchantById(Long id);                 // 根据 ID 查询商家
    @Insert("INSERT INTO merchant (user_id, merchant_name, contact_info, status) VALUES (#{userId}, #{merchantName}, #{contactInfo}, #{status})")
    int addMerchant(Merchant merchant);                 // 添加商家
    @Delete("DELETE FROM merchant WHERE id = #{id}")
    int deleteMerchant(Long id);                        // 删除商家
    @Update(" UPDATE merchant SET status = #{status} WHERE id = #{id}")
    int updateMerchantStatus(@Param("id") Long id,
                             @Param("status") String status); // 更新商家状态
}