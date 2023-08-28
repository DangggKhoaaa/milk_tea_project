package com.example.hudamilktea.service;

import com.example.hudamilktea.model.LocationRegion;
import com.example.hudamilktea.model.Staff;
import com.example.hudamilktea.repository.LocationRegionRepository;
import com.example.hudamilktea.repository.StaffRepository;
import com.example.hudamilktea.service.DTO.LocationRegionRequest;
import com.example.hudamilktea.service.DTO.ProfileStaffSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final LocationRegionRepository locationRegionRepository;
    public List<Staff> findAll(){
        return staffRepository.findAll();
    }
    public void editProfileStaff(ProfileStaffSaveRequest request, Long staffID){
        Staff staff = staffRepository.findById(staffID).orElseThrow(() -> new StaffNotFoundException("Staff not found"));
        staff.setFullName(request.getFullName());
        staff.setEmail(request.getEmail());
        staff.setPhone(request.getPhone());
        staff.setAge(Long.parseLong(request.getAge()));

        LocationRegionRequest locationRegionSaveRequest = request.getLocationRegion();
//        LocationRegion locationRegion = new LocationRegion();
//        locationRegion.setId(null);
//        locationRegion.setDistrictId(locationRegionSaveRequest.getDistrictId());
//        locationRegion.setDistrictName(locationRegionSaveRequest.getDistrictName());
//        locationRegion.setProvinceId(locationRegionSaveRequest.getProvinceId());
//        locationRegion.setProvinceName(locationRegionSaveRequest.getProvinceName());
//        locationRegion.setWardId(locationRegionSaveRequest.getWardId());
//        locationRegion.setWardName(locationRegionSaveRequest.getWardName());
//        locationRegion.setAddress(locationRegionSaveRequest.getAddress());
//
//        locationRegion = locationRegionRepository.save(locationRegion);
//        staff.setLocationRegion(locationRegion);
//        staffRepository.save(staff);
        LocationRegion locationRegion = staff.getLocationRegion();
        locationRegion.setDistrictId(locationRegionSaveRequest.getDistrictId());
        locationRegion.setDistrictName(locationRegionSaveRequest.getDistrictName());
        locationRegion.setProvinceId(locationRegionSaveRequest.getProvinceId());
        locationRegion.setProvinceName(locationRegionSaveRequest.getProvinceName());
        locationRegion.setWardId(locationRegionSaveRequest.getWardId());
        locationRegion.setWardName(locationRegionSaveRequest.getWardName());
        locationRegion.setAddress(locationRegionSaveRequest.getAddress());

        locationRegionRepository.save(locationRegion);
        staffRepository.save(staff);
    }
    public void deleteStaff(Long staffID){
        Staff staff = staffRepository.findById(staffID).orElseThrow(() -> new StaffNotFoundException("Staff not found"));
        LocationRegion locationRegion = staffRepository.findById(staffID).orElseThrow(() -> new StaffNotFoundException("Staff not found")).getLocationRegion();

        staffRepository.delete(staff);
        locationRegionRepository.delete(locationRegion);
    }

    public Boolean loadStaffByUsername(String staffName) {
        return staffRepository.existsByStaffName(staffName);
    }
}
