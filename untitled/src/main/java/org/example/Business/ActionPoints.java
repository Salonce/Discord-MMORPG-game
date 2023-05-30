package org.example.Business;

import org.example.Exceptions.NotEnoughActionPointsException;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

public class ActionPoints{
    // Duration/period operates on temporals -> duration operates on instants (instant belongs to Temporal)
    public static final int MAX_AP = 50;
    public static final int AP_RECOVERY_TIME = 30;

    public ActionPoints(){
        //this.action_points = ActionPoints.MAX_AP;
    }
    private ArrayList<Instant> timeList = new ArrayList<>();
    //private int action_points;

    public int getCurrentAP(){
        cleanNegativeCooldowns();
        return (MAX_AP - timeList.size());
    }

    private Instant getLastTimeListInstant(){
        Instant lastInstant;
        if (!timeList.isEmpty())
            lastInstant = timeList.get(timeList.size() - 1);
        else{
            lastInstant = Instant.now();
        }
        return lastInstant;
    }

    private void cleanNegativeCooldowns(){
        Iterator<Instant> cooldownIterator = timeList.iterator();
        while(cooldownIterator.hasNext()){
            if (Instant.now().compareTo(cooldownIterator.next()) >= 0){
                cooldownIterator.remove();
            }
        }
    }

    public void addCooldown(int number) throws NotEnoughActionPointsException {
        if ((getCurrentAP() - number) >= 0) {
            for (int i = 0; i < number; i++)
                timeList.add(getLastTimeListInstant().plusSeconds(AP_RECOVERY_TIME));
        }
        else{
            throw new NotEnoughActionPointsException(String.valueOf(number));
        }
    }

    public long getFirstCD(){
        cleanNegativeCooldowns();
        try {
            Duration duration = Duration.between(Instant.now(), timeList.get(0));
            if (duration.isPositive()) {
                return duration.getSeconds();
            }
        } catch (IndexOutOfBoundsException e){
            //do nothing
        }
        return 0;
    }
    public long getLastCD(){
        cleanNegativeCooldowns();
        Duration duration = Duration.between(Instant.now(), getLastTimeListInstant());
        if (duration.isPositive()){
            return duration.getSeconds();
        }
        return 0;
    }




    ///// For testing
    /*
    public void printCooldowns(){
        cleanNegativeCooldowns();
        Iterator<Instant> cooldownIterator = timeList.iterator();
        while(cooldownIterator.hasNext()){
            Duration duration = Duration.between(Instant.now(), cooldownIterator.next());
            if (duration.isPositive()){
                System.out.println("seconds of duration: " + duration.getSeconds());
            }
            else;
        }
    }
    */
}
