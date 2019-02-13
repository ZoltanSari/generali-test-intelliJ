package com.generali.test.generalitest.service;

import com.generali.test.generalitest.model.CurrentTime;
import com.generali.test.generalitest.repository.CurrentTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentTimeService {

    @Autowired
    private CurrentTimeRepository repository;

    public void addCurrentTimeToDB(String currentTime) {
        CurrentTime newCurrentTime = CurrentTime.builder().currentTime(currentTime).build();
        repository.save(newCurrentTime);
    }

    public CurrentTime getCurrentTimeByCurrentTime(String currentTime) {
        return repository.findByCurrentTime(currentTime);
    }

    public List<CurrentTime> getAllCurrentTime() {
        System.out.println(repository.findAll());
        return repository.findAll();
    }
}
