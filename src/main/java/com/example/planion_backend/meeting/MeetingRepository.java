package com.example.planion_backend.meeting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface MeetingRepository extends JpaRepository<Meeting, Long>{    
}
