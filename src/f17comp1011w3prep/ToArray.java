package f17comp1011w3prep;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author JWright
 */
public class ToArray {
    public static void main(String[] args)
    {
        String[] colors = {"black","blue","yellow"};
        LinkedList<String> links = new LinkedList<>(Arrays.asList(colors));
        
        links.addLast("red");
        links.add("pink");
        links.add(3, "green");
        links.addFirst("cyan");
        
        colors = links.toArray(new String[links.size()]);
        
        System.out.println("colors: ");
        
        for (String color : colors)
            System.out.printf("%s ",color);
        
        System.out.println("\n\nPrinting as a LinkedList:");
        printList(links);
    }
    
    public static void printList(LinkedList<String> list)
    {
        for (String item : list)
            System.out.printf("%s ", item);
        System.out.println();
    }
}
