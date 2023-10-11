package org.softuni.exam.structures;

import org.softuni.exam.entities.Airline;
import org.softuni.exam.entities.Flight;

import java.util.*;
import java.util.stream.Collectors;

public class AirlinesManagerImpl implements AirlinesManager {
    private Map<String, Airline> airLineById = new LinkedHashMap<>();
    private Map<String, Flight> flightById = new LinkedHashMap<>();
    private Map<String, Flight> completedflights = new LinkedHashMap<>();
    private Map<String, List<Flight>> flightByAirline = new LinkedHashMap<>();
    @Override
    public void addAirline(Airline airline) {
        airLineById.put(airline.getId(),airline);
        flightByAirline.put(airline.getId(), new ArrayList<>());
    }

    @Override
    public void addFlight(Airline airline, Flight flight) {
        if(!contains(airline)){
            throw new IllegalStateException();
        }
        flightById.put(flight.getId(),flight);
        List<Flight> currentAirlineFlights = flightByAirline.get(airline.getId());
        currentAirlineFlights.add(flight);
        if(flight.isCompleted()){
            completedflights.put(flight.getId(),flight);
        }
    }

    @Override
    public boolean contains(Airline airline) {
        return airLineById.containsKey(airline.getId());
    }

    @Override
    public boolean contains(Flight flight) {
        return flightById.containsKey(flight.getId()) ;
    }

    @Override
    public void deleteAirline(Airline airline) throws IllegalArgumentException {
        if(!contains((airline))){
            throw new IllegalArgumentException();
        }
    airLineById.remove(airline.getId());
        List<Flight> flightsRemove = flightByAirline.get(airline.getId());
        for (Flight flight : flightsRemove) {
            flightById.remove(flight.getId());
            completedflights.remove(flight.getId());
        }
    }

    @Override
    public Iterable<Flight> getAllFlights() {
        return flightById.values();
    }

    @Override
    public Flight performFlight(Airline airline, Flight flight) throws IllegalArgumentException {
        if(!contains(airline) || !contains(airline)){
            throw new IllegalArgumentException();
        }
        Flight completedFlight = flightById.get(flight.getId());
        completedFlight.setCompleted(true);
        completedflights.put(completedFlight.getId(),completedFlight);
        return completedFlight;
    }

    @Override
    public Iterable<Flight> getCompletedFlights() {
        return completedflights.values();
    }

    @Override
    public Iterable<Flight> getFlightsOrderedByNumberThenByCompletion() {
        return flightById.values().stream()
                .sorted(Comparator.comparing(Flight::isCompleted).thenComparing(Flight::getNumber))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Airline> getAirlinesOrderedByRatingThenByCountOfFlightsThenByName() {
        return airLineById.values().stream()
                .sorted(Comparator.comparing(Airline::getRating).reversed().thenComparing(a -> flightByAirline.get(a.getId()).size()).reversed()
                                .thenComparing(Airline::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Airline> getAirlinesWithFlightsFromOriginToDestination(String origin, String destination) {
        return airLineById.values().stream()
                .filter(a -> flightByAirline.get(a.getId()).stream().allMatch(f-> !f.isCompleted() && f.getOrigin().equals(origin) &&f.getDestination().equals(destination)))
                .collect(Collectors.toList());
    }
}
