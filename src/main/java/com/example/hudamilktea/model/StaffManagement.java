package com.example.hudamilktea.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffManagement {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    private LocalDateTime attendanceStartTime;
    private LocalDateTime attendanceEndTime;
    private double salary;
}
