import java.util.Scanner;
public class Customer {
    private String firstName;
    private String surname;
    private int noOfBurgers;

    public Customer(Scanner input) {
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
    }

    public int getNoOfBurgers(){
        return noOfBurgers;
    }
    public String CustomerFullDetails(){ return firstName + "-" + surname+ "-" + noOfBurgers;}
    public String getFirstName() {
        return firstName;
    }
    public String getSurname() {
        return surname;
    }


}

