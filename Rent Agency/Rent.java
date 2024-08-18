
/**
 * This class represents a Rent object
 *
 * @author Gal Levi
 * @version 20/11/2022
 */
public class Rent{
    
    // Attributes and finals
    private String _name; // client name
    private Car _car; // the rented car
    private Date _pickDate; // car pickup date
    private Date _returnDate; // car return date
    private static final int PRICE_A = 100;
    private static final int PRICE_B = 150;
    private static final int PRICE_C = 180;
    private static final int PRICE_D = 240;
    private static final double WEEK_DISCOUNT = 0.9;
    
    /**
     * Creates a new Rent object
     * The return date must be at least one day after the pickup date,
     * otherwise the method sets it to one day after the pick up date.
     * @param name The client's name
     * @param car The rented car
     * @param pick The pickup date
     * @param ret The return date
     */
    public Rent(String name, Car car, Date pick, Date ret){
        setName(name);
        setCar(car);
        _pickDate = new Date(pick);
        _returnDate = new Date(ret);
        if (!_returnDate.after(_pickDate)) // _returnDate equal or before _pickDate
            setReturnDate(_pickDate.tomorrow()); // _returnDate is now _pickDate tomorrow day date
    } // end of Constructor Rent
    
    /**
     * Copy Constructor
     * Initializes an instance of rent identical to the given rent
     * @param other The rent to copy
     */
    public Rent(Rent other){
        _name = other.getName();
        _car = other.getCar();
        _pickDate = other.getPickDate();
        _returnDate = other.getReturnDate();
    } // end of copy Constructor Rent
    
    /**
     * Gets the client name
     * @return The client name
     */
    public String getName(){
        return new String(_name);
    } // end of method getName
    
    /**
     * Gets the car
     * @return The car
     */
    public Car getCar(){
        return new Car(_car);
    } // end of method getCar
    
    /**
     * Gets the pick up date
     * @return The pick up date
     */
    public Date getPickDate(){
        return new Date(_pickDate);
    } // end of method getPickDate
    
    /**
     * Gets the return date
     * @return The return date
     */
    public Date getReturnDate(){
        return new Date(_returnDate);
    } // end of method getReturnDate
    
    /**
     * Sets the client name
     * @param name The client name to set
     */
    public void setName(String name){
        _name = new String(name);
    } // end of method setName
    
    /**
     * Sets the rented car
     * @param name The rented car to set
     */
    public void setCar(Car car){
        _car = new Car(car);
    } // end of method setCar
    
    /**
     * Sets the pick up date.
     * The pick up date must be at least one day before the return date,
     * otherwise - pick up date will not change
     * @param pickDate The pick up date to set
     */
    public void setPickDate(Date pickDate){
        if(pickDate.before(_returnDate))
            _pickDate = new Date(pickDate);
    } // end of method setPickDate
    
    /**
     * Sets the return date.
     * The return date must be at least one day after the pick up date,
     * otherwise - return date will not change
     * @param returnDate The return date to set
     */
    public void setReturnDate(Date returnDate){
        if(returnDate.after(_pickDate))
            _returnDate = new Date(returnDate);
    } // end of method setReturnDate
    
    /**
     * Checks if 2 rents are the same
     * @param other The rent to check if equal to the current one
     * @return True if the rents are the same
     */
    public boolean equals(Rent other){
        return _name.equals(other._name) 
        && _car.equals(other._car)
        && _pickDate.equals(other._pickDate) 
        &&_returnDate.equals(other._returnDate);
    } // end of method equals
    
    /**
     * Returns the number of rent days
     * @return The number of rent days
     */
    public int howManyDays(){
        return _pickDate.difference(_returnDate);
    } // end of method howManyDays
    
