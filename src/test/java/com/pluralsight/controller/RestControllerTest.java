package com.pluralsight.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

import org.junit.Test;

public class RestControllerTest {

	@Test(timeout=3000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:8080/ride_tracker/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}
	}
	
	@Test(timeout=3000)
	public void testCreateRide() {
		RestTemplate restTemplate = new RestTemplate();
		Ride ride = new Ride();
		
		ride.setName("Sagebush Trail X");
		ride.setDuration(38);
		
		//restTemplate.put("http://localhost:8080/ride_tracker/ride", ride);
		restTemplate.postForObject("http://localhost:8080/ride_tracker/ride", ride, Ride.class);
		
		System.out.println("Ride: "+ride.getId());
		
	}
	
	@Test(timeout=3000)
	public void updateRide() {
		RestTemplate restTemplate = new RestTemplate();
		
		Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/1", Ride.class);
		
		//restTemplate.postForObject("http://localhost:8080/ride_tracker/ride", ride, Ride.class);
		
		System.out.println("From getRide()");
		System.out.println("Ride: "+ride.getId());
		System.out.println("Ride: "+ride.getName());
		System.out.println("Ride: "+ride.getDuration());
		System.out.println("From updating duration, +5....");
		
		ride.setDuration(ride.getDuration()+5);
		
		restTemplate.put("http://localhost:8080/ride_tracker/ride", ride);
		
	}
	
	@Test(timeout=3000)
	public void testBatchUpdate() {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getForObject("http://localhost:8080/ride_tracker/batch", Object.class);
		
	}
	
	@Test(timeout=10000)
	public void testDelete() {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.delete("http://localhost:8080/ride_tracker/delete/6");
		
	}
	
	@Test(timeout=10000)
	public void testException() {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getForObject("http://localhost:8080/ride_tracker/test", Ride.class);
		
	}
	
}
