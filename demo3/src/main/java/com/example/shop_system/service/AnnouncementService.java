package com.example.shop_system.service;

import com.example.shop_system.entity.Announcement;
import com.example.shop_system.mapper.AnnouncementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    @Autowired
    private  AnnouncementMapper announcementMapper;

    // 查询所有公告
    public List<Announcement> getAllAnnouncements() {
        return announcementMapper.findAllAnnouncements();
    }

    // 添加公告
    public void addAnnouncement(Announcement announcement) {
        announcementMapper.addAnnouncement(announcement);
    }

    // 删除公告
    public void deleteAnnouncement(Long id) {
        announcementMapper.deleteAnnouncement(id);
    }
}