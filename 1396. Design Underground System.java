class UndergroundSystem {
    
    Map<Integer, CheckInInfo> checkIn;
    Map<String, TravelStats> travelTime;

    public UndergroundSystem() {
        checkIn = new HashMap<>();
        travelTime = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkIn.put(id, new CheckInInfo(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        CheckInInfo cii = checkIn.get(id);
        int time = t - cii.time;
        String stations = cii.station + "-" + stationName;
        travelTime.putIfAbsent(stations, new TravelStats());
        travelTime.get(stations).totalTime += time;
        travelTime.get(stations).count++;
        // remove from checkIn map when customer checks out to save space
        checkIn.remove(id);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String stations = startStation + "-" + endStation;
        TravelStats ts = travelTime.get(stations);
        return (double)ts.totalTime / ts.count;
    }
}

class CheckInInfo {
    String station;
    int time;
    public CheckInInfo(String stationName, int t) {
        this.station = stationName;
        this.time = t;
    }
}

class TravelStats {
    int totalTime;
    int count;
    public TravelStats() {}
}

// oop
class Passenger {
    int checkinTime;
    int checkoutTime;
    String checkinLocation;
    String checkoutLocation;

    public Passenger(String checkinLocation, int checkinTime) {
        this.checkinLocation = checkinLocation;
        this.checkinTime = checkinTime;
    }

    void checkout(String checkoutLocation, int checkoutTime) {
        this.checkoutLocation = checkoutLocation;
        this.checkoutTime = checkoutTime;
    }

}

class Route {
    String startStation;
    String endStation;
    int totalNumberOfTrips;
    long totalTimeSpentInTrips;

    public Route(String startStation, String endStation) {
        this.startStation = startStation;
        this.endStation = endStation;
    }

    double getAverageTime() {
        return (double) totalTimeSpentInTrips / totalNumberOfTrips;
    }

    void addTrip(int startTime, int endTime) {
        totalTimeSpentInTrips += endTime - startTime;
        totalNumberOfTrips++;
    }
}

class UndergroundSystem {

    Map<Integer, Passenger> currentPassengerMap;
    Map<String, Route> routeMap;

    public UndergroundSystem() {
        currentPassengerMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        if (!currentPassengerMap.containsKey(id)) {
            Passenger passenger = new Passenger(stationName, t);
            currentPassengerMap.put(id, passenger);
        }
    }

    public void checkOut(int id, String stationName, int t) {
        if (currentPassengerMap.containsKey(id)) {
            Passenger passenger = currentPassengerMap.get(id);
            passenger.checkout(stationName, t);
            String routeKey = passenger.checkinLocation + "," + passenger.checkoutLocation;
            Route route = routeMap.getOrDefault(routeKey, new Route(passenger.checkinLocation, passenger.checkoutLocation));
            route.addTrip(passenger.checkinTime, passenger.checkoutTime);
            routeMap.put(routeKey, route);
            currentPassengerMap.remove(id);
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        return routeMap.get(startStation + "," + endStation).getAverageTime();
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
