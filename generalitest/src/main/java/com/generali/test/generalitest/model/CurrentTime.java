package com.generali.test.generalitest.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "time_table")
public class CurrentTime {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, name = "actual_time")
    private String currentTime;

    @Builder
    public CurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
