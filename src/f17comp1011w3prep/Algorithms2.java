package f17comp1011w3prep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author JWright
 */
public class Algorithms2 {
    public static void main(String[] args)
    {
        String[] colors = {"red", "white", "yellow","blue"};
        List<String> list1 = Arrays.asList(colors);
        ArrayList<String> list2 = new ArrayList<>();
        
        list2.add("black");
        list2.add("red");
        list2.add("green");
        
        System.out.printf("Before addAll, list2 contains: ");
        System.out.printf("%s%n", list2);
        
        Collections.addAll(list2, colors);
        
        System.out.printf("%nAfter addAll, list2 contains: %s%n",list2);
        
        //get frequency of red
        int frequency = Collections.frequency(list2, "red");
        System.out.printf("%nFrequency of red in list2: %d%n", frequency);
        
        //check whether list1 and list2 have elements in common
        boolean disjoint = Collections.disjoint(list1, list2);
        System.out.printf("%ndisjoint = %s%n", disjoint);
        
        System.out.printf("%nlist1 and list2 %s elements in common%n",
                (disjoint ? "do not have" : "have"));
    }
}
