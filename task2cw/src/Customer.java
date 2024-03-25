import java.util.Scanner;
public class Customer {
    private String firstName;
    private String surname;
    private int noOfBurgers;

    public Customer(Scanner input) {  //Constructor to create a Customer object with input
        System.out.print("First Name of customer: ");
        firstName = input.next();
        System.out.print("Surname of the customer: ");
        surname = input.next();
        do {
            try {
                input.nextLine();//input buffer
                System.out.print("Burgers: ");
                noOfBurgers = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid Input");
            }
        } while (noOfBurgers <= 0);
    }
    public Customer(String firstName, String surname, int noOfBurgers){
        this.firstName = firstName;
        this.surname = surname;
        this.noOfBurgers = noOfBurgers;
    }
    public String fullName(){
        return firstName + " " + surname;
    } //Returns the full name of the customer.

    public int getNoOfBurgers(){
        return noOfBurgers;
    }
    // Returns the number of burgers ordered by the customer.
    public String CustomerFullDetails(){ return firstName + "-" + surname+ "-" + noOfBurgers;}
    // Returns the full details of the customer


}

