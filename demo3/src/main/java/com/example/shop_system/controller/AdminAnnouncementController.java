package com.example.shop_system.controller;

import com.example.shop_system.entity.Announcement;
import com.example.shop_system.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/announcements")
@CrossOrigin(origins = "http://localhost:8086", allowCredentials = "true", maxAge = 3600)
@RequiredArgsConstructor
public class AdminAnnouncementController {
    @Autowired
    private  AnnouncementService announcementService;

    // 查询所有公告
    @GetMapping
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        return ResponseEntity.ok(announcementService.getAllAnnouncements());
    }

    // 添加公告
    @PostMapping
    public ResponseEntity<String> addAnnouncement(@RequestBody Announcement announcement) {
        announcementService.addAnnouncement(announcement);
        return ResponseEntity.ok("Announcement added successfully");
    }

    // 删除公告
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.ok("Announcement deleted successfully");
    }
}