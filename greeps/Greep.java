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
        if (getFlag(1)) {
        turn(90-Greenfoot.getRandomNumber(180));
        move();
        if (atWorldEdge() || atWater()) {
            setFlag(1, false);
        }
    }
        if (getFlag(2)) {
               spit ("orange");
               loadTomato();
               if (!atFood()) {
                    setFlag(2, false);
                    }
               return;     
                }
        if (carryingTomato()) {
            if(atShip()) {
                dropTomato();
            }
            else {
                //Hier ansetzen! Das Problem war, dass die Greeps
                //nicht richtig "Herumirren"
               if (atWater() || atWorldEdge()) {
                 turn(180);
                 move();
                 setFlag(1, true);
                 return;
                 }
                 else {
                 turnHome();
                 move();
                 }
            }
        }
    else {
            //Flag 2 designates Greep as loader
            //Orange communicates that loader is present at this spot
          if (atFood() && !getFlag(2))       {
               if (!seePaint("orange")) {
               move();
               setFlag(2,true);
               spit("orange");
               }
     }
     else {
     move();
     turn(15-Greenfoot.getRandomNumber(30));
                if (seePaint("purple") && !seePaint("orange")) {
                //turnHome();
                //turn (180);
             }
                if (atWater() || atWorldEdge()) {
                turn(90+45-Greenfoot.getRandomNumber(90));
             }
    }
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