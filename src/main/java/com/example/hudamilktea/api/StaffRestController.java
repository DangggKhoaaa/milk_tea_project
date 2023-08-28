package com.example.hudamilktea.api;

import com.example.hudamilktea.model.Staff;
import com.example.hudamilktea.repository.LocationRegionRepository;
import com.example.hudamilktea.repository.StaffRepository;
import com.example.hudamilktea.service.DTO.ProfileStaffSaveRequest;
import com.example.hudamilktea.service.DTO.StaffSaveRequest;
import com.example.hudamilktea.service.RegisterStaffService;
import com.example.hudamilktea.service.StaffNotFoundException;
import com.example.hudamilktea.service.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staffs")
@AllArgsConstructor
public class StaffRestController {
    private final StaffService staffService;
    private final StaffRepository staffRepository;
    private final RegisterStaffService registerStaffService;
    private final LocationRegionRepository locationRegionRepository;

    @GetMapping
    public List<Staff> findAll(){
        return staffService.findAll();
    }
    @GetMapping("/profileStaff/{staffId}")
    public ResponseEntity<Optional<Staff>> getStaffProfile(@PathVariable Long staffId) {
        Optional<Staff> staff = staffRepository.findById(staffId);

        if (staff != null) {
            return ResponseEntity.ok(staff);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> createStaff(@RequestBody StaffSaveRequest request){
        try {
            registerStaffService.register(request);
            return ResponseEntity.ok("User quiz submission saved successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving user quiz submission.");
        }
    }

    @PutMapping("/edit-profile/{staffId}")
    public ResponseEntity<String> editProfileStaff(@RequestBody ProfileStaffSaveRequest request, @PathVariable Long staffId
    ) {
        try {
            staffService.editProfileStaff(request, staffId);
            return ResponseEntity.ok("Profile updated successfully.");
        } catch (StaffNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Staff not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    @DeleteMapping("/delete/{staffID}")
    public ResponseEntity<String> deleteStaff(@PathVariable Long staffID) {
        try {
            staffService.deleteStaff(staffID);
            return new ResponseEntity<>("Staff deleted successfully", HttpStatus.OK);
        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>("Staff not found", HttpStatus.NOT_FOUND);
        }
    }
}
