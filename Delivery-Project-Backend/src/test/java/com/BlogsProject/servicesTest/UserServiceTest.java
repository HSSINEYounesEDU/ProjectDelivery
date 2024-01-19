package com.BlogsProject.servicesTest;

import com.BlogsProject.Authentication.Entity.Role;
import com.BlogsProject.Authentication.Entity.User;
import com.BlogsProject.Authentication.Entity.UserRepository;
import com.BlogsProject.Functions.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetClientsByName_All() {
        int page = 0;
        int pageSize = 10;
        String name = "all";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("firstname").ascending());
        Page<User> expectedPage = mock(Page.class);
        when(userRepository.findAllUsers(pageable, Role.CLIENT)).thenReturn(expectedPage);

        Page<User> result = userService.getClientsByName(page, pageSize, name);

        verify(userRepository, times(1)).findAllUsers(pageable, Role.CLIENT);
        assertEquals(expectedPage, result);
    }

    @Test
    void testGetClientsByName_SpecificName() {

        int page = 0;
        int pageSize = 10;
        String name = "John";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("firstname").ascending());
        Page<User> expectedPage = mock(Page.class);
        when(userRepository.findUsersByName(name, pageable, Role.CLIENT)).thenReturn(expectedPage);

        Page<User> result = userService.getClientsByName(page, pageSize, name);

        verify(userRepository, times(1)).findUsersByName(name, pageable, Role.CLIENT);
        assertEquals(expectedPage, result);
    }

    @Test
    void testGetCheckpointssByName_All() {
        int page = 0;
        int pageSize = 10;
        String name = "all";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("firstname").ascending());
        Page<User> expectedPage = mock(Page.class);
        when(userRepository.findAllUsers(pageable, Role.CHECKPOINT)).thenReturn(expectedPage);

        Page<User> result = userService.getCheckpointssByName(page, pageSize, name);

        verify(userRepository, times(1)).findAllUsers(pageable, Role.CHECKPOINT);
        assertEquals(expectedPage, result);
    }

    @Test
    void testGetCheckpointssByName_SpecificName() {
        int page = 0;
        int pageSize = 10;
        String name = "Jane";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("firstname").ascending());
        Page<User> expectedPage = mock(Page.class);
        when(userRepository.findUsersByName(name, pageable, Role.CHECKPOINT)).thenReturn(expectedPage);

        Page<User> result = userService.getCheckpointssByName(page, pageSize, name);

        verify(userRepository, times(1)).findUsersByName(name, pageable, Role.CHECKPOINT);
        assertEquals(expectedPage, result);
    }

    @Test
    void testGetAdminsByName_All() {

        int page = 0;
        int pageSize = 10;
        String name = "all";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("firstname").ascending());
        Page<User> expectedPage = mock(Page.class);
        when(userRepository.findAllUsers(pageable, Role.ADMIN)).thenReturn(expectedPage);

        Page<User> result = userService.getAdminsByName(page, pageSize, name);

        verify(userRepository, times(1)).findAllUsers(pageable, Role.ADMIN);
        assertEquals(expectedPage, result);
    }

    @Test
    void testGetAdminsByName_SpecificName() {
        int page = 0;
        int pageSize = 10;
        String name = "SpecificName";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("firstname").ascending());
        Page<User> expectedPage = mock(Page.class);
        when(userRepository.findUsersByName(name, pageable, Role.ADMIN)).thenReturn(expectedPage);

        Page<User> result = userService.getAdminsByName(page, pageSize, name);

        verify(userRepository, times(1)).findUsersByName(name, pageable, Role.ADMIN);
        assertEquals(expectedPage, result);
    }
}
