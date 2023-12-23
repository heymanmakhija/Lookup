package com.example.location.Controller;

import com.example.location.LocationResponse;
import com.example.location.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/fetch")
    public String fetchAddress(@RequestParam("ipAddress") String ipAddress, Model model) throws IOException {
        LocationResponse locationResponse = locationService.findLocationByIp(ipAddress);
        model.addAttribute("locationResponse", locationResponse);
        return "index";
    }

}