    /**
     * Returns the rent total price
     * @return The rent total price
     */
    public int getPrice(){
        int rentDays = howManyDays();
        int numOfWeeks, notWeekDays, weekDays;
        numOfWeeks = rentDays / 7;
        weekDays = numOfWeeks * 7;
        notWeekDays = rentDays % 7;
        /*
         * finds this car type and calculates its weighted price
         * with week discount.
         */
        switch (_car.getType()){
            case 'A': return (int)(weekDays * PRICE_A * WEEK_DISCOUNT +
            notWeekDays * PRICE_A); // weekDays * 100 * 0.9 + notWeekDays * 100
            case 'B': return (int)(weekDays * PRICE_B * WEEK_DISCOUNT +
            notWeekDays * PRICE_B); // weekDays * 150 * 0.9 + notWeekDays * 150
            case 'C': return (int)(weekDays * PRICE_C * WEEK_DISCOUNT +
            notWeekDays * PRICE_C); // weekDays * 180 * 0.9 + notWeekDays * 180
            case 'D': return (int)(weekDays * PRICE_D * WEEK_DISCOUNT +
            notWeekDays * PRICE_D); // weekDays * 240 * 0.9 + notWeekDays * 240
            default: return 0; // never happens - car's type will always be A/B/C/D
        } // end of switch statement
    } // end of method getPrice
    
    /**
     * Tries to upgrade the car to a better car.
     * If the given car is better than the current car of the rent,
     * upgrades it and returns the upgrade additional cost, otherwise - doesn't upgrade
     * @param newCar The car to upgrade to
     * @return The upgrade cost
     */
    public int upgrade(Car newCar){
        int beforeUpgradePrice = getPrice(); // old price
        int difference = 0;
        int afterUpgradePrice;
        if (newCar.better(_car)){
            setCar(newCar); // this car = newCar
            afterUpgradePrice = getPrice(); // new price
            difference = afterUpgradePrice - beforeUpgradePrice;
        } // end of if statement
        return difference;
    } // end of method upgrade
    
    /**
     * Checks if there is a double listing of a rent for the same person
     * and car with an overlap in the rental days.
     * If there is - returns a new rent object with the unified dates, otherwise - returns null.
     * @param other The other rent
     * @return the unified rent or null
     */
    public Rent overlap(Rent other){
        Date otherPick = other.getPickDate(); // other pick date
        Date otherReturn = other.getReturnDate(); // other return date
        if (_car.equals(other._car) && _name.equals(other._name)){
            if (pickIsBetween(otherPick, otherReturn) &&
            returnIsBetween(otherPick, otherReturn)) // this dates are between other dates
                return new Rent(_name, _car, otherPick, otherReturn);
            if (returnIsBetween(otherPick, otherReturn)) // only this returnDate is between other dates
                return new Rent(_name, _car, _pickDate, otherReturn);
            if (pickIsBetween(otherPick, otherReturn)) // only this pickDate is between other dates
                return new Rent(_name, _car, otherPick, _returnDate);
            if (other.pickIsBetween(_pickDate, _returnDate) &&
            other.returnIsBetween(_pickDate, _returnDate)) // other dates are between this dates
                return new Rent(_name, _car, _pickDate, _returnDate);
        } // end of external if statement
        return null; // only happens when there is no overlap
    } // end of method overlap
    
    /**
     * Returns a String that represents this rent
     * @return String that represents this rent in the following format:
     * Name:Rama From:30/10/2022 To:12/11/2022 Type:B Days:13 Price:1845
     */
    public String toString(){
        char carType = _car.getType();
        int rentDays = howManyDays();
        int price = getPrice();
        return "Name:" + _name + " From:" + _pickDate + " To:" + _returnDate
        + " Type:" + carType + " Days:" + rentDays + " Price:" + price;
    } // end of method toString
    

    // checks if this pickDate is between the params dates
    private boolean pickIsBetween(Date date1, Date date2){
        // _pickDate equal or after date1 and equal or before date2
        return !_pickDate.before(date1) && !_pickDate.after(date2);
    } // end of method pickIsBetween
    
    // checks if this returnDate is between the params dates
    private boolean returnIsBetween(Date date1, Date date2){
        // _returnDate equal or after date1 and equal or before date2
        return !_returnDate.before(date1) && !_returnDate.after(date2);
    } // end of method returnIsBetween
    
} // end of class Rent
