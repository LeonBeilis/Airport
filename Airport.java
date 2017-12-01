/**
 * notes:
 * Flight object array should be without holes after every action
 * check for aliasing
 * write own api on end
 * todo's: remove all debugging
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
        if(f.getDestination() == _airport || f.getOrigin() == _airport) {
            //todo: check for faster way to find empty space in flight schedule to insert new flight
            //todo: also consider check if flight added successfully to flightSchedule array
            //destination or origin is airport, check if there is space in flightSchedule
            for (int i = 0; i < _flightSchedule.length; i++){
                // search for empty space inside flightSchedule and set new flight if found
                if(_flightSchedule[i] == null){
                    _flightSchedule[i] = f;
//                    System.out.println("Flight " + f.getOrigin() + " has being added.");
                    _noOfFlights = numOfFlights();
//                    System.out.println("current num of flights is: " + _noOfFlights);
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
        //todo: check with lecturer if on delete set the index to null and/or change other flight index's in array. - yes
        for (int i = 0; i < _noOfFlights; i++){
            // search for empty space inside flightSchedule and set new flight if found
            if(_flightSchedule[i] == f){
//                System.out.println("num of flights before remove: " + _noOfFlights);
                _flightSchedule[i] = null;
                _noOfFlights = numOfFlights();
//                System.out.println("num of flights after remove: " + _noOfFlights);
                //more test if 0/1 indexs..
                _flightSchedule[i] = (_flightSchedule[_noOfFlights] != null) ? _flightSchedule[_noOfFlights] : _flightSchedule[_noOfFlights - 1];
//                System.out.println("num of flights after insert last: " + _noOfFlights);
                return true;
                //break; //no need because return breaks the loop
            }
        }
        // flight not found!
        return false;
    }

    /**
     * number of actural flights in flightSchedule array
     * @return number of flights
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

    public Time1 firstFlightFromDestination(String place){
        //check if airport has place and flights.. else return null
        if(_airport != place || _flightSchedule.length < 1){
            return null;
        }
        //set first flight as first and start look if there any flight that is before
        Time1 firstFlightInDay = new Time1( _flightSchedule[0].getDeparture() );
        int flights = numOfFlights();
//        System.out.println("number of flights: " + numOfFlights());
        for (int i = 0; i < flights; i++){
            //check other flight compare to current firstFlightInDay, skip the first
//            System.out.println("start loop on index: " + i);
            if( (_flightSchedule[i].getOrigin().equals(place))){
//                System.out.println("inside loop: " + _flightSchedule[i].getOrigin());
                Time1 tmp = new Time1( _flightSchedule[i].getDeparture() );
                if(tmp.before(firstFlightInDay)){
                    firstFlightInDay = tmp;
                }
            }
        }
        return new Time1(firstFlightInDay);
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

        String[] cities = new String[_noOfFlights];
        for (int i = 0; i < _noOfFlights; i++){
            cities[i] = _flightSchedule[i].getDestination();
        }

        //debug..
        for (int i = 0; i < _noOfFlights; i++){
            System.out.println(cities[i]);
        }

        //cities length: 7
        //cities 4
        int[][] cityByScore = new int[cities.length][cities.length];
        for (int i = 0; i < cities.length; i++){
            cityByScore[i][0] = 0;
        }

        /**
         * []               [city index]
         * [score index]        [0]
         */

        //check..
        for (int i = 0; i < cityByScore.length; i++){
            System.out.println( "score for city: " + cities[i]  + " is " + cityByScore[i][0] );
        }

        for (int i = 0; i < cityByScore.length; i++){
            String city = cities[i];
            for (int j = 0; j < cityByScore.length; j++){
                //match
                if( cities[j].equals(city) ){
                    cityByScore[i][0] += 1; //give +1 if match to city by index
                }
            }
        }

        //check..
        for (int i = 0; i < cityByScore.length; i++){
            System.out.println( "score for city: " + cities[i]  + " is " + cityByScore[i][0] );
        }

        /**
         * find max and get city id
         */
        int[][] max = new int[1][1];
//        int[][] maxIndex = new int[1][1];
//        int cityIndex = 0;
        max[0][0] = 0;
