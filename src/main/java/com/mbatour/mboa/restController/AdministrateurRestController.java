package com.mbatour.mboa.restController;


import com.mbatour.mboa.DTO.AdminInfoDTO;
import com.mbatour.mboa.repository.AdministrateurRepository;
import com.mbatour.mboa.service.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdministrateurRestController {
    @Autowired
    private AdministrateurService administrateurService;
    @GetMapping("/adminDashboard/{id}")
    public AdminInfoDTO adminDashboard(@PathVariable("id") Long admId) {
        return administrateurService.getAdminInfo(admId);
    }
}
