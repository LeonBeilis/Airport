/**
 * Maman 13, Java Assingment
 * @author Leon
 */
public class Airport {
    private int _noOfFlights;
    private String _airport;
    private final static int MAX_FLIGHTS = 200;
    private Flight[] _flightSchedule;

    private static final int MINIMUM_MINUTE_RANGE = 0;
    private static final int MINIMUM_HOUR_RANGE = 0;

    public Airport(String city){
        _airport = city;
        _flightSchedule = new Flight[MAX_FLIGHTS];
    }

    /**
     * adding flight to flightSchedule array
     * checks if received flight origin or destination is in airport
     * checks if there available space in flightSchedule, if there is
     * adding the new flight and return true
     * @param f Flight Object
     * @return boolean
     */
    public boolean addFlight(Flight f){
        if(f.getDestination().equals(_airport) || f.getOrigin().equals(_airport)) {
            for (int i = 0; i < _flightSchedule.length; i++){
                if(_flightSchedule[i] == null){
                    _flightSchedule[i] = new Flight(f);
                    _noOfFlights = numOfFlights();
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }

    }

    /**
     * remove flight from airport flight array
     * @param f Flight Object
     * @return boolean
     */
    public boolean removeFlight(Flight f){
        for (int i = 0; i < _noOfFlights; i++){
            if(_flightSchedule[i].equals(f)){
                _flightSchedule[i] = null;
                _noOfFlights = numOfFlights();
                if(i == (_noOfFlights)){
                    _flightSchedule[_noOfFlights] = null;
                } else {
                    _flightSchedule[i] = new Flight(_flightSchedule[_noOfFlights]);
                    _flightSchedule[_noOfFlights] = null;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * returns num of _flightSchedule without null object
     * @return int
     */
    private int numOfFlights(){
        int count = 0;
        for (int i = 0; i < _flightSchedule.length; i++){
            if(_flightSchedule[i] != null){
                count++;
            }
        }
        return count;
    }

    /**
     * return time of first flight by place
     * @param place     flight's origin
     * @return Time1
     */
    public Time1 firstFlightFromDestination(String place){
        if(_flightSchedule.length < 1){
            return null;
        }

        int citiesInSameDay = 0;
        /**
         * find how many cities does land in day
         * and with same place
         */
        Flight f1 = new Flight("a",place,12,0,210,250,100);
        Time1 lastTimeInSameDay = new Time1(23,59);
        f1.setDeparture(lastTimeInSameDay);
        Time1 firstFlightInDay = new Time1(f1.getDeparture());
        for (int i = 0; i < _noOfFlights; i++){
            if(_flightSchedule[i].getDestination().equals(place)){
                citiesInSameDay++;
                Time1 tmp = new Time1( _flightSchedule[i].getDeparture() );
                if(tmp.before(firstFlightInDay)){
                    firstFlightInDay = new Time1( _flightSchedule[i].getDeparture() );
                }
            }
        }
        if(citiesInSameDay == 0){
            return null; //no cities in same day
        }
        return new Time1(firstFlightInDay);
    }

    /**
     * count full flight's in airport and return it
     * @return int
     */
    public int howManyFullFlights(){
        int countFullFlights = 0;
        for (int i = 0; i < _noOfFlights; i++){
            if( _flightSchedule[i].getIsFull() ){
                countFullFlights++;
            }
        }
        return countFullFlights;
    }

    /**
     * count flights by origin and destination that land from city1 to city2 or from city2 to city1
     * @param city1 String  origin/destination
     * @param city2 String  origin/destination
     * @return int  flight between 2 places
     */
    public int howManyFlightsBetween(String city1, String city2){
        int flightBetweenCitiesCount = 0;
        for (int i = 0; i < _noOfFlights; i++){
            if( ((_flightSchedule[i].getOrigin()).equals(city1) && (_flightSchedule[i].getDestination()).equals(city2)) ||
                    ((_flightSchedule[i].getOrigin()).equals(city2) && (_flightSchedule[i].getDestination()).equals(city1)) ){
                flightBetweenCitiesCount++;
            }
        }
        return flightBetweenCitiesCount;
    }

    /**
     * return the most populate city by its destination
     * @return String
     */
    public String mostPopularDestination(){
        int citiesInSameDay = 0;
        /**
         * find how many cities does land in day
         */
        for (int i = 0; i < _noOfFlights; i++){
            Flight f1 = new Flight("a","b",12,0,210,250,100);
            Time1 lastTimeInSameDay = new Time1(23,59);
            f1.setDeparture(lastTimeInSameDay);
            if(_flightSchedule[i].landsEarlier(f1)){
                citiesInSameDay++;
            }
        }
        /**
         * if no flights landed in same day return
         */
        if(citiesInSameDay == 0){
//            return "there are no flights that land today!";
            return null; //teacher said to return null if none found
        }
        /**
         * set cities array given size of cities that lands in same day
         * and collect cities of flights
         */
        String[] cities = new String[citiesInSameDay];
        for (int i = 0; i < citiesInSameDay; i++){
            cities[i] = _flightSchedule[i].getDestination();
        }

        int[][] cityByScore = new int[cities.length][cities.length];
        for (int i = 0; i < cities.length; i++){
            cityByScore[i][0] = 0;
        }
        /**
         * create action table with city index and its score
         * [[*]]            [city index]
         * [score index]    [0]
         */
        for (int i = 0; i < cityByScore.length; i++){
            String city = cities[i];
            for (int j = 0; j < cityByScore.length; j++){
                if( cities[j].equals(city) ){
                    cityByScore[i][0] += 1; //give +1 if match to city by index
                }
            }
        }
        /**
         * find max score and get its city id
         */
        int[][] max = new int[1][1];
        max[0][0] = 0;
        int cityID = 0;
        for (int i = 0; i < cityByScore.length; i++){
            for (int j = 0; j < cityByScore.length; j++){
                if(cityByScore[i][j] > max[0][0]){
                    max[0][0] = cityByScore[i][j];
                    cityID = i;
                }
            }
        }
        return "most populate city by destination is: " + cities[ cityID ];
    }

    /**
     * find most expensive ticket in flights that inside the airport
     * @return Flight Object
     */
    public Flight mostExpensiveTicket(){
        //todo: what if there more then 1 flight that its ticket is expensive? overwrite current one or not
        Flight mostExpensiveTicketFlight = new Flight( "tmp", "tmp", 0, 0, 0, 0, 0);
        for (int i = 0; i < _noOfFlights; i++){
            if ( _flightSchedule[i].getPrice() > mostExpensiveTicketFlight.getPrice() ){
                mostExpensiveTicketFlight = new Flight(_flightSchedule[i]);
            }
        }
        return mostExpensiveTicketFlight;
    }

    /**
     * find the longestFlight inside the airport
     * @return Flight Object
     */
    public Flight longestFlight(){
        Flight mostLongestFlight = new Flight( "tmp", "tmp", 0, 0, 0, 0, 0);
        for (int i = 0; i < _noOfFlights; i++){
            if ( _flightSchedule[i].getFlightDuration() > mostLongestFlight.getFlightDuration() ){
                mostLongestFlight = new Flight(_flightSchedule[i]);
            }
        }
        return mostLongestFlight;
    }

    /**
     * return all the flights inside airport Object
     * @return String
     */
    public String toString(){
        System.out.println("The flights for airport " + _airport +" today are:");
        for (int i = 0; i < _noOfFlights; i++){
            System.out.println(_flightSchedule[i]);
        }
        return "";
    }

}
