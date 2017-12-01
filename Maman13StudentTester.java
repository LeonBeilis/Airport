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
//        System.out.println();
//        a1.removeFlight(f1);
//        System.out.println(a1);
//        System.exit(1);

        /**
         * START Leon Debug
         */
        Flight f5 = new Flight("Tel-Aviv","London",6,40,210,250,100);
        Flight f6 = new Flight("Russia","Tel-Aviv",15,20,210,250,100);
        Flight f7 = new Flight("Zoo","Tel-Aviv",10,12,210,250,100);
        Flight f8 = new Flight("Zoo","Tel-Aviv",12,12,210,250,100);
        Flight f9 = new Flight("Zoo","Tel-Aviv",11,12,210,250,100);
        Flight f10 = new Flight("Zoo2","Tel-Aviv",10,12,210,250,100);
        Flight f11 = new Flight("Zoo2","Tel-Aviv",12,12,210,250,100);
        Flight f12 = new Flight("Zoo2","Tel-Aviv",11,12,210,250,100);
        Flight f13 = new Flight("Zoo2","Tel-Aviv",11,12,210,250,100);
        a1.addFlight(f5);
        a1.addFlight(f6);
        a1.addFlight(f7);
        a1.addFlight(f8);
        a1.addFlight(f9);
        a1.addFlight(f10);
        a1.addFlight(f11);
        a1.addFlight(f12);
        a1.addFlight(f13);
//        System.out.println("after 4 flights added.");
//        System.out.println(a1);
        a1.mostPopularDestination();
        System.exit(1);
        /**
         * END Leon Debug
         */

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