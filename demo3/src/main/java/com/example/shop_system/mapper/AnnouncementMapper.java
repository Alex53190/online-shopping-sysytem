package com.example.shop_system.mapper;

import com.example.shop_system.entity.Announcement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    @Select("SELECT * FROM announcement")
    List<Announcement> findAllAnnouncements();     // 查询所有公告
    @Insert("INSERT INTO announcement (content, create_time) VALUES (#{content}, NOW())")
    int addAnnouncement(Announcement announcement); // 添加公告
    @Delete("DELETE FROM announcement WHERE id = #{id}")
    int deleteAnnouncement(Long id);               // 删除公告
}