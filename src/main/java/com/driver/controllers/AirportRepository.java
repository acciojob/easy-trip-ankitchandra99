package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AirportRepository {

    private final Map<String, Airport> airports = new HashMap<>();
    private final Map<Integer, Flight> flights = new HashMap<>();

    public void addAirport(Airport airport) {
        airports.put(airport.getAirportName(), airport);
    }

    public String getLargestAirportName() {
        int maxTerminals = 0;
        String largestAirportName = null;

        for (Airport airport : airports.values()) {
            if (airport.getNoOfTerminals() > maxTerminals) {
                maxTerminals = airport.getNoOfTerminals();
                largestAirportName = airport.getAirportName();
            } else if (airport.getNoOfTerminals() == maxTerminals) {
                if (largestAirportName == null || airport.getAirportName().compareTo(largestAirportName) < 0) {
                    largestAirportName = airport.getAirportName();
                }
            }
        }

        return largestAirportName;
    }
    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        double shortestDuration = -1;

        for (Flight flight : flights.values()) {
            if (flight.getFromCity() == fromCity && flight.getToCity() == toCity) {
                if (shortestDuration == -1 || flight.getDuration() < shortestDuration) {
                    shortestDuration = flight.getDuration();
                }
            }
        }

        return shortestDuration;
    }

    public void addFlight(Flight flight) {
        flights.put(flight.getFlightId(), flight);
    }

    public Flight getFlightById(int flightId) {
        return flights.get(flightId);
    }

    public List<Flight> getAllFlights() {
        return new ArrayList<>(flights.values());
    }

    public void updateFlight(Flight flight) {
        flights.put(flight.getFlightId(), flight);
    }
    public void deleteFlight(int flightId) {
        flights.remove(flightId);
    }
}
