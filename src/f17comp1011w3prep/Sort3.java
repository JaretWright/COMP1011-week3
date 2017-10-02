package f17comp1011w3prep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author JWright
 */
public class Sort3 {
    public static void main(String[] args)
    {
        List<Time> list = new ArrayList<>();
        
        list.add(new Time(6, 24,34));
        list.add(new Time(18,14,58));
        list.add(new Time(6,05,34));
        list.add(new Time(12,14,58));
        list.add(new Time(6, 24, 22));
        
        System.out.printf("Unsorted list elements: %n%s%n", list);
        
        Collections.sort(list, new TimeComparator());
        
        System.out.printf("%nSorted list elements: %n%s%n", list);
    }
}
