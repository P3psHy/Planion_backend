package com.example.planion_backend.participant;

import com.example.planion_backend.meeting.Meeting;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.example.planion_backend.user.User;

@Entity
@Table(name = "participants")
public class Participant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean isCreator;
    private String requestStatus;
    private String role;
    // Relations

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    
    public long getId() {
        return this.id;
    }

    public boolean isIsCreator() {
        return this.isCreator;
    }

    public void setIsCreator(boolean isCreator) {
        this.isCreator = isCreator;
    }

    public String getRequestStatus() {
        return this.requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
