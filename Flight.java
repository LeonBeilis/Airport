public class Flight {
    
    /**
     * final declartions
     */
    private static final int MAX_CAPACITY = 250;
    private static final int MIN_CAPACITY = 0;
    private static final int MINUTES_IN_DAY = 1440;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int MIN_PRICE = 0;
    
    /**
     * private declarations
     */
    private String _dest, _origin;
    private Time1 _departure;
    private int _durTimeMinutes, _noOfPass, _price;
    private boolean _isFull;
    
    /**
     * Constructor for a Flight object. If the number of passengers exceeds the maximum capacity the number of passengers
     * will be set to the maximum capacity If the number of passengers is negative the number of passengers will be set to zero.
     * If the flight duration is negative the flight duration will be set to zero.
     * If the price is negative the price will be set to zero.
     * @param   dest                The city the flight lands at.
     * @param   origin              The city the flight leaves from.
     * @param   depHour             the departure hour (should be between 0-23).
     * @param   depMinute           The departure minute (should be between 0-59).
     * @param   durTimeMinutes      The duration time in minutes(should not be negative).
     * @param   noOfPass            The number of passengers (should be between 0-maximum capacity).
     * @param   price               The price (should not be negative).
     */
    public Flight( String dest, String origin,
                    int depHour, int depMinute, int durTimeMinutes,
                    int noOfPass, int price ){
        _dest = dest;
        _origin = origin;
        _departure = new Time1(depHour, depMinute);
        _durTimeMinutes = durTimeMinutes;
        _noOfPass = validatePassengersCapacity(noOfPass);
        _isFull = (_noOfPass == MAX_CAPACITY) ? true : false;
        _price = price;
    }
    
    /**
     * Copy constructor for a Flight object. Construct a Flight object with the same attributes as another Flight object.
     * @param   other   The Flight object from which to construct the new Flight.
     */
    public Flight(Flight other) {
        _origin = other._origin;
        _dest = other._dest;
        _departure = other._departure;
        _durTimeMinutes = other._durTimeMinutes;
        _noOfPass = other._noOfPass;
        _isFull = (_noOfPass == MAX_CAPACITY) ? true : false;
        _price = other._price;
    }
    
    /**
     * validates noOfPass, if higher then max, then return max capacity
     * if lower then min, return min capacity
     * else if in range return itself
     */
    private static int validatePassengersCapacity(int num) {
        if (num > MAX_CAPACITY) {
            return MAX_CAPACITY;
        } else if (num < MIN_CAPACITY) {
            return MIN_CAPACITY;
        } else {
            return num;
        }
    }
    
    /**
     * Check if the received flight is equal to this flight.
     * Flights are considered equal if the origin, destination and departure times are the same.
     * @param   other   The flight to be compared with this flight.
     * @return  True if the received flight is equal to this flight.
     */
    public boolean equals(Flight other) {
        return (_origin.equals(other._origin) && _dest.equals(other._dest) && _departure.equals(other._departure));
    }
    
    /**
     * Returns the arrival time of the flight.
     * @return  The arrival time of this flight.
     */
    public Time1 getArrivalTime() {
        int totalMinutes = _departure.getMinute() + (_departure.getHour() * MINUTES_IN_HOUR);
        totalMinutes += _durTimeMinutes;
        Time1 arrivalTime = new Time1(_departure);
        arrivalTime.setHour((totalMinutes / MINUTES_IN_HOUR) % (MINUTES_IN_DAY / MINUTES_IN_HOUR));
        arrivalTime.setMinute(totalMinutes % MINUTES_IN_HOUR);
        return arrivalTime;
    }

    /**
     * Add passengers to this flight. If the number of passengers exceeds he maximum capacity,
     * no passengers are added and also is returned. If the flight becomes full,
     * the boolean attribute describing whether the flight if full becomes true.
     * Assume parameter is positive.
     * @param   num     The number of passengers to be added to this flight.
     * @return  True if the passengers were added to the flight.
     */
    public boolean addPassengers(int num) {
        if((_noOfPass + num) <= MAX_CAPACITY) {
            _noOfPass += num;
            _isFull = (_noOfPass== MAX_CAPACITY) ? true : false;
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Check if this flight is cheaper than another flight.
     * @param   other   The flight whose price is to be compared with this flight's price.
     * @return  True if this flight is cheaper than the received flight.
     */
    public boolean isCheaper(Flight other) {
        return (_price < other._price) ? true : false;
    }
    
    /**
     * Calculate the total price of the flight.
     * @return  The total price of the flight.
     */
    public int totalPrice() {
        return (_noOfPass * _price);
    }
    
    /**
     * Check if this flight lands before another flight.
     * Note - the flights may land on different days, the method checks which flight lands first.
     * @param   other   The flight whose arrival time to be compared with this flight's arrival time.
     * @return  True if this flight arrives before the received flight.
     */
    public boolean landsEarlier(Flight other) {
        int totalMinutesFirstFlight = _departure.getMinute() + (_departure.getHour() * MINUTES_IN_HOUR) + _durTimeMinutes;
        int totalMinutesSecondFlight = other._departure.getMinute() + (other._departure.getHour() * MINUTES_IN_HOUR) + other._durTimeMinutes;
        return (totalMinutesFirstFlight < totalMinutesSecondFlight) ? true : false;
    }
    
    /**
     * Return a string representation of this flight (for example: "Flight from London to Paris departs at 09:24.Flight is full.").
     * @return  String representation of this flight
     */
    public String toString() {
        String passengersAvailableStatus = (_isFull) ? "" : " not";
        return ("Flight from " + _origin + " to " + _dest + " departs at " + _departure +
            " Flight is" + passengersAvailableStatus + " full.");
    }
    
    /**
     * Returns the flight origin.
     * @return  The flight origin.
     */
    public String getOrigin() {
        return _origin;
    }
    
    /**
     * Returns the flight departure time.
     * @return  A copy of the flight departure time.
     */
    public Time1 getDeparture() {
        return (new Time1(_departure));
    }
    
    /**
     * Returns the flight destination.
     * The flight destination.
     */
    public String getDestination() {
        return _dest;
    }
    
    /**
     * Returns the flight duration time in minutes.
     * @return  The flight duration.
     */
    public int getFlightDuration() {
        return _durTimeMinutes;
    }
    
    /**
     * Returns the number of passengers on the flight.
     * @return  The number of passengers.
     */
    public int getNoOfPassengers() {
        return _noOfPass;
    }
    
    /**
     * Returns whether the flight is full or not.
     * @return  True if the flight is full.
     */
    public boolean getIsFull() {
        return _isFull;
    }
    
    /**
     * Returns the price of the flight.
     * @return  The price.
     */
    public int getPrice() {
        return _price;
    }
    
    /**
     * Changes the flight's origin.
     * @param   origin  The flight's new origin.
     */
    public void setOrigin(String origin) {
        _origin = origin;
    }
    
    /**
     * Changes the flight's destination.
     * @param   dest    The flight's new destination.
     */
    public void setDestination(String dest) {
        _dest = dest;
    }
    
    /**
     * Changes the flight's departure time.
     * @param   departureTime   The flight's new departure time.
     */
    public void setDeparture(Time1 departure) {
        _departure = new Time1(departure);
    }
    
    /**
     * Changes the flight's duration time. If the parameter is negative the duration time will remain unchanged.
     * @param   durTimeMinutes  The flight's new duration time.
     */
    public void setFlightDuration(int durTimeMinutes) {
        if (durTimeMinutes >= 0) {
            _durTimeMinutes= durTimeMinutes;
        }
    }
    
    /**
     * Changes the number of passengers.
     * If the parameter is negative or larger than the maximum capacity the number of passengers will remain unchanged.
     * @param   noOfPass    The new number of passengers.
     */
    public void setNoOfPassengers(int noOfPass) {
        if((noOfPass >= MIN_CAPACITY) && (noOfPass <= MAX_CAPACITY)) {
            _noOfPass = noOfPass;
            _isFull = (_noOfPass == MAX_CAPACITY) ? true : false;
        }
    }
    
    /**
     * Changes the flight price. If the parameter is negative the price will remain unchanged.
     * @param   price   The new price.
     */
    public void setPrice(int price) {
        if (price >= MIN_PRICE) {
            _price = price;
        }
    }

}