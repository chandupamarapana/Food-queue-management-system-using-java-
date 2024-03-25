import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static int no_of_burgers = 50;
    public static void main(String[] args) {
        FoodQueue queueOne = new FoodQueue(2);
        FoodQueue queueTwo = new FoodQueue(3);
        FoodQueue queueThree = new FoodQueue(5);
        FoodQueue waitingQueue = new FoodQueue(50);
        Scanner input = new Scanner(System.in);

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
                            110 or IFQ: income of each queue.
                            999 or EXT: Exit the Program
                                        
                                       
                            X= Not occupied 0= Occupied
                            The price of one burger is 650.
                                
                """);
        while (true) {
            String inputWord = "";
            int inputNumber = 0;
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
                queue(queueOne, queueTwo, queueThree);
            } else if (inputNumber == 101 || inputWord.equalsIgnoreCase("veq")) {
                checkEmptyQueue(queueOne, queueTwo, queueThree);
            } else if (inputNumber == 102 || inputWord.equalsIgnoreCase("acq")) {
                minimumLengthQueue(queueOne, queueTwo, queueThree, waitingQueue,input);
            } else if (inputNumber == 103 || inputWord.equalsIgnoreCase("rcq")) {
                removeCustomer(queueOne, queueTwo, queueThree, input,waitingQueue);
            } else if (inputNumber == 104 || inputWord.equalsIgnoreCase("pcq")) {
                removeServeCustomer(queueOne,queueTwo,queueThree,input,waitingQueue);
            } else if (inputNumber == 105 || inputWord.equalsIgnoreCase("vcs")) {
                sortMethod(queueOne,queueTwo,queueThree);
            } else if (inputNumber == 106 || inputWord.equalsIgnoreCase("spd")) {
                fileStoring(queueOne,queueTwo,queueThree,waitingQueue);
            } else if (inputNumber == 107 || inputWord.equalsIgnoreCase("lpd")) {
                loadData(queueOne,queueTwo,queueThree,waitingQueue);
            } else if (inputNumber == 108 || inputWord.equalsIgnoreCase("stk")) {
                remainingBurgers();
            } else if (inputNumber == 109 || inputWord.equalsIgnoreCase("afs")) {
                burgersAdd(input);
            }  else if (inputNumber == 110 || inputWord.equalsIgnoreCase("ifq")) {
                printIncome(queueOne, queueTwo, queueThree);
            }else if (inputNumber == 999 || inputWord.equalsIgnoreCase("ext")) {
                System.out.println("Now you will exit the program. Thank you ");
                break;
            }
            else {
                System.out.println("invalid input");
            }
            if(no_of_burgers <= 10){
                System.out.println("Alert");
                System.out.println();
                System.out.println("there are only "+ no_of_burgers + " left");
            }
        }
    }

    public static void queue(FoodQueue cashierOne, FoodQueue cashierTwo, FoodQueue cashierThree) {
        System.out.println("""
                *****************
                **** cashier ****
                *****************
                """);
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + cashierOne.validateArray(i) + " " + cashierTwo.validateArray(i) + " " + cashierThree.validateArray(i));
        }
        System.out.println("0 = occupied X = Empty ");
    }

    public static void checkEmptyQueue(FoodQueue cashierOne, FoodQueue cashierTwo, FoodQueue cashierThree) {
        if (cashierOne.checkEmpty() && cashierThree.checkEmpty() && cashierTwo.checkEmpty()) {
            System.out.println("ALl queues are empty");
        } else if (cashierOne.checkEmpty()) {
            System.out.println("Queue one is empty");
        } else if (cashierTwo.checkEmpty()) {
            System.out.println("Queue two is empty");
        } else if (cashierThree.checkEmpty()) {
            System.out.println("Queue three is empty");
        }
    }

    public static void minimumLengthQueue(FoodQueue cashierOne, FoodQueue cashierTwo, FoodQueue cashierThree,FoodQueue waitingQueue, Scanner input) {
        Customer customer = new Customer(input);
        if (cashierOne.viewFullQueue() && cashierTwo.viewFullQueue() && cashierThree.viewFullQueue()) {
            if (waitingQueue.viewFullQueue()) {
                System.out.println("All the queues are full ");
                System.out.println(" waiting queue is also full, cannot add the customer");
            } else {
                waitingQueue.addcustomer(customer);
                System.out.println(customer.fullName() + " added to the waiting queue");
            }
        }else{
            for (int i = 0; i < 5; i++) {
                if (i == 0 || i == 1) {
                    if (cashierOne.indexCheck(i)) {
                        cashierOne.addcustomer(customer);
                        System.out.println(customer.fullName()+" added to the queue one. ");
                        break;
                    }
                }
                if (i == 0 || i == 1 || i == 2) {
                    if (cashierTwo.indexCheck(i)) {
                        cashierTwo.addcustomer(customer);
                        System.out.println(customer.fullName()+" added to the queue two");
                        break;
                    }
                }
                if (cashierThree.indexCheck(i)) {
                    cashierThree.addcustomer(customer);
                    System.out.println(customer.fullName()+" added to the queue three");
                    break;
                }
            }
        }
    }
    public static void removeCustomer(FoodQueue cashierOne, FoodQueue cashierTwo, FoodQueue cashierThree, Scanner input,FoodQueue waitingQueue){
        try {
            System.out.print("From what queue would you like to remove a customer: ");
            int queue = input.nextInt();
            System.out.print("Enter position: ");
            int position = input.nextInt();
            switch(queue){
                case 1 -> removeCustomerFromQueue(cashierOne,position,waitingQueue);
                case 2 -> removeCustomerFromQueue(cashierTwo,position, waitingQueue);
                case 3 -> removeCustomerFromQueue(cashierThree, position, waitingQueue);
                default -> System.out.println("Invalid queue" );
            }
        }catch(Exception e){
            System.out.println("Invalid input" );
        }
    }

    public  static void removeCustomerFromQueue(FoodQueue queue, int position,FoodQueue waitingQueue){
        if(queue.indexCheck(position - 1)){
            System.out.println("position is already empty" );
        }else if(queue.queueSize() < position){
            System.out.println("Invalid input");
        }else{
            queue.removeName(position - 1);
            queue.setPositionToNull(position - 1);
            queue.moveCustomersForward(position);
            addingToWaitingQueue(queue , waitingQueue);
        }
    }

    public static void removeServeCustomer(FoodQueue queueOne, FoodQueue queueTwo, FoodQueue queueThree, Scanner scan , FoodQueue waitingQueue){
        try {
            System.out.print("What queue would you like to serve: ");
            int queue = scan.nextInt();
            switch (queue) {
                case 1 -> removeServedCustomer(queueOne,waitingQueue);
                case 2 -> removeServedCustomer(queueTwo,waitingQueue);
                case 3 -> removeServedCustomer(queueThree,waitingQueue);
                default -> System.out.println("Invalid input");
            }
        }catch(Exception e){
            System.out.println("Invalid Input" );
        }
    }

    public static void removeServedCustomer(FoodQueue queue,FoodQueue waitingQueue){
        if(queue.indexCheck(0)){
            System.out.println("No one to serve");
        }else{
            if( no_of_burgers< queue.customerSee(0).getNoOfBurgers()){
                System.out.println("Don't have that many burgers in stock");
            }else{
                no_of_burgers = no_of_burgers - queue.customerSee(0).getNoOfBurgers();
                System.out.println( queue.customerSee(0).fullName() + " Was served " + queue.customerSee(0).getNoOfBurgers() + " burgers" );
                removeCustomerFromQueue(queue, 1,waitingQueue);
            }
        }
    }
    public static boolean compareTwoStrings(String One, String Two) {
        int length = Math.min(One.length(), Two.length());
        for (int i = 0; i < length; i++) {
            if (One.charAt(i) > Two.charAt(i)) {return true;}
            else if (One.charAt(i) < Two.charAt(i)) {return false;}
        }
        return One.length() > Two.length();
    }

    public static void sortMethod(FoodQueue cashierOne, FoodQueue cashierTwo, FoodQueue cashierThree) {
        ArrayList<String> allCustomers = new ArrayList<>();
        for(int i = 0; i < 5; i ++) {
            if (i == 0 || i == 1) {
                if (!cashierOne.indexCheck(i)) {
                    allCustomers.add(cashierOne.customerSee(i).fullName());
                }
                if (!cashierTwo.indexCheck(i)) {
                    allCustomers.add(cashierTwo.customerSee(i).fullName());
                }
                if (!cashierThree.indexCheck(i)){
                    allCustomers.add(cashierThree.customerSee(i).fullName());
                }
            }
        }

        for (int i = 0; i < allCustomers.size(); i++) {
            for (int j = i + 1; j < allCustomers.size(); j++) {
                if (allCustomers.get(i) != null && allCustomers.get(j) != null) {
                    if (compareTwoStrings(allCustomers.get(i), allCustomers.get(j))) {
                        String temp = allCustomers.get(i);
                        allCustomers.set(i, allCustomers.get(j));
                        allCustomers.set(j, temp);
                    }
                }
            }
        }
        System.out.println( "Customers sorted: " );
        for (String customer : allCustomers) {
            if (customer != null) {System.out.println(customer);}
        }
    }
    public static void fileStoring(FoodQueue cashierOne, FoodQueue cashierTwo, FoodQueue cashierThree, FoodQueue waitingQueue){
        try{
            FileWriter dataSaver = new FileWriter("dataFile.txt");
            dataSaver.write(no_of_burgers+"\n");
            cashierOne.customersSave(dataSaver);
            cashierTwo.customersSave(dataSaver);
            cashierThree.customersSave(dataSaver);
            waitingQueue.customersSave(dataSaver);
            dataSaver.close();
            System.out.println("Data saved successfully");
        }catch(Exception e){
            System.out.println("An error occurred");
        }
    }

    public static void loadData(FoodQueue queueOne, FoodQueue queueTwo, FoodQueue queueThree,FoodQueue waitingQueue){
        try {
            File dataFile = new File("dataFile.txt");
            Scanner fileScanner = new Scanner(dataFile);
            no_of_burgers = Integer.parseInt(fileScanner.nextLine());
            loadCustomer(queueOne, fileScanner);
            loadCustomer(queueTwo, fileScanner);
            loadCustomer(queueThree, fileScanner);
            loadCustomer(waitingQueue,fileScanner);
            fileScanner.close();
            System.out.println("Data loaded successfully");

        }catch (Exception e){
            System.out.println("An error occurred");
        }

    }

    public static void loadCustomer(FoodQueue queue, Scanner scan){
        for(int i = 0; i < queue.queueSize(); i++){
            String name = scan.nextLine();
            if(name.equals("null")){
                queue.setPositionToNull(i);
            }else{
                String[] customerDetails = name.split("-", 4);
                queue.customerDetails(i, customerDetails[0], customerDetails[1], customerDetails[2]);
            }
        }
    }

    public static void remainingBurgers(){
        System.out.println("You have " + no_of_burgers + " remaining" );
    }

    public static void burgersAdd(Scanner scan){
        try {
            System.out.print("How many burgers would you like to add: ");
            int burgersAdded = scan.nextInt();
            if((no_of_burgers + burgersAdded) > 50){
                System.out.println("You can only store 50 burgers");
                System.out.println(burgersAdded - 50 + " burgers Were removed");
                System.out.println("You currently have 50 burgers in stock");
                no_of_burgers = 50;
            }else if(burgersAdded < 0){
                System.out.println("Invalid Input" );
            }else{
                System.out.println(burgersAdded + " burgers added");
                no_of_burgers += burgersAdded;
                System.out.println("You currently have " + no_of_burgers + " in stock" );
            }
        }catch (Exception e){
            System.out.println("Invalid Input");
        }
    }
    public static void addingToWaitingQueue(FoodQueue cashier, FoodQueue waitingQueue){ //This method was created to make a waiting queue.
        if(!waitingQueue.checkEmpty()){
            cashier.customerDetails(cashier.queueSize()-1,waitingQueue.customerSee(0).getFirstName(),waitingQueue.customerSee(0).getSurname(),Integer.toString(waitingQueue.customerSee(0).getNoOfBurgers()));
            System.out.println(waitingQueue.customerSee(0).fullName()+" added from the waiting queue.");
              waitingQueue.setPositionToNull(0);
              waitingQueue.moveCustomersForward(1);
            }
        }
    public static void printIncome(FoodQueue cashierOne , FoodQueue cashierTwo , FoodQueue cashierThree){
        System.out.println(" First queue income: " + cashierOne.incomePerQueue());
        System.out.println("Second queue income: " + cashierTwo.incomePerQueue());
        System.out.println("Third queue income: " + cashierThree.incomePerQueue());
    }
}













