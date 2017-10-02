package f17comp1011w3prep;

import java.util.Comparator;

/**
 *
 * @author JWright
 */
public class TimeComparator implements Comparator<Time>{
    @Override
    public int compare(Time time1, Time time2)
    {
        int hourDifference = time1.getHours() - time2.getHours();
        
        if (hourDifference != 0)
            return hourDifference;
        
        int minuteDifference = time1.getMinutes() - time2.getMinutes();
        
        if (minuteDifference != 0)
            return minuteDifference;
        
        int secondsDifference = time1.getSeconds() - time2.getSeconds();
        
        return secondsDifference;
    }
}
