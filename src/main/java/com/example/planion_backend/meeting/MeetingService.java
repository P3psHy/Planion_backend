package com.example.planion_backend.meeting;

import java.util.List;

public class MeetingService {
    
    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    // CREATE
    public Meeting createMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    // READONE
    public Meeting getMeetingById(Long id) {
        return meetingRepository.findById(id).orElse(null);
    }

    // READALL
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    // UPDATE
    public Meeting updateMeeting(Long id, Meeting updatedMeeting) {
        return meetingRepository.findById(id)
                .map(meeting -> {
                    meeting.setTitle(updatedMeeting.getTitle());
                    meeting.setDescription(updatedMeeting.getDescription());
                    meeting.setStartTime(updatedMeeting.getStartTime());
                    meeting.setEndTime(updatedMeeting.getEndTime());
                    meeting.setCreationDate(updatedMeeting.getCreationDate());
                    return meetingRepository.save(meeting);
                })
                .orElse(null);
    }

    // DELETE
    public void deleteMeeting(Long id) {
        meetingRepository.deleteById(id);
    }

    // Extra methods

    // public List<User> getParticipantsByMeetingId(Long meetingId) {
    // }

}
