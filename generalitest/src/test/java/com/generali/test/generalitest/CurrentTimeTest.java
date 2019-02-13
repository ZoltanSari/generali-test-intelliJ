package com.generali.test.generalitest;

import com.generali.test.generalitest.model.CurrentTime;
import com.generali.test.generalitest.repository.CurrentTimeRepository;
import com.generali.test.generalitest.service.CurrentTimeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
public class CurrentTimeTest {

    @Configuration
    static class currentTimeServiceTestContextConfiguration {

        @Bean
        public CurrentTimeService currentTimeService() {
            return new CurrentTimeService();
        }
    }

    @Autowired
    private CurrentTimeService service;

    @MockBean
    private CurrentTimeRepository repository;

    @Before
    public void setUp() {
        CurrentTime firstCurrentTime = new CurrentTime("23:14:38.128");
        List<CurrentTime> currentTimes = Arrays.asList(
                new CurrentTime("00:00:00.000"),
                new CurrentTime("12:00:00.000")
        );

        Mockito.when(repository.findByCurrentTime(firstCurrentTime.getCurrentTime()))
                .thenReturn(firstCurrentTime);
        Mockito.when(repository.findAll()).thenReturn(currentTimes);

    }

    @Test
    public void testFindCurrentTimeByCurrentTimeIsValid() {
        String currentTime = "23:14:38.128";
        CurrentTime foundCurrentTime = service.getCurrentTimeByCurrentTime(currentTime);

        assertThat(foundCurrentTime.getCurrentTime()).isEqualTo(currentTime);
    }

    @Test
    public void testFindCurrentTimeByCurrentTimeIsInValid() {
        String currentTime = "23:14:38.123";
        CurrentTime foundCurrentTime = service.getCurrentTimeByCurrentTime(currentTime);

        assertNotEquals(foundCurrentTime, currentTime);
    }

    @Test
    public void testFindCurrentTimeByCurrentTimeReturnInvalidElement() {
        String currentTime = "time";
        CurrentTime foundCurrentTime = service.getCurrentTimeByCurrentTime(currentTime);

        assertNotEquals(foundCurrentTime, currentTime);
    }

    @Test
    public void testFindCurrentTimeByCurrentTimeIsNull() {
        assertNull(service.getCurrentTimeByCurrentTime(null));
    }

    @Test
    public void testGetAllCurrentTimeAreValid() {
        List<CurrentTime> expectedCurrentTimes = Arrays.asList(
                new CurrentTime("00:00:00.000"),
                new CurrentTime("12:00:00.000")
        );

        List<CurrentTime> currentTimes = service.getAllCurrentTime();

        assertThat(currentTimes)
                .isEqualTo(expectedCurrentTimes);
    }

    @Test
    public void testGetAllCurrentTimeAreInValid() {
        List<CurrentTime> expectedCurrentTimes = Arrays.asList(
                new CurrentTime("00:00:00.001"),
                new CurrentTime("01:00:00.000")
        );

        List<CurrentTime> currentTimes = service.getAllCurrentTime();

        assertNotEquals(currentTimes, expectedCurrentTimes);
    }

    @Test
    public void testGetAllCurrentTimeListReturnInvalidList() {
        List<CurrentTime> expectedCurrentTimes = Arrays.asList(
                new CurrentTime("current"),
                new CurrentTime("time")
        );

        List<CurrentTime> currentTimes = service.getAllCurrentTime();

        assertNotEquals(currentTimes, expectedCurrentTimes);
    }

    @Test
    public void testGetAllCurrentTimeListSizeIsValid() {
        List<CurrentTime> expectedCurrentTimes = Arrays.asList(
                new CurrentTime("00:00:00.000"),
                new CurrentTime("12:00:00.000")
        );

        List<CurrentTime> currentTimes = service.getAllCurrentTime();

        assertEquals(currentTimes.size(), expectedCurrentTimes.size());
    }

    @Test
    public void testGetAllCurrentTimeListSizeIsInValid() {
        List<CurrentTime> expectedCurrentTimes = Arrays.asList(
                new CurrentTime("00:00:00.000"),
                new CurrentTime("24:00:00.000"),
                new CurrentTime("12:00:00.000")
        );

        List<CurrentTime> currentTimes = service.getAllCurrentTime();

        assertNotEquals(currentTimes.size(), expectedCurrentTimes.size());
    }
}
