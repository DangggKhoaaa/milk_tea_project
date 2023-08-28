package com.example.hudamilktea.service;

import com.example.hudamilktea.model.LocationRegion;
import com.example.hudamilktea.model.Staff;
import com.example.hudamilktea.model.enums.Role;
import com.example.hudamilktea.repository.LocationRegionRepository;
import com.example.hudamilktea.repository.StaffRepository;
import com.example.hudamilktea.service.DTO.LocationRegionRequest;
import com.example.hudamilktea.service.DTO.ProfileStaffSaveRequest;
import com.example.hudamilktea.service.DTO.StaffSaveRequest;
import com.example.hudamilktea.util.AppUtils;
import lombok.AllArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RegisterStaffService implements UserDetailsService {
    private final StaffRepository staffRepository;
    private final LocationRegionRepository locationRegionRepository;

    private final PasswordEncoder passwordEncoder;

    public List<Staff> findAll(){
        return staffRepository.findAll();
    }

//    public void register(StaffSaveRequest request){
//        var staff = AppUtils.mapper.map(request, Staff.class);
//        staff.setRole(Role.ROLE_USER);
//        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
//        staffRepository.save(staff);
//    }


    public void register( StaffSaveRequest request) {
        var staff = AppUtils.mapper.map(request, Staff.class);
        staff.setStaffName(staff.getStaffName());
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        staff.setRole(Role.ROLE_USER);

        LocationRegionRequest locationRegionSaveRequest = request.getLocationRegion();
        LocationRegion locationRegion = new LocationRegion();
        locationRegion.setId(null);
        locationRegion.setDistrictId(locationRegionSaveRequest.getDistrictId());
        locationRegion.setDistrictName(locationRegionSaveRequest.getDistrictName());
        locationRegion.setProvinceId(locationRegionSaveRequest.getProvinceId());
        locationRegion.setProvinceName(locationRegionSaveRequest.getProvinceName());
        locationRegion.setWardId(locationRegionSaveRequest.getWardId());
        locationRegion.setWardName(locationRegionSaveRequest.getWardName());
        locationRegion.setAddress(locationRegionSaveRequest.getAddress());

        locationRegion = locationRegionRepository.save(locationRegion);
        staff.setLocationRegion(locationRegion);
        staffRepository.save(staff);
    }
    public boolean CheckStaffNameOrPhoneOrEmail(StaffSaveRequest request, BindingResult result){
        boolean check = false;
        if (staffRepository.existsByStaffName(request.getStaffName())){
            result.reject("staffname",null,"Tài Khoản đã tồn tại, vui lòng nhập tài khoản mới");
            check = true;
        }
        if (staffRepository.existsByEmailIgnoreCase(request.getEmail())){
            result.reject("email", null, "Email đã tồn tài, xin vui lòng tạo email mới");
            check = true;
        }
        if (staffRepository.existsByPhone(request.getPhone())){
            result.reject("phoneNumber", null,
                    "There is already ");
            check = true;
        }
        return check;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff = staffRepository.findByStaffNameIgnoreCaseOrEmailIgnoreCaseOrPhone(username,username,username).orElse(null);
        if (staff == null){
            throw new UsernameNotFoundException("Tài khoản không tồn tài, cút");
        }
        var role = new ArrayList<SimpleGrantedAuthority>();
        role.add(new SimpleGrantedAuthority(staff.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(staff.getStaffName(),staff.getPassword(),role);
    }


}
