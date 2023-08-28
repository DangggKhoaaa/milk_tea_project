package com.example.hudamilktea.api;

import com.example.hudamilktea.model.Staff;
import com.example.hudamilktea.service.RegisterStaffService;
import com.example.hudamilktea.service.StaffManagementService;
import com.example.hudamilktea.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff-management")
public class StaffManagementRestController {
    @Autowired
    private StaffManagementService staffManagementService;
    private StaffService staffService;
    private RegisterStaffService registerStaffService;

    @PostMapping("/check-in")
    public ResponseEntity<?> checkIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();

        Staff staff = staffService.loadStaffByUsername(loggedInUsername);

        staffManagementService.checkIn(staff);

        return ResponseEntity.ok("Check-in successful");
    }
    @PostMapping("/check-out")
    public ResponseEntity<String> checkOut(@RequestBody Staff staff) {
        staffManagementService.checkOut(staff);
        return ResponseEntity.ok("Checked out successfully");
    }
}
