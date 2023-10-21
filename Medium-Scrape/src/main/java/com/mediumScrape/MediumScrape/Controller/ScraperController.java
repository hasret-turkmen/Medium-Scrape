package com.mediumScrape.MediumScrape.Controller;

import com.mediumScrape.MediumScrape.Model.ResponseDTO;
import com.mediumScrape.MediumScrape.Service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "/car")
public class ScraperController {

    @Autowired
    ScraperService scraperService;

    //localhost:8080/car/range-rover
    @GetMapping(path = "/{vehicleModel}")
    public Set<ResponseDTO> getVehicleByModel(@PathVariable String vehicleModel) {
        return  scraperService.getVehicleByModel(vehicleModel);
    }
}