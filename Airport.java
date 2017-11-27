/**
 * notes:
 * Flight object array should be without holes after every action
 * check for aliasing
 * write own api on end
 */
public class Airport {
    private Flight[] _flightsSchedule;
    private int _noOfFlights;
    private String _airport;
    final static int MAX_FLIGHTS = 200;
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
        if(f.getDestination() == _airport || f.getOrigin() == _airport) {
            //todo: check for faster way to find empty space in flight schedule to insert new flight
            //todo: also consider check if flight added successfully to flightSchedule array
            //destination or origin is airport, check if there is space in flightSchedule
            for (int i = 0; i < _flightSchedule.length; i++){
                // search for empty space inside flightSchedule and set new flight if found
                if(_flightSchedule[i] == null){
                    _flightSchedule[i] = f;
                    return true;
                    //break; //no need because return breaks the loop
                }
            }
            //all flightSchedule is full of flights
            return false;
        } else {
            //neither dest or origin match the airport
            return false;
        }
    }


    public boolean removeFlight(Flight f){
        //todo: check with lecturer if on delete set the index to null and/or change other flight index's in array.
        //todo: do we need to check for dest and origin again like on addFlight..
        //todo: do we need to check if flight has being deleted after deletetion?
        for (int i = 0; i < _flightSchedule.length; i++){
            // search for empty space inside flightSchedule and set new flight if found
            if(_flightSchedule[i] == f){
                _flightSchedule[i] = null;
                return true;
                //break; //no need because return breaks the loop
            }
        }
        // flight not found!
        return false;
    }

    public Time1 firstFlightFromDestination(String place){
        return _flightSchedule[0].getDeparture();
        //todo: do we need to check multi airport or just one? for now checking on this one only..
        //todo: what to do if there more then one flight in same day?
        //todo: can't set time to null.. gotta rework it
        /*
        if(_airport != place){
            return null;
        }
        Time1 firstFlightInDay = new Time1(null);
        for (int i = 0; i < flightSchedule.length; i++){
            if(flightSchedule[i].getOrigin() == place){
                Time1 tmp = new Time1( flightSchedule[i].getDeparture() );
                if(tmp.before(firstFlightInDay)){
                    firstFlightInDay = tmp;
                }
            }
        }
        if(firstFlightInDay.equals(null)){
            //nothing changed
            return null;
        } else {
            //return the firstFlight in day
            return firstFlightInDay;
        }
        */
    }

    public int howManyFullFlights(){
        int countFullFlights = 0;
        for (int i = 0; i < _flightSchedule.length; i++){
            if( _flightSchedule[i] != null && _flightSchedule[i].getIsFull() ){
                countFullFlights++;
            }
        }
        return countFullFlights;
    }

    public int howManyFlightsBetween(String city1, String city2){
        //todo: check if must check those flight in same day or it doesn't matter.. because if do more calculation is needed
        int flightBetweenCitiesCount = 0;
        for (int i = 0; i < _flightSchedule.length; i++){
            if(_flightSchedule[i] != null) {
                if( (_flightSchedule[i].getOrigin() == city1 && _flightSchedule[i].getDestination() == city2) ||
                        (_flightSchedule[i].getOrigin() == city2 && _flightSchedule[i].getDestination() == city1) ){
                    flightBetweenCitiesCount++;
                }
            }
        }
        return flightBetweenCitiesCount;
    }

    public String mostPopularDestination(){
        //todo:check if in same day
        //todo: finish this..
        // cities array maxed to max flights.. cant be more destination then max flights..
        /*
        String[] citiesArray = new String[MAX_FLIGHTS];
        for (int i = 0; i < _flightSchedule.length; i++){
            String city = _flightSchedule[i].getDestination();
            todo: think how to collect data and return most popular destination, maybe 2d array..
            for (int j; j < citiesArray.length; i++){
                if(citiesArray[i].equals(city)){

                }
            }
        }
        */
        return "Under Development";
    }

    public Flight mostExpensiveTicket(){
        //todo: what if there more then 1 flight that its ticket is expensive? overwrite current one or not
        Flight mostExpensiveTicketFlight = new Flight( "tmp", "tmp", 0, 0, 0, 0, 0);
        for (int i = 0; i < _flightSchedule.length; i++){
            if(_flightSchedule[i] != null) {
                if (((int) _flightSchedule[i].getPrice()) > ((int) mostExpensiveTicketFlight.getPrice())) {
                    mostExpensiveTicketFlight = _flightSchedule[i];
                }
            }
        }
        return mostExpensiveTicketFlight;
    }

    public Flight longestFlight(){
        Flight mostLongestFlight = new Flight( "tmp", "tmp", 0, 0, 0, 0, 0);
        for (int i = 0; i < _flightSchedule.length; i++){
            if(_flightSchedule[i] != null) {
                if ((int) _flightSchedule[i].getFlightDuration() > (int) mostLongestFlight.getFlightDuration()) {
                    mostLongestFlight = _flightSchedule[i];
                }
            }
        }
        return mostLongestFlight;
    }

    public String toString(){
        //todo: check if "Tel-Aviv" should be static or _airport..
        //todo: rework it
//        System.out.println("The flights for airport Tel-Aviv today are:");
        for (int i = 0; i < _flightSchedule.length; i++){
            if(_flightSchedule[i] != null){
//                System.out.println(flightSchedule[i]);
            }
        }
        return "UNDER DEVELOPMENT";
    }

}
