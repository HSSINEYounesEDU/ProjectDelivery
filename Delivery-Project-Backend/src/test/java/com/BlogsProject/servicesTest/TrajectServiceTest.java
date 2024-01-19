package com.BlogsProject.servicesTest;

import com.BlogsProject.Functions.entities.Traject;
import com.BlogsProject.Functions.repositories.TrajectRepository;
import com.BlogsProject.Functions.services.TrajectService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Service
@RequiredArgsConstructor
public class TrajectServiceTest {

    @Mock
    private TrajectRepository trajectRepository;

    @InjectMocks
    private TrajectService trajectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Traject traject = new Traject();
        // Set up traject properties if needed

        trajectService.create(traject);

        verify(trajectRepository, times(1)).save(traject);
    }

    @Test
    void testFindTrajects() {
        int page = 0;
        int pageSize = 10;
        List<Traject> trajectList = Collections.singletonList(new Traject());
        Page<Traject> expectedPage = new PageImpl<>(trajectList);

        Mockito.when(trajectRepository.findAll(PageRequest.of(page, pageSize, Sort.by("source").ascending())))
                .thenReturn(expectedPage);

        Page<Traject> result = trajectService.findTrajects(page, pageSize);

        assertEquals(expectedPage, result);
        verify(trajectRepository, times(1))
                .findAll(PageRequest.of(page, pageSize, Sort.by("source").ascending()));
    }

}
