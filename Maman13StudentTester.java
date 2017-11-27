public class Maman13StudentTester
{
    public static void main(String []args)
    {

        /*******************************  Airport CLASS TESTER *******************************/
        /***********************************************************************************/

        //Check constructor
        Airport a1 = new Airport("Tel-Aviv");

        //AddFlight
        Flight f1 = new Flight("Tel-Aviv","London",12,0,210,250,100);
        Flight f2 = new Flight("New York","Tel-Aviv",10,50,210,250,150);
        a1.addFlight(f1);
        a1.addFlight(f2);
//        System.out.println(a1);

        //RemoveFlight
        a1.removeFlight(f1);
        System.out.println(a1);

        //First Flight From Destination
        Flight f3 = new Flight("Tel-Aviv","Paris",11,35,210,100,50);
        a1.addFlight(f3);
        Time1 t1 = a1.firstFlightFromDestination("Tel-Aviv");
        System.out.println(t1);

        //HowMany Full Flights
        int x = a1.howManyFullFlights();
        System.out.println("Full Flight - " + x);

        //HowMany Flights Between
        Flight f4 = new Flight("London","Tel-Aviv",12,1,210,249,100);
        a1.addFlight(f4);
        int y = a1.howManyFlightsBetween("Tel-Aviv","London");
        System.out.println("FlightsBetween Tel-Aviv to London - " + y);

        //Most Popular Destination
        String mostPopular = a1.mostPopularDestination();
        System.out.println("Most Popular Destination - " + mostPopular);

        //Most Expensive Ticket
        Flight mostExpensive = a1.mostExpensiveTicket();
        System.out.println("Most Expensive Ticket - " + mostExpensive);

        //Longest Flight
        Flight longestFlight = a1.longestFlight();
        System.out.println("Longest Flight - " + longestFlight);

    }
}