package com.accuweather.service;

import com.accuweather.entity.AdminArea;
import com.accuweather.entity.Country;
import com.accuweather.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Value("${accuweather.root}")
    String accuweatherURL;

    @Value("${apikey}")
    String apikey;


    @Autowired
    RestTemplate restTemplate;

    public List<Region> loadAllRegions(){
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(accuweatherURL + "/locations/v1/regions")
                .queryParam("apikey", apikey);

        ResponseEntity<List<Region>> response = restTemplate.exchange(
                uriBuilder.build().encode().toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Region>>() {
                });
        return response.getBody();
    }

    public List<Country> loadAllCountriesBasedOnARegion(){
        List<Country> countriesList = new ArrayList<>();
        Optional<Region> region = loadAllRegions().stream().findFirst();

        if(region.isPresent()){
            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .fromHttpUrl(accuweatherURL + "/locations/v1/countries")
                    .queryParam("apikey", apikey)
                    .queryParam("regionCode", region.get().getId());

            ResponseEntity<List<Country>> response = restTemplate.exchange(
                    uriBuilder.build().encode().toUri(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Country>>() {
                    });

            countriesList = response.getBody();
        }
        return countriesList;
    }


    public List<AdminArea> loadAllAreasBasedByCountryCode(){
        List<AdminArea> areasList = new ArrayList<>();
        Optional<Country> country = loadAllCountriesBasedOnARegion().stream().findFirst();

        if(country.isPresent()){
            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .fromHttpUrl(accuweatherURL + "/locations/v1/adminareas")
                    .queryParam("apikey", apikey)
                    .queryParam("countryCode", country.get().getId());

            ResponseEntity<List<AdminArea>> response = restTemplate.exchange(
                    uriBuilder.build().encode().toUri(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<AdminArea>>() {
                    });

            areasList = response.getBody();
        }
        return areasList;
    }
}
