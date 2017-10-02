package f17comp1011w3prep;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author JWright
 */
public class Algorithms1 {
    public static void main(String[] args)
    {
        Character[] letters = {'P', 'C','M'};
        List<Character> list = Arrays.asList(letters);
        output(list);
        
        //reverse and display the the List<Character>
        System.out.printf("Reversing the list%n");
        Collections.reverse(list);
        output(list);
        
        // create copyList from an array of 3 characters
        Character[] letterCopy = new Character[3];
        List<Character> copyList = Arrays.asList(letterCopy);
        
        //copy the contents of the list into copyList
        System.out.printf("After copying, copyList contains:%n");
        Collections.copy(copyList, list);
        output(copyList);
        
        //fill list with Rs
        Collections.fill(list, 'R');
        System.out.printf("After calling fill, list contains:%n");
        output(list);
    }
    
    public static void output(List<Character> listRef)
    {
        System.out.printf("The list is: %s%n", listRef);
        System.out.printf("Max: %s   Min: %s%n%n", Collections.max(listRef),
                                               Collections.min(listRef));
    }
}
