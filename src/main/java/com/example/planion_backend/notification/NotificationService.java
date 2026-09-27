package com.example.planion_backend.notification;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.planion_backend.meeting.*;
import com.example.planion_backend.user.*;


@Service 
public class NotificationService {
    
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final MeetingRepository meetingRepository;


    public NotificationService(
        NotificationRepository notificationRepository,
        UserRepository userRepository,
        MeetingRepository meetingRepository
    ) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.meetingRepository = meetingRepository;
    }


    // CREATE
    public Notification createNotification(
        Notification notification,
        Long userId,
        Long meetingId
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("Meeting not found with id: " + meetingId));

        notification.setUser(user);
        notification.setMeeting(meeting);

        return notificationRepository.save(notification);
    }

    // READONE
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    // READALL
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // UPDATE
    public Notification updateNotification(Long id, Notification updatedNotification) {
        return notificationRepository.findById(id)
                .map(notification -> {
                    notification.setTitle(updatedNotification.getTitle());
                    notification.setDescription(updatedNotification.getDescription());
                    notification.setSendedDate(updatedNotification.getSendedDate());
                    return notificationRepository.save(notification);
                })
                .orElse(null);
    }

    // DELETE
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    // Extra methods
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findAll().stream()
                .filter(notification -> notification.getUser() != null && notification.getUser().getId().equals(userId))
                .toList();
    }

}
