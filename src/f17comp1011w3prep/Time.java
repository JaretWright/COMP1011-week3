package f17comp1011w3prep;

/**
 *
 * @author JWright
 */
public class Time {

    private int hours, minutes, seconds;
    
    public Time()
    {
        setTime(0,0,0);
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
    
    
    public Time(int hours, int minutes, int seconds)
    {
        setTime(hours, minutes, seconds);
    }
    
    /**
     * This method will validate and set the instance variables
     * @param hours - 0 to 23 inclusive
     * @param minutes - 0 to 59 inclusive
     * @param seconds - 0 to 59 inclusive
     */
    public void setTime(int hours, int minutes, int seconds)
    {
        if (hours < 0 || hours >= 24)
            throw new IllegalArgumentException("Hours must be in the range 0-23");
        if (minutes <0 || minutes >= 60)
            throw new IllegalArgumentException("Minutes must be in the range 0-59");
        if (seconds <0 || seconds >= 60)
            throw new IllegalArgumentException("Seconds must be in the range 0-59");
        
        this.hours=hours;
        this.minutes=minutes;
        this.seconds=seconds;
    }
    
    
    /**
     * This method returns the time object in Military (24 hour) format
     * @return 
     */
    public String toMilitaryTime()
    {
        return String.format("%02d:%02d:%02d",hours, minutes, seconds);
    }
    
    
    /**
     * This method will return the time in standard format (AM/PM)
     * @return 
     */
    public String toStandardTime()
    {
        return String.format("%d:%02d:%02d %s",
                        ((hours == 0) || (hours == 12) ? 12 : hours % 12),
                        minutes,
                        seconds,
                        (hours >= 12) ? "PM" : "AM");
    }
    
    public String toString()
    {
        return toStandardTime();
    }
}
