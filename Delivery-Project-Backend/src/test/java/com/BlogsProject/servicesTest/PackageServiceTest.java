package com.BlogsProject.servicesTest;

import com.BlogsProject.Authentication.Entity.Role;
import com.BlogsProject.Authentication.Entity.User;
import com.BlogsProject.Authentication.Entity.UserRepository;
import com.BlogsProject.Functions.entities.Package;
import com.BlogsProject.Functions.entities.State;
import com.BlogsProject.Functions.entities.Traject;
import com.BlogsProject.Functions.models.PackageRequest;
import com.BlogsProject.Functions.repositories.PackageRepository;
import com.BlogsProject.Functions.repositories.TrajectRepository;
import com.BlogsProject.Functions.services.PackageService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Service
@RequiredArgsConstructor
public class PackageServiceTest {

    private PackageRepository packageRepository;
    private TrajectRepository trajectRepository;
    private UserRepository userRepository;
    private PackageService packageService;

    @BeforeEach
    void setUp() {
        packageRepository = Mockito.mock(PackageRepository.class);
        trajectRepository = mock(TrajectRepository.class);
        userRepository = mock(UserRepository.class);
        packageService = new PackageService(packageRepository, trajectRepository, userRepository);
    }

    @Test
    void testCreatePackageRequest() throws Exception {
        String userEmail = "client@example.com";
        int trajectId = 1;
        User user = new User();
        user.setRole(Role.CLIENT);

        Traject traject = new Traject();

        PackageRequest request = new PackageRequest();
        request.setEmail(userEmail);
        request.setTrajectId(trajectId);

        when(userRepository.findUserByEmail(userEmail)).thenReturn(Optional.of(user));
        when(trajectRepository.findById(trajectId)).thenReturn(Optional.of(traject));

        packageService.create(request);

        verify(packageRepository, times(2)).save(any(Package.class)); // Expecting two invocations now
    }
    @Test
    void testUpdatePackage() {
        Package pack = new Package();
        packageService.update(pack);
        verify(packageRepository, times(1)).save(pack);
    }

    @Test
    void testDeletePackage() {
        Package pack = new Package();

        packageService.delete(pack);

        verify(packageRepository, times(1)).delete(pack);
    }

    @Test
    void testFindById() {
        int id = 1;
        Package expectedPackage = new Package();

        when(packageRepository.findById(id)).thenReturn(Optional.of(expectedPackage));

        Package actualPackage = packageService.findById(id);

        assertEquals(expectedPackage, actualPackage);
        verify(packageRepository, times(1)).findById(id);
    }

    @Test
    void testFindInProgressByPackageReferenceAll() {
        String reference = "all";
        int page = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        Page<Package> expectedPage = mock(Page.class);
        when(packageRepository.findAllInProgress(pageable)).thenReturn(expectedPage);

        Page<Package> result = packageService.findInProgressByPackageReference(reference, page, pageSize);

        verify(packageRepository, times(1)).findAllInProgress(pageable);
        assertEquals(expectedPage, result);
    }

    @Test
    void testFindInProgressByPackageReferenceSpecific() {
        String reference = "specificReference";
        int page = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());

        Page<Package> actualPage = packageService.findInProgressByPackageReference(reference, page, pageSize);

        verify(packageRepository, times(1)).findInProgressByReference(reference, pageable);
    }

    @Test
    void testFindPackageById() {
        int id = 1;
        Package pack = new Package();
        when(packageRepository.findById(id)).thenReturn(Optional.of(pack));
        Package found = packageService.findById(id);
        assertEquals(pack, found);
    }

    public Page<Package> findDeliveredByPackageReference(String reference, int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        if(reference.equals("all")){
            return packageRepository.findAllDelivered(pageable);
        }
        else{
            return packageRepository.findDeliveredByReference(reference, pageable);
        }
    }
     @Test
     void testFindAll_InProgress() {
         String uid = "user123";
         int page = 0;
         int pageSize = 10;
         Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());

         List<Package> packageList = Collections.singletonList(new Package());
         Page<Package> expectedPage = new PageImpl<>(packageList, pageable, packageList.size());

         when(packageRepository.findAll_InProgress(uid, pageable)).thenReturn(expectedPage);

         Page<Package> resultPage = packageService.findAll_InProgress(page, pageSize, uid);

         assertEquals(expectedPage, resultPage);
     }

    @Test
    void testFindByReference_InProgress() {
        String reference = "ref123";
        String uid = "user123";
        int page = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());

        List<Package> packageList = Collections.singletonList(new Package());
        Page<Package> expectedPage = new PageImpl<>(packageList, pageable, packageList.size());

        when(packageRepository.findByReference_InProgress(reference, uid, pageable)).thenReturn(expectedPage);

        Page<Package> resultPage = packageService.findByReference_InProgress(reference, page, pageSize, uid);

        assertEquals(expectedPage, resultPage);
    }

    @Test
    void testFindAll_Delivered() {
        String uid = "user123";
        int page = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());

        List<Package> packageList = Collections.singletonList(new Package());
        Page<Package> expectedPage = new PageImpl<>(packageList, pageable, packageList.size());

        when(packageRepository.findAll_Delivered(uid, pageable)).thenReturn(expectedPage);

        Page<Package> resultPage = packageService.findAll_Delivered(page, pageSize, uid);

        assertEquals(expectedPage, resultPage);
    }

    @Test
    void testFindByReference_Delivered() {
        String reference = "ref123";
        String uid = "user123";
        int page = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());

        List<Package> packageList = Collections.singletonList(new Package());
        Page<Package> expectedPage = new PageImpl<>(packageList, pageable, packageList.size());

        when(packageRepository.findByReference_Delivered(reference, uid, pageable)).thenReturn(expectedPage);

        Page<Package> resultPage = packageService.findByReference_Delivered(reference, page, pageSize, uid);

        assertEquals(expectedPage, resultPage);
    }

    public Package getPackage(String uid, int id) throws Exception{
        Package p = packageRepository.getReferenceById(id);
        if(p.getClient().getUid().equals(uid)){
            return p;
        }
        else{
            throw new Exception("Access denied.");
        }
    }
    @Test
    void testGetPackage_Success() throws Exception {
        int packageId = 1;
        String userId = "user123";
        Package mockPackage = new Package();
        User mockUser = new User();
        mockUser.setUid(userId);
        mockPackage.setClient(mockUser);

        when(packageRepository.getReferenceById(packageId)).thenReturn(mockPackage);

        Package result = packageService.getPackage(userId, packageId);

        assertEquals(mockPackage, result);
    }
    @Test
    void testGetPackage_AccessDenied() {
        int packageId = 1;
        String userId = "user123";
        String differentUserId = "user456";
        Package mockPackage = new Package();
        User mockUser = new User();
        mockUser.setUid(differentUserId);
        mockPackage.setClient(mockUser);

        when(packageRepository.getReferenceById(packageId)).thenReturn(mockPackage);

        Exception exception = assertThrows(Exception.class, () -> {
            packageService.getPackage(userId, packageId);
        });
        assertEquals("Access denied.", exception.getMessage());
    }
}
