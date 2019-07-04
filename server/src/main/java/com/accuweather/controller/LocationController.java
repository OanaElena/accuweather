package com.accuweather.controller;

import com.accuweather.entity.AdminArea;
import com.accuweather.entity.Country;
import com.accuweather.entity.Region;
import com.accuweather.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    LocationService locationService;

    @RequestMapping(value="/regions", method = RequestMethod.GET)
    public List<Region> loadAllRegions(){
        return locationService.loadAllRegions();
    }

    @RequestMapping(value="/countries", method = RequestMethod.GET)
    public List<Country> loadAllCountriesFromGivenRegion(){
        return locationService.loadAllCountriesBasedOnARegion();
    }

    @RequestMapping(value="/areas", method = RequestMethod.GET)
    public List<AdminArea> loadAllAreasFromGivenCountry(){
        return locationService.loadAllAreasBasedByCountryCode();
    }

    @RequestMapping(value="/countries-from-region-specified", method = RequestMethod.GET)
    public List<Country> loadCountriesByRegionId(@RequestParam("regionCode") String regionCode){
        //call in postman http://localhost:9000/countries-from-region-specified?regionCode=ARC
        return locationService.loadAllCountriesFromSpecifiedRegionId(regionCode);
    }

    @RequestMapping(value="/areas-from-country-specified", method = RequestMethod.GET)
    public List<AdminArea> loadAreasByCountryId(@RequestParam("countryCode") String countryCode){
        //call in postman http://localhost:9000/areas-from-country-specified?countryCode=GL
        return locationService.loadAllAreasFromSpecifiedCountryId(countryCode);
    }
}
