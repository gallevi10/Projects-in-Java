
/**
 * This class represents a Date object
 *
 * @author Gal Levi
 * @version 20/11/2022
 */
public class Date{
    
    // Attributes and finals
    private int _day;
    private int _month;
    private int _year;
    private static final int START = 1; // months and days start with 1
    private static final int END_DAY31 = 31; // max days in months 1,3,5,7,8,10,12
    private static final int END_DAY30 = 30; // max days in months 4,6,9,11
    private static final int END_DAY28 = 28; // max days in month 2 when it's not a leap year
    private static final int END_DAY29 = 29; // max days in month 2 when it's a leap year
    private static final int MAX_MONTH = 12; // there are only 12 months in a year
    private static final int START_YEAR = 1000;
    private static final int END_YEAR = 9999;
    private static final int DEFAULT_DAY = 1;
    private static final int DEFAULT_MONTH = 1;
    private static final int DEFAULT_YEAR = 2000;
    private static final int MAX_FOR_ZERO = 9;
    private static final int INCREASE = 1; // increasion for tomorrow method
    private static final int RESET = 1; // reset for tomorrow method
    
    /**
     * If the given date is valid - creates a new Date object, otherwise creates the date 1/1/2000
     * @param day The day in the month (1-31)
     * @param month The month in the year (1-12)
     * @param year The year (4 digits)
     */
    public Date(int day, int month, int year){
        if (isDateValid(day, month, year)){ // checks if the params are valid
            _day = day;
            _month = month;
            _year = year;
        } // end of if statement
        else{ // params are invalid
            _day = DEFAULT_DAY; // _day = 1
            _month = DEFAULT_MONTH; // _month = 1
            _year = DEFAULT_YEAR; // _year = 2000
        } // end of else statement
    } // end of Constructor Date
    
    /**
     * Copy Constructor
     * Initializes an instance of date identical to the given date
     * @param other The date to be copied
     */
    public Date(Date other){
        _day = other._day;
        _month = other._month;
        _year = other._year;
    } // end of copy Constructor Date
    
    /**
     * Gets the day
     * @return The day
     */
    public int getDay(){
        return _day;
    } // end of method getDay
    
    /**
     * Gets the month
     * @return The month
     */
    public int getMonth(){
        return _month;
    } // end of method getMonth
    
    /**
     * Gets the year
     * @return The year
     */
    public int getYear(){
        return _year;
    } // end of method getYear
    
    /**
     * Sets the day (only if date remains valid)
     * @param dayToSet The day value to be set
     */
    public void setDay(int dayToSet){
        if (isDateValid(dayToSet, _month, _year)) // checks if param dayToSet is valid
            _day = dayToSet;
    } // end of method setDay
    
    /**
     * Sets the month (only if date remains valid)
     * @param monthToSet The month value to be set
     */
    public void setMonth(int monthToSet){
        if (isDateValid(_day, monthToSet, _year)) // checks if param monthToSet is valid
            _month = monthToSet;
    } // end of method setMonth
    
    /**
     * Sets the year (only if date remains valid)
     * @param yearToSet The year value to be set
     */
    public void setYear(int yearToSet){
        if (isDateValid(_day, _month, yearToSet)) // checks if param yearToSet is valid
            _year = yearToSet;
    } // end of method setYear
    
    /**
     * Checks if 2 dates are the same
     * @param other The date to compare this date to
     * @return True if the dates are the same, otherwise false
     */
    public boolean equals(Date other){
        return _day == other._day && _month == other._month &&
        _year == other._year;
    } // end of method equals
    
    /**
     * Checks if this date is before other date
     * @param other The date to compare this date to
     * @return True if this date is before other date, otherwise false
     */
    public boolean before(Date other){
        // calculates how many days passed until the current date and assign it into currentDays
        int currentDays = calculateDate(_day, _month, _year);
        // calculates how many days passed until the other date and assign it into otherDays
        int otherDays = calculateDate(other._day, other._month, other._year);
        // if currentDays value is lesser than otherDays value so current date before other date
        return currentDays < otherDays;
    } // end of method before
    
    /**
     * Checks if this date is after other date
     * @param other The date to compare this date to
     * @return True if this date is after other date, otherwise false
     */
    public boolean after(Date other){
        return other.before(this);
    } // end of method after
    
    /**
     * Calculates the difference in days between two dates
     * @param other The date to calculate the difference between
     * @return Number of days between the dates (non negative value)
     */
    public int difference(Date other){
        int currentDays = calculateDate(_day, _month, _year);
        int otherDays = calculateDate(other._day, other._month, other._year);
        return Math.abs(currentDays - otherDays); // returns absolute value of the difference between the dates
    } // end of method difference
    
    /**
     * Returns a String that represents this date
     * @return String that represents this date in the following format:
     * day (2 digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString(){
        String day = "";
        String month = "";
        if (_day <= MAX_FOR_ZERO) // 1<=_day<=9 (_day can't be under 1)
             day += "0" + _day; // day = "0_day"
        else
            day += _day;
        if (_month <= MAX_FOR_ZERO) // 1<=month<=9 (_month can't be under 1)
            month += "0" + _month; // month = "0_month"
        else
            month += _month;
        return day + "/" + month + "/" + _year;
    } // end of method toString
    
    /**
     * Calculates the date of tomorrow
     * @return A date which represent the tomorrow day of the current date
     */
    public Date tomorrow(){
        Date tomorrow = new Date(this); // tomorrow is a copy of current date
        tomorrow.setDay(tomorrow.getDay() + INCREASE); // tomorrow day increased by 1
        if (tomorrow.getDay() == _day){ // tomorrow date is invalid after icreasion
            tomorrow.setDay(RESET); // tomorrow day reseted to 1
            tomorrow.setMonth(tomorrow.getMonth() + INCREASE); // tomorrow month increased by 1
            if (tomorrow.getMonth() == _month){// tomorrow date is invalid after icreasion
                tomorrow.setMonth(RESET); // tomorrow month reseted to 1
                tomorrow.setYear(tomorrow.getYear() + INCREASE); // tomorrow year increased by 1
            } // end of internal if statement
        } // end of external if statement
        return tomorrow;
    } // end of method tomorrow
    
    
    
    // the method checks if the date is valid
    private boolean isDateValid(int day, int month, int year){
        if (month < START || month > MAX_MONTH) // checks if the month is valid
            return false;
        if (year < START_YEAR || year > END_YEAR) // checks if the year is valid
            return false;
        if (month == 4 || month == 6 || month == 9 ||
            month == 11) // months 4,6,9,11 in all years
                return day >= START && day <= END_DAY30; // 1<=day<=30
        // February in a leap year
        else if (((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) && month == 2)
            return day >= START && day <= END_DAY29; // 1<=day<=29
        // February in a non-leap year
        else if (month == 2)
            return day >= START && day <= END_DAY28; // 1<=day<=28
        else // months 1,3,5,7,8,10,12 in all years
            return day >= START && day <= END_DAY31; // 1<=day<=31
    } // end of method isDateValid
    
    
    // computes the day number since the beginning of the Christian counting of years
    private int calculateDate(int day, int month, int year){
        if (month < 3){
        year--;
        month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    } // end of method calculateDate

} // end of class Date
