package com.BlogsProject.Functions.services;

import com.BlogsProject.Authentication.Entity.Role;
import com.BlogsProject.Authentication.Entity.User;
import com.BlogsProject.Authentication.Entity.UserRepository;
import com.BlogsProject.Functions.entities.Package;
import com.BlogsProject.Functions.entities.State;
import com.BlogsProject.Functions.entities.Traject;
import com.BlogsProject.Functions.models.PackageRequest;
import com.BlogsProject.Functions.repositories.PackageRepository;
import com.BlogsProject.Functions.repositories.TrajectRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PackageService {

    private final PackageRepository packageRepository;
    private final TrajectRepository trajectRepository;
    private final UserRepository userRepository;

    public void create(Package pack){
        packageRepository.save(pack);
    }

    public void create(PackageRequest request) throws Exception{
        Optional<User> user = userRepository.findUserByEmail(request.getEmail());
        Optional<Traject> trj = trajectRepository.findById(request.getTrajectId());
        if(user.isPresent()){
            if(user.get().getRole().name().equals(Role.CLIENT.name())){
                Package pck = Package.builder()
                        .weight(request.getWeight())
                        .description(request.getDescription())
                        .traject(trj.get())
                        .address(request.getAddress())
                        .creationDate(new Date())
                        .client(user.get())
                        .build();
                packageRepository.save(pck);
                String ref = "ref" + pck.getId();
                pck.setReference(ref);
                pck.setPresentLocation(pck.getTraject().getSource());
                packageRepository.save(pck);

            }
            else{
                throw new Exception("This is an employee account.");
            }
        }
        else{
            throw new Exception("Non existing user.");
        }


    }

    public void update(Package pack){
        packageRepository.save(pack);
    }

    public void delete(Package pack){
        packageRepository.delete(pack);
    }

    public Package findById(int id){
        return packageRepository.findById(id).get();
    }

    public Page<Package> findInProgressByPackageReference(String reference, int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        if(reference.equals("all")){
            return packageRepository.findAllInProgress(pageable);
        }
        else{
            return packageRepository.findInProgressByReference(reference, pageable);
        }
    }

    public Page<Package> findInProgressOnCkeckpoints(String reference, int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        if(reference.equals("all")){
            return packageRepository.findAllInProgressInCheckpoints(State.ONCHECKPOINT, pageable);
        }
        else{
            return packageRepository.findInProgressInCheckpoints(reference, State.ONCHECKPOINT, pageable);
        }
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




                            //    SERVICES FOR CLIENT APIS
    public Page<Package> findAll_InProgress(int page, int pageSize, String uid){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        return packageRepository.findAll_InProgress(uid, pageable);
    }

    public Page<Package> findByReference_InProgress(String reference, int page, int pageSize, String uid){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        return packageRepository.findByReference_InProgress(reference, uid,  pageable);
    }

    public Page<Package> findAll_Delivered(int page, int pageSize, String uid){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        return packageRepository.findAll_Delivered(uid, pageable);
    }

    public Page<Package> findByReference_Delivered(String reference, int page, int pageSize, String uid){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        return packageRepository.findByReference_Delivered(reference, uid,  pageable);
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
}
