/**
 * Represents time - hours:minutes. Coordinates cannot be negative.
 */
public class Time1 {

    private int _hours, _minutes;

    private static final int MINIMUM_MINUTE_RANGE = 0;
    private static final int MAXIMUM_MINUTE_RANGE = 59;
    private static final int MINIMUM_HOUR_RANGE = 0;
    private static final int MAXIMUM_HOUR_RANGE = 23;
    private static final int MINUTES_IN_HOUR = 60;

    /**
     * Constructs a Time1 object. Construct a new time instance with the specified hour and minute.
     * hour should be between 0-23, otherwise it should be set to 0.
     * minute should be between 0-59, otherwise it should be set to 0.
     * @param h - the hour of the time
     * @param m - the minute of the time
     */
    public Time1(int h, int m) {
        if(!isTimeValid(h,m)){
            _minutes = MINIMUM_MINUTE_RANGE;
            _hours = MINIMUM_HOUR_RANGE;
        } else {
            _minutes = m;
            _hours = h;
        }
    }

    /**
     * Copy constructor for Time1. Construct a time with the same instance variables as another time.
     * @param other - The time object from which to construct the new time
     */
    public Time1(Time1 other) {
        _minutes = other._minutes;
        _hours = other._hours;
    }

    /**
     * validate if time is valid
     * @return boolean
     */
    private boolean isTimeValid(int hours, int minutes) {
        return (isHourValid(hours) && isMinuteValid(minutes));
    }

    /**
     * check if hour is valid
     * @return boolean
     */
    private boolean isHourValid(int h) {
        return (h >= MINIMUM_HOUR_RANGE && h <= MAXIMUM_HOUR_RANGE);
    }

    /**
     * check if minute is valid
     * @return boolean
     */
    private boolean isMinuteValid(int m) {
        return (m >= MINIMUM_MINUTE_RANGE && m <= MAXIMUM_MINUTE_RANGE);
    }

    /**
     * Returns the hour of the time.
     * @return  The hour of the time
     */
    public int getHour(){
        return _hours;
    }

    /**
     * Returns the minute of the time.
     * @return  The minute of the time
     */
    public int getMinute(){
        return _minutes;
    }

    /**
     * Changes the hour of the time. If an illegal number is received hour will be unchanged.
     * @param num - The new hour
     */
    public void setHour(int num){
        _hours = (isHourValid(num)) ? num : _hours;
    }

    /**
     * Changes the minute of the time. If an illegal number is received minute will be unchanged.
     * @param num - The new minute
     */
    public void setMinute(int num){
        _minutes = (isMinuteValid(num)) ? num : _minutes;
    }

    /**
     * [formatInt - fix num to 2 int like: 7 to 07]
     * @param  num [hour/minute] Int
     * @return String
     */
    public String formatInt(int num){
        if(num == 0){
            return "00";
        } else if(num < 10){
            return ("0" + num);
        } else {
            return "" + num;
        }
    }

    /**
     * Return a string representation of this time (hh:mm).
     * @Overrides   toString in class java.lang.Object
     * @return      String representation of this time (hh:mm).
     */
    public String toString() {
        return formatInt(_hours) + ":" + formatInt(_minutes);
    }

    /**
     * Return the amount of minutes since midnight.
     * @return  amount of minutes since midnight.
     */
    public int minFromMidnight(){
        return _hours * MINUTES_IN_HOUR + _minutes;
    }

    /**
     * Check if the received time is equal to this time.
     * @param other - The time to be compared with this time
     * @return  True if the received time is equal to this time
     */
    public boolean equals(Time1 other){
        return (_hours == other._hours && _minutes == other._minutes);
    }

    /**
     * Check if this time is before a received time.
     * @param other - The time to check if this point is before
     * @return  True if this time is before other time
     */
    public boolean before(Time1 other){
        return (_hours * MINUTES_IN_HOUR + _minutes) < (other._hours  * MINUTES_IN_HOUR + other._minutes);
    }

    /**
     * Check if this time is after a received time.
     * @param other - The time to check if this point is after
     * @return  True if this time is after other time
     */
    public boolean after(Time1 other){
        return !before(other);
    }

    /**
     * Calculates the difference (in minutes) between two times. Assumption: this time is after other time.
     * @param other - The time to check the difference to
     * @return  int difference in minutes
     */
    public int difference(Time1 other) {
        return (Math.abs(_hours - other._hours) * MINUTES_IN_HOUR) + Math.abs(_minutes - other._minutes);
    }
}