import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String[] cashierOne = new String[2];
        String[] cashierTwo = new String[3];
        String[] cashierThree = new String[5];
        int no_of_burgers = 50;  //The total number of burgers.
// The interface.
        System.out.println(""" 
                 100 or VFQ: view all Queues
                            101 or VEQ: View all Empty Queues
                            102 or ACQ: Add customer to a Queue.
                            103 or RCQ: Remove a customer from a Queue. (From a specific location)
                            104 or PCQ: Remove a served customer.
                            105 or VCS: View Customers Sorted in alphabetical order\s
                            106 or SPD: Store Program Data into file.
                            107 or LPD: Load Program Data from file.
                            108 or STK: View Remaining burgers Stock.
                            109 or AFS: Add burgers to Stock.
                            999 or EXT: Exit the Program
                                        
                                       
                            X= Not occupied 0= Occupied
                                
                """);
        while (true) {
            String inputWord = "";
            int inputNumber = 0;
            Scanner input = new Scanner(System.in);
            System.out.print("Enter Your inputs: ");
            // checks if the inputs are int or strings
            if (input.hasNextInt()) {
                int userNumber = input.nextInt();
                inputNumber += userNumber;
            } else {
                String userWord = input.nextLine();
                inputWord += userWord;
            }
            if (inputNumber == 100 || inputWord.equalsIgnoreCase("vfq")) {
                queue(cashierOne, cashierTwo, cashierThree);
            } else if (inputNumber == 101 || inputWord.equalsIgnoreCase("veq")) {
                methodForEmptyQueues(cashierOne, cashierTwo, cashierThree);
            } else if (inputNumber == 102 || inputWord.equalsIgnoreCase("acq")) {
                try {
                    System.out.print("Enter the queue: ");
                    int queueAdd = input.nextInt();
                    System.out.print("Enter your name: ");
                    String name = input.next();
                    switch (queueAdd) {  //To add a new customers for the queues
                        case 1:
                            newCustomerAdd(cashierOne, name);
                            break;
                        case 2:
                            newCustomerAdd(cashierTwo, name);
                            break;
                        case 3:
                            newCustomerAdd(cashierThree, name);
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                } catch (Exception exception) {
                    System.out.println("invalid input");
                }
            } else if (inputNumber == 103 || inputWord.equalsIgnoreCase("RCQ")) {
                try {
                    System.out.println("Queue: ");
                    int removeCustomer = input.nextInt();
                    System.out.print("Position you want to remove: ");
                    int place = input.nextInt();
                    switch (removeCustomer) {
                        case 1:
                            customerRemove(cashierOne, place);
                            break;
                        case 2:
                            customerRemove(cashierTwo, place);
                            break;
                        case 3:
                            customerRemove(cashierThree, place);
                            break;
                        default:
                            System.out.print("invalid input ");
                            break;
                    }
                } catch (Exception exception) {
                    System.out.print("invalid input");
                }
            } else if (inputNumber == 104 || inputWord.equalsIgnoreCase("PCQ")) {
                try {
                    System.out.print("Queue: ");
                    int customer = input.nextInt();
                    switch (customer) {
                        case 1:
                            no_of_burgers = remove_served_customer(cashierOne, no_of_burgers);
                            break;
                        case 2:
                            no_of_burgers = remove_served_customer(cashierTwo, no_of_burgers);
                            break;
                        case 3:
                            no_of_burgers = remove_served_customer(cashierThree, no_of_burgers);
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                } catch (Exception exception) {
                    System.out.println("invalid input");
                }
            } else if (inputNumber == 105 || inputWord.equalsIgnoreCase("vcs")) {
                sortingMethod(cashierOne, cashierTwo, cashierThree);
            } else if (inputNumber == 106 || inputWord.equalsIgnoreCase("spd")) {
                fileStoring(no_of_burgers,cashierOne,cashierTwo,cashierThree);
            } else if (inputNumber == 107 || inputWord.equalsIgnoreCase("lpd")) {
                loadingData(no_of_burgers, cashierOne, cashierTwo, cashierThree);
            }else if (inputNumber==108 || inputWord.equalsIgnoreCase("STk")){ //Checking how many burgers are left.
                System.out.println("there are " + no_of_burgers+ " left ");
            }
            else if (inputNumber == 109 || inputWord.equalsIgnoreCase("AFS")){
                try{
                    System.out.print("How many burgers to add : "); //Adding burgers
                    int burgersToAdd = input.nextInt();
                    if(burgersToAdd>0) {
                        if(burgersToAdd + no_of_burgers > 50){
                            System.out.println("cant store more than 50 burgers at once");
                        }else{
                            no_of_burgers +=  burgersToAdd;
                            System.out.println("burgers added successfully");
                        }
                    }else{
                        System.out.println("Invalid input");
                    }
                }catch(Exception e){
                    System.out.println("Invalid input");
                }
            }
            else if (inputNumber == 999 || inputWord.equalsIgnoreCase("EXT")) {
                System.out.println("Now you will exit the program. Thank you ");
                break;

            }
            else {
                System.out.println("Invalid input");
            }

            if(no_of_burgers <= 10){
                System.out.println();
                System.out.println("Alert");
                System.out.println("There are only " +no_of_burgers + " burgers left ");

            }
        }



    }




    public static void queue(String[] arrayOne, String[] arrayTwo, String[] arrayThree) {
        String[] cashierOne = copyArray(arrayOne);
        String[] cashierTwo = copyArray(arrayTwo);
        String[] cashierThree = copyArray(arrayThree);

        System.out.println();
        System.out.println("***********");
        System.out.println("* Cashier *");
        System.out.println("***********");

        for (int i = 0; i < 2; i++) {
            System.out.println("  " + cashierOne[i] + ("  ") + cashierTwo[i] + "  " + cashierThree[i]);
        }

        System.out.println("     " + cashierTwo[2] + "  " + cashierThree[2]);
        System.out.println("        " + cashierThree[3]);
        System.out.println("        " + cashierThree[4]);

// This method was used to display all the queues.

    } 


    public static String[] copyArray(String[] array) {
        String[] aarray = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                aarray[i] = "X";
            } else {
                aarray[i] = "0";
            }
        }
        return aarray;
        // This method was used to assign "X" or "0"
    }

    public static boolean queuMethod2(String[] part2array) {  //The method that was used when the user entered 101 or VEQ
        for (String s : part2array) {
            if (s != null) {
                return false;
            }
        }
        return true;
    }
