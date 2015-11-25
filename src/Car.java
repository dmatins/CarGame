
/**
 * Author David Matins
 *
 * This class is meant to hold all attributes of a car object.
 */
public class Car {

    private float startingPosition;
    private float maxSpeed;
    private float acceleration;

    //used in graph display
    private int index;
    //used to tell if this car has been chosen in the best path
    private Boolean chosen = false;

    /*
    Constructor for Car class takes a starting position, a max speed , and a max acceleration.
     */
    public Car(int index, float startPosition, float maxSpeed, float acceleration){
        this.index = index;
        startingPosition = startPosition;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
    }

    public String toString(){
        //return "{position: "+ position + ", speed: "+speed + ", acceleration: "+acceleration+"}\n";
        return "{car: "+index+", position: "+ startingPosition + ", maxSpeed: "+maxSpeed + ", acceleration: "+acceleration+"}\n";
    }

    public void choose(Boolean isChosen){
        chosen = isChosen;
    }

    //Getters
    public int getIndex(){
        return index;
    }
    public Boolean isChosen(){
        return chosen;
    }

    public float getPosition() {
        return startingPosition;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getAcceleration() {
        return acceleration;
    }
}