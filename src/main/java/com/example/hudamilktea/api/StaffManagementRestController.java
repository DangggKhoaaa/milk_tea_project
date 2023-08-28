package com.example.hudamilktea.api;

import com.example.hudamilktea.model.Staff;
import com.example.hudamilktea.service.RegisterStaffService;
import com.example.hudamilktea.service.StaffManagementService;
import com.example.hudamilktea.service.StaffService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff-management")
public class StaffManagementRestController {
    @Autowired
    private StaffManagementService staffManagementService;
    private StaffService staffService;
    private RegisterStaffService registerStaffService;

//    @PostMapping("/check-in")
//    public ResponseEntity<?> checkIn() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String loggedInUsername = authentication.getName();
//
//        boolean staff = staffService.loadStaffByUsername(loggedInUsername);
//
//        staffManagementService.checkIn(staff);
//
//        return ResponseEntity.ok("Check-in successful");
//    }
}
