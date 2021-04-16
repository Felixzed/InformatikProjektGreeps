import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A Greep is an alien creature that likes to collect tomatoes.
 * 
 * @author Felix Zeilmann
 * @version 0.1
 */
public class Greep extends Creature
{
    // Remember: you cannot extend the Greep's memory. So:
    // no additional fields (other than final fields) allowed in this class!
    
    /**
     * Default constructor for testing purposes.
     */
    public Greep()
    {
        this(null);
        setMemory(0);
    }

    
    /**
     * Create a Greep with its home space ship.
     */
    public Greep(Ship ship)
    {
        super(ship);
    }
    

    /**
     * Do what a greep's gotta do.
     */
    public void act()
    {
        super.act();   // do not delete! leave as first statement in act().
    //Entladen von Tomaten
    if(atShip()) {
       dropTomato();
    }    
    //Code für "Herumirren", also für 20 Schritte nicht TurnHome folgen.
    //Damit die Greeps nicht Feststecken
    if (getFlag(2) && getFlag(1)) {
        if (atWorldEdge() || atWater()) {
            turn(90-Greenfoot.getRandomNumber(45));
        }
        //if (!carryingTomato() && (atWorldEdge() || atWater())) {
        //    turn(-90+Greenfoot.getRandomNumber(45));       
        //}
        //Einfacher Counter
        setMemory(getMemory()+1);
        move();
        //Sobald Counter 20 erreicht, wieder TurnHome folgen.
        if (getMemory() == 20) {
            setFlag(1, false);
            setFlag(2, false);
            setMemory(0);
        }
        return;
    }
    //Loader ist Flag 2,1 true
    //Loader-Script ausführen
    if (getFlag(2) && !getFlag(1)) {
        spit ("orange");
        loadTomato();
        if (!atFood()) {
            setFlag(2, false);
            setFlag(1, false);
        }
        return;     
    }
    if (atFood() && !carryingTomato()) {
        //Greep wird Loader, wenn er auf eine neue TomatoPile trifft.
        if (!seePaint("orange")) {
            setFlag(1, false);
            setFlag(2, true);
        }
        return;
    }
    if (carryingTomato()) {
        //Sobald der Greep an ein Hindernis gelangt soll dieser "Herumirren"
        if (atWorldEdge() || atWater()) {
                setFlag(1,true);
                setFlag(2, true);
                return;
                //Flag 1,2 True heisst "Herumirren"
        }
        spit("red");
        turnHome();
        move();
        return;
    }
    if (seePaint("red")) {
        turnHome();
        turn(180);
    }
    move();
    if (atWorldEdge() || atWater()) {
        setFlag(1,true);
        setFlag(2, true);
    }
    }



    /**
     * Is there any food here where we are? If so, try to load some!
     */
    public void checkFood()
    {
        // check whether there's a tomato pile here
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        if(tomatoes != null) {
            loadTomato();
            // Note: this attempts to load a tomato onto *another* Greep. It won't
            // do anything if we are alone here.
        }
    }


    /**
     * This method specifies the name of the author (for display on the result board).
     */
    public static String getAuthorName()
    {
        return "Felix";  // write your name here!
    }


    /**
     * This method specifies the image we want displayed at any time. (No need 
     * to change this for the competition.)
     */
    public String getCurrentImage()
    {
        if(carryingTomato())
            return "greep-with-food.png";
        else
            return "greep.png";
    }
}