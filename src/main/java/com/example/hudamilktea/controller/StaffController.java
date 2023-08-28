package com.example.hudamilktea.controller;

import ch.qos.logback.core.model.Model;
import com.example.hudamilktea.model.Staff;
import com.example.hudamilktea.repository.LocationRegionRepository;
import com.example.hudamilktea.repository.StaffRepository;
import com.example.hudamilktea.service.DTO.StaffSaveRequest;
import com.example.hudamilktea.service.RegisterStaffService;
import com.example.hudamilktea.service.StaffNotFoundException;
import com.example.hudamilktea.service.StaffService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@AllArgsConstructor
@Controller
@RequestMapping("/staff")
public class StaffController {
    private StaffService staffService;
    private StaffRepository staffRepository;
    private RegisterStaffService registerStaffService;
    private LocationRegionRepository locationRegionRepository;
    @GetMapping
    public ModelAndView showStaff(){
        ModelAndView view = new ModelAndView();
        Staff staff = new Staff();
        view.addObject("staff",staff);
        view.addObject("locationRegion",locationRegionRepository.findAll());
        return view;
    }
//    @GetMapping("/profileStaff")
//    public ModelAndView showProfileStaff(){
//            ModelAndView view = new ModelAndView("profileStaff");
//            Staff staff = new Staff();
//            view.addObject("staff",staff);
//            view.addObject("locationRegion",locationRegionRepository.findAll());
//            return view;
//
//    }

    @GetMapping("/profileStaff/{id}")
    public ModelAndView showProfileStaff(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("profileStaff");
        Staff staff = staffRepository.findById(id).orElse(null);
//        if (staff == null) {
//            view.setViewName("errorPage");
//            view.addObject("errorMessage", "Staff not found");
//        }
//
//
//        view.addObject("staff", staff);
//        view.addObject("locationRegion", locationRegionRepository.findAll());
        if (staff == null) {
            view.setViewName("errorPage");
            view.addObject("errorMessage", "Staff not found");
        } else {
            view.setViewName("profileStaff");
            view.addObject("staff", staff);
            view.addObject("locationRegion", locationRegionRepository.findAll());
        }
        return view;
    }



    @GetMapping("/registerStaff")
    public ModelAndView showRegistrationStaffForm(){

        ModelAndView view = new ModelAndView("registerStaff");
        StaffSaveRequest staff = new StaffSaveRequest();
        view.addObject("staff", staff);
        return view;
    }
    @PostMapping("/registerStaff")
    public String registrationStaff(@Valid @ModelAttribute("staff") StaffSaveRequest request,
                                    BindingResult result, Model model){
        registerStaffService.CheckStaffNameOrPhoneOrEmail(request,result);
        if (result.hasErrors()){
            return "/registerStaff";
        }
        registerStaffService.register(request);
        return "redirect:/registerStaff?success";
    }
}
