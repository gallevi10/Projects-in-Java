
/**
 * This class represents a Car object
 *
 * @author Gal Levi
 * @version 20/11/2022
 */
public class Car{

    // Attributes and finals
    private int _id;
    private char _type;
    private String _brand;
    private boolean _isManual;
    private static final int START_ID = 1000000; // start of range of a valid id
    private static final int END_ID = 9999999; // end of range of a valid id
    private static final int DEFAULT_ID = 9999999;
    private static final char DEFAULT_TYPE = 'A';
    
    /**
     * Creates a new Car object.
     * id should be a 7 digits number, otherwise set it to 9999999
     * type should be 'A','B','C' or 'D', otherwise set it to 'A'
     * @param id The id of the car (7 digits number)
     * @param type The type of the car ('A','B','C' or 'D')
     * @param brand The car's brand
     * @param isManual flag indicating if the car is manual or not
     */
    public Car(int id, char type, String brand, boolean isManual){
        _id = DEFAULT_ID; // _id = 9999999
        _type = DEFAULT_TYPE; // _type = 'A'
        setId(id); // checks the param id and assign it if valid
        setType(type);// checks the param type and assign it if valid
        setBrand(brand);
        setIsManual(isManual);
    } // end of Constructor Car
    
    /**
     * Copy Constructor
     * Initializes an instance of car identical to the given car
     * @param other The car to be copied
     */
    public Car(Car other){
        _id = other._id;
        _type = other._type;
        _brand = other.getBrand();
        _isManual = other._isManual;
    } // end of Copy Constructor Car
    
    /**
     * Returns the car id
     * @return car id
     */
    public int getId(){
        return _id;
    } // end of method getId
    
    /**
     * Returns the car type
     * @return car type
     */
    public char getType(){
        return _type;
    } // end of method getType
    
    /**
     * Returns the car brand
     * @return car brand
     */
    public String getBrand(){
        return new String(_brand);
    } // end of method getBrand
    
    /**
     * Gets the isManual flag
     * @return The isManual flag
     */
    public boolean isManual(){
        return _isManual;
    } // end of method getIsManual
    
    /**
     * Sets the given id (only if the id is valid)
     * @param id The id value to be set
     */
    public void setId(int id){
        if ( id >= START_ID && id <= END_ID ) // checks if id contains 7 digits(1000000<=id<=9999999)
            _id = id;
    } // end of method setId
    
    /**
     * Sets the given type (only if the type is valid)
     * @param type The type value to be set
     */
    public void setType(char type){
        if ( type == 'A' || type == 'B' ||
        type == 'C' || type == 'D' ) // checks if type is A/B/C/D
            _type = type;
    } // end of method setType
    
    /**
     * Sets the given brand
     * @param brand The brand value to be set
     */
    public void setBrand(String brand){
        _brand = new String(brand);
    } // end of method setBrand
    
    /**
     * Sets the given isManual flag
     * @param isManual The isManual flag to be set
     */
    public void setIsManual(boolean isManual){
        _isManual = isManual;
    } // end of method setIsManual
    
    /**
     * Returns a String object that represents this car
     * @return String that represents this car in the following format:
     * id:1234567 type:B brand:Toyota gear:manual (or auto)
     */ 
    public String toString(){
        String gear;
        if(_isManual) // checks if the gear is manual/auto
            gear = "manual"; // we got true - the gear is manual
        else
            gear = "auto"; // we got false - the gear is auto
        return "id:" + _id + " type:" + _type + " brand:" + 
        _brand + " gear:" + gear;
    } // end of method toString
    
    /**
     * Checks if two cars are the same.
     * Cars are considered the same if they have the same type, brand and gear.
     * @param other The car to compare this car to
     * @return True if the cars are the same, otherwise false
     */
    public boolean equals(Car other){
        return _type == other._type && _brand.equals(other._brand)
        && _isManual == other._isManual;
    } // end of method equals
    
    /**
     * Checks if this car is better than the other car.
     * A car is considered better than another car if its type is higher.
     * If both cars have the same type, an automated car is better than a manual car.
     * @param other The car to compare this car to
     * @return True if this car is better than the other car, otherwise false
     */
    public boolean better(Car other){
        if (_type > other._type) // checks which type is greater according to UNICODE value
            return true;
        else if (_type == other._type) // the cars have the same type
            return !_isManual && other._isManual; // true if current gear is auto and other gear is manual
        else // other car is better than the current one
            return false;
    } // end of method better
    
    /**
     * Checks if this car is worse than the other car
     * @param other The car to compare this car to
     * @return True if this car is worse than the other car, otherwise false
     */
    public boolean worse(Car other){
        return other.better(this);
    } // end of method worse

} // end of class Car
