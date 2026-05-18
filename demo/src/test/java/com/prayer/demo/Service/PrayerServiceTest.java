package com.prayer.demo.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.prayer.demo.Repo.PrayerRepo;
import com.prayer.demo.utility.Prayer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class PrayerServiceTest {

    @Mock
    private PrayerRepo prayerRepo;

    @InjectMocks
    private PrayerService prayerService;

    @Test
    public void testGetPrayers_withPageable_returnsPage() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Prayer prayer = new Prayer();
        prayer.setNumber(1L);
        prayer.setName("Test Prayer");
        prayer.setContent("Sample content");
        Page<Prayer> mockPage = new PageImpl<>(Collections.singletonList(prayer), pageable, 1);
        when(prayerRepo.findAll(pageable)).thenReturn(mockPage);

        // Act
        Page<Prayer> result = prayerService.getPrayers(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        Prayer returned = result.getContent().get(0);
        assertEquals("Test Prayer", returned.getName());
        assertEquals("Sample content", returned.getContent());
        verify(prayerRepo, times(1)).findAll(pageable);
    }
}