// This method was used to check for empty queues
    public static void methodForEmptyQueues(String[] arrayOne, String[] arrayTwo, String[] arrayThree) {
        if (queuMethod2(arrayOne) && queuMethod2(arrayTwo) && queuMethod2(arrayThree)) {
            System.out.println(" All the queues are empty");
            return;
        }
        if (queuMethod2(arrayOne)) {
            System.out.println("queue one is empty");
        }
        if (queuMethod2(arrayTwo)) {
            System.out.println("queue two is empty");
        }
        if (queuMethod2(arrayThree)) {
            System.out.println("queue three is empty");
        }
    }

    public static boolean fullQueue(String[] arr) {
        for (String s : arr) {
            if (s == null) {
                return false;
            }
        }
        return true;
    }

    public static void newCustomerAdd(String[] array1, String name) {  //This method was used to add new customers for Arrays.
        if (!fullQueue(array1)) {
            for (int i = 0; i < array1.length; i++) {
                if (array1[i] == null) {
                    array1[i] = name;
                    System.out.println(name + " is added to the respective queue ");
                    break;
                }
            }
        } else {
            System.out.println("Queue is full");
        }
    }

    public static void moveCustomersForward(String[] arr, int position) { //This method is used to move customers forward in queue if the customer was served or removed.
        for (int i = position - 1; i < arr.length - 1; i++) {
            if (arr[i] == null && arr[i + 1] != null) {
                arr[i] = arr[i + 1];
                arr[i + 1] = null;
            }
        }
    }

    public static void customerRemove(String[] arr, int position) { //This method was used to remove customers from queues.
        if (arr[position - 1] == null) {
            System.out.println("That position is empty");
        } else {
            arr[position - 1] = null;
            moveCustomersForward(arr, position);
        }
    }

    public static int remove_served_customer(String[] arr, int burgers) { //This method was used to remove a served customer from the respective queues.
        if (arr[0] == null) {
            System.out.println("No one to serve");
        } else {
            System.out.println(arr[0] + "  has been served 5 burgers");
            arr[0] = null;
            burgers -= 5;
            moveCustomersForward(arr, 1);
        }
        return burgers;
    }

    public static void sortingMethod(String[] arrayOne, String[] arrayTwo, String[] arrayThree) { //sorting methods
        String[] customers = new String[arrayOne.length + arrayTwo.length + arrayThree.length];
        int index = 0;

        // Merge all arrays into one
        for (int i = 0; i < arrayOne.length; i++) {
            if (arrayOne[i] != null) {
                customers[index] = arrayOne[i];
                index++;
            }
        }
        for (int i = 0; i < arrayTwo.length; i++) {
            if (arrayTwo[i] != null) {
                customers[index] = arrayTwo[i];
                index++;
            }
        }
        for (int i = 0; i < arrayThree.length; i++) {
            if (arrayThree[i] != null) {
                customers[index] = arrayThree[i];
                index++;
            }
        }

        // Perform bubble sort
        for (int i = 0; i < customers.length - 1; i++) {
            for (int j = 0; j < customers.length - 1 - i; j++) {
                if (customers[j] != null && customers[j + 1] != null) {
                    if (compareNames(customers[j], customers[j + 1])) {
                        // Swap elements if they are out of order
                        String temp = customers[j];
                        customers[j] = customers[j + 1];
                        customers[j + 1] = temp;
                    }
                }
            }
        }

        // Print sorted customers
        System.out.println("Customers sorted alphabetically: ");
        for (String customer : customers) {
            if (customer != null) {
                System.out.println(customer);
            }
        }
    }

    // Compare two names lexicographically
    public static boolean compareNames(String first, String second) {
        int length = Math.min(first.length(), second.length());
        for (int i = 0; i < length; i++) {
            if (first.charAt(i) > second.charAt(i)) {
                return true;
            } else if (first.charAt(i) < second.charAt(i)) {
                return false;
            }
        }
        return first.length() > second.length();
    }
    public static void fileStoring( int burgers , String[] Array1, String[] Array2 , String[] Array3){ //This method was used to store the respective data in a textfile.
        try{
            FileWriter dataSaver = new FileWriter("dataFile.txt");;
            dataSaver.write(burgers + "\n");
            storeCashier(Array1,dataSaver);
            storeCashier(Array2,dataSaver);
            storeCashier(Array3,dataSaver);
            dataSaver.close();
            System.out.println("Data stored");
        }catch(Exception e){
            System.out.println("An error occurred");
        }
    }
    public static void storeCashier(String[] arr, FileWriter dataSaver) throws IOException {
        for (String s : arr) {
            if (s == null) {
                dataSaver.write("null\n");
            } else {
                dataSaver.write(s + "\n");
            }
        }
    }
    public static int loadingData(int burger,  String[] arrOne, String[] arrTwo, String[] arrThree){ // This method was used to load data from the previous data list.
        try {
            File loadFile = new File("dataFile.txt");
            Scanner fileScanner = new Scanner(loadFile);
            burger = Integer.parseInt(fileScanner.nextLine());
            loadCustomers(arrOne, fileScanner);
            loadCustomers(arrTwo, fileScanner);
            loadCustomers(arrThree, fileScanner);
            fileScanner.close();

        }catch (Exception e){
            System.out.println("An error occurred");
        }
        return burger;
    }
    public static void loadCustomers(String[] arr, Scanner scan){
        for(int i = 0; i < arr.length; i++){
            String name = scan.nextLine();
            if(name.equals("**")){
                arr[i] = null;
            }else{
                arr[i] = name;
            }
        }
    }



    }











