package com.example.planion_backend.participant;

import com.example.planion_backend.user.*;

import java.util.List;

import com.example.planion_backend.meeting.*;


public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final MeetingRepository meetingRepository;


    public ParticipantService(
        ParticipantRepository participantRepository,
        UserRepository userRepository,
        MeetingRepository meetingRepository
    ) {
        this.participantRepository = participantRepository;
        this.userRepository = userRepository;
        this.meetingRepository = meetingRepository;
    }

    // CREATE
    public Participant createParticipant(Participant participant, long userId, long meetingId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("Meeting not found with id: " + meetingId));    
    
        participant.setUser(user);
        participant.setMeeting(meeting);
        return participantRepository.save(participant);
    }

    // READONE
    public Participant getParticipantById(long id) {
        return participantRepository.findById(id).orElse(null);
    }

    // READALL
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    // UPDATE
    public Participant updateParticipant(long id, Participant updatedParticipant) {
        return participantRepository.findById(id)
                .map(participant -> {
                    participant.setIsCreator(updatedParticipant.isIsCreator());
                    participant.setRequestStatus(updatedParticipant.getRequestStatus());
                    participant.setRole(updatedParticipant.getRole());
                    return participantRepository.save(participant);
                })
                .orElse(null);
    }

    // DELETE
    public void deleteParticipant(long id) {
        participantRepository.deleteById(id);
    }

    // Extra methods
    public List<Participant> getParticipantsByMeetingId(long meetingId){
        
        return participantRepository.findAll().stream()
                .filter(participant -> participant.getMeeting() != null && participant.getMeeting().getId() == meetingId)
                .toList();
    }

}
