package f17comp1011w3prep;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author JWright
 */
public class LinkTest {
    public static void main(String[] args)
    {
        String[] colors = {"black", "yellow", "green", "blue", "violet", "silver"};
        
        List<String> list1 = new LinkedList<>();
        
        for (String color : colors)
            list1.add(color);
        
        //add colors2 elements to list2
        String[] colors2 = {"gold","white","brown","blue","gray","silver"};
        List<String> list2 = new LinkedList<>();
        
        for(String color:colors2)
            list2.add(color);
        
        list1.addAll(list2); //concatenate lists
        list2 = null;   //release resources
        printList(list1); //print list1 elements
        
        convertToUpperCaseStrings(list1);
        printList(list1);
        
        System.out.printf("%nDeleting elements 4 to 6...");
        removeItems(list1, 4, 7);
        printList(list1);
        printReversedList(list1);
    }
    
    private static void printList(List<String> list)
    {
        System.out.printf("%nlist:%n");
        
        for (String color : list)
            System.out.printf("%s ", color);
        
        System.out.println();
    }
    
    
    private static void convertToUpperCaseStrings(List<String> list)
    {
        ListIterator<String> iterator = list.listIterator();
        
        while (iterator.hasNext())
        {
            String color = iterator.next(); //get the item
            iterator.set(color.toUpperCase());
        }
    }
    
    
    private static void removeItems(List<String> list, int start, int end)
    {
        list.subList(start, end).clear(); //remove items
    }
    
    public static void printReversedList(List<String> list)
    {
        ListIterator<String> iterator = list.listIterator(list.size());
        
        System.out.printf("%nReversed list:");
        
        while (iterator.hasPrevious())
            System.out.printf("%s ", iterator.previous());
    }
}
