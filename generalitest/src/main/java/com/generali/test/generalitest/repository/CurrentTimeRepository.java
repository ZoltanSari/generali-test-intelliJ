package com.generali.test.generalitest.repository;

import com.generali.test.generalitest.model.CurrentTime;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CurrentTimeRepository extends JpaRepository<CurrentTime, Long> {

    CurrentTime findByCurrentTime(String currentTime);

    List<CurrentTime> findAll();
}