//        maxIndex[0][0] = 0;
        int cityID = 0;

//        int runArrayLength = cityByScore.length - 1;
        for (int i = 0; i < cityByScore.length; i++){
            for (int j = 0; j < cityByScore.length; j++){
//                System.out.println( cityByScore[i][j] + ":" + max[0][0]);
                if(cityByScore[i][j] > max[0][0]){
//                    System.out.println("inside max..");
                    //set max
//                    cityIndex = int cityByScore[i];
                    max[0][0] = cityByScore[i][j]; //value!
//                    maxIndex[0][0] = cityByScore[i][i]; //index!
                    cityID = i; //index!
                    /*
                    if(max[0][0] == 3){
//                        System.out.println(cityByScore[i][i]);
                        System.out.println(i);
                    }
                    */
                }
            }
        }
        /**
         * cityID = max[0]
         * cityScore = max[0][0]
         */
//        int cityID = max[0];
//        int[][] cityScore = max[0][0];
//        int cityID = maxIndex[0][0];
        System.out.println("most populate city by destination is: " + cities[ cityID ]);
//        System.out.println( "max pts is: " + max[0][0] );
//        System.out.println( "index is: " + cities[index] );
        /*
        System.out.println(cities);
        System.out.println(cities.length);
        for (int i = 0; i < cities.length; i++){
            System.out.println(cities[i]);
        }
        */

        String mostPopular = cities[0];

        /*
        int citiesCount = cities.length;
        int[][] popularity = new int[citiesCount][1];
        for (int i = 0; i < cities.length; i++){
            String city = cities[i]; //tel aviv
            for (int j = 0; j < cities.length; j++){
//                if(popularity[i][j] == null)
//                popularity[i][j] = ( city.equals(cities[j]) ) ?  popularity[i][j+1] : popularity[i][j];
//                System.out.println(cities[j]);
//                System.out.println(popularity[i][0]);
//                System.out.println(cities.length);
            }
        }
        for (int i = 0; i < popularity.length; i++) {
            System.out.println(popularity[i][0]);
        }
        System.out.println(cities.length);
        for (int i = 0; i < cities.length; i++){
            System.out.println(cities[i]);
        }
        */

        return "";

//        String[][] citiesArray = new String[_noOfFlights][_noOfFlights];
        /**
         * [][]
         * [][]
         * cities   ["tel-aviv","berlin"]
         * scores   ["3","1"]
         * //       city    city
         * scores   value   value
         * [0] city
         * [1] score
         */
        /*
        for (int i = 0; i < _noOfFlights; i++){
            String city = _flightSchedule[i].getDestination();
            //city
            int flag = 0;
            for (int j = 0; j < _noOfFlights; i++){
                if(citiesArray[0][j].equals(city)){
                    flag = 1;
                    //popularity
                    if(citiesArray[1][j] != null){
                        citiesArray[1][j] = citiesArray[1][j] + 1;
                    } else {
                        citiesArray[1][j] = 1;
                    }
//                    citiesArray[1][j] = ( citiesArray[1][j] != null ) ? citiesArray[1][j] + 1 : new String("1");
                }
            }

            if(flag == 0){
                citiesArray[0][i] = city;
                citiesArray[1][i] = "1";
            }


        }
        int max = 0;
        for (int i=0; i < _noOfFlights; i++){
            if( citiesArray[1][i] > max ){
                max = i;
            }
        }
        String mostPopCity = citiesArray[0][max];
        return mostPopCity;
        */
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
//        String res = "";
//        return _flightSchedule.toString();
        System.out.println("The flights for airport " + _airport +" today are:");
//        return "" + _noOfFlights;
//        /*
        for (int i = 0; i < _noOfFlights; i++){
//            res = concat(res,_flightSchedule[i].toString());
//            res += " " + _flightSchedule[i].toString() + " ";
            System.out.println(_flightSchedule[i]);
//            return _flightSchedule[i].toString();
        }
//        res = " t " + _noOfFlights;
        return "";
        /*
        return "";
        */
//        return response;
//        return res;
    }

}
