package com.pluralsight.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

import org.junit.Test;

public class RestControllerTest {

    @Test(timeout = 3000)
    public void testCreateRide() {

        RestTemplate restTemplate = new RestTemplate();

        Ride ride = new Ride();
        ride.setName("Yellow Fork Trail Ride");
        ride.setDuration(33);

        ride = restTemplate.postForObject("http://localhost:8180/RideTracker/ride", ride, Ride.class);

        System.out.println("Ride: " + ride);
    }

    @Test(timeout = 3000)
    public void testGetRides() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange("http://localhost:8180/RideTracker/rides",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ride>>() {
                });
        List<Ride> rides = ridesResponse.getBody();

        for (Ride ride : rides) {
            System.out.println("Ride name: " + ride.getName());
        }
    }

    @Test(timeout = 3000)
    public void testGetRide() {
        RestTemplate restTemplate = new RestTemplate();
        
        Ride ride = restTemplate.getForObject("http://localhost:8180/RideTracker/ride/1", Ride.class);
        
        System.out.println("Ride name: " + ride.getName());

    }

    @Test(timeout = 3000)
    public void testUpdateRide() {
        RestTemplate restTemplate = new RestTemplate();
        
        Ride ride = restTemplate.getForObject("http://localhost:8180/RideTracker/ride/1", Ride.class);
        
        ride.setDuration(ride.getDuration() + 1);
        
        restTemplate.put("http://localhost:8180/RideTracker/update/ride", ride);
        
        System.out.println("Ride name: " + ride.getName() + " has a new duration of " + ride.getDuration());
    }
}
