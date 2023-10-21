package com.mediumScrape.MediumScrape.Service;

import com.mediumScrape.MediumScrape.Model.ResponseDTO;

import java.util.Set;

public interface ScraperService {

    Set<ResponseDTO> getVehicleByModel(String vehicleModel);
}