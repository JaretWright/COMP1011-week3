package f17comp1011w3prep;

import java.util.Scanner;

/**
 *
 * @author JWright
 */
public class RegexExamples {
    public static void main(String[] args)
    {
        
        Scanner keyboard = new Scanner(System.in);
        System.out.printf("Enter an area code or phone number: ");
        String userInput = keyboard.nextLine();
        
        System.out.printf("That is a%s area code%n", checkAreaCode(userInput)? " valid":"n invalid" );
        System.out.printf("That is a%s phone #%n", checkPhoneNumber(userInput)? " valid":"n invalid" );        
        
        System.out.printf("Enter your first name: ");
        String name = keyboard.nextLine();
        
        System.out.printf("%s is a%s first name%n", name, checkName(name)? " valid":"n invalid" );
        
    }
    
    public static boolean checkAreaCode(String areaCode)
    {
        return areaCode.matches("[2-9]\\d{2}");
    }
    
    public static boolean checkPhoneNumber(String phone)
    {
        return phone.matches("\\(?[2-9]\\d{2}\\)?[-.\\s]?[2-9]\\d{2}[-.\\s]?\\d{4}");
    }
    
    public static boolean checkName(String name)
    {
        return name.matches("[A-Z][a-zA-Z]*");
    }
}
