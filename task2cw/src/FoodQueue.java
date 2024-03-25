import java.io.FileWriter;
import java.io.IOException;

public class FoodQueue {
    Customer[] queueArray;

    public FoodQueue(int size){
        queueArray = new Customer[size];
    }
        // Constructor to create a FoodQueue object with a given size
    public String validateArray(int i){ // Check if the array at the specified index is null or not
        try{
            if(queueArray[i] == null) {
                return "X"; // Empty
            }
            else {
                return "0"; // Occupied
            }
            } catch(Exception e){ // Exception occured
            return " ";
        }
    }
    public void addcustomer(Customer customer){ // Add a customer to the first available position in the queue
        for(int i = 0; i<queueArray.length; i++){
            if(queueArray[i] == null){
                queueArray[i] = customer;
                break;
            }
        }
    }
    public boolean checkEmpty(){
        return queueArray[0] == null;
    } //check if the queue is empty
    public boolean viewFullQueue(){ // check if the queue is full
        for (Customer customer : queueArray){
            if(customer == null){
                return false; // Not full
            }
        }
        return true; // Full
    }
    public boolean indexCheck(int i ) {
        return queueArray[i] == null;
    }
    public int queueSize(){ // get the size of the queue.
        return queueArray.length;
    }
    public void removeName(int i){
        System.out.println( queueArray[i].fullName() + " has been removed" );
    } //Remove the customer at the specified index.
    public Customer customerSee(int i){
        return queueArray[i];
    } // get the customer at the specified index.
    public void setPositionToNull(int i){
        queueArray[i] = null;
    }

    public void moveCustomersForward(int position){  // Move customers forward by one position starting from the specified position
        for(int i = position - 1 ; i < queueArray.length - 1; i++){
            if(indexCheck(i) && !indexCheck(i + 1)) {
                queueArray[i] = queueArray[i+1];
                queueArray[i+1] = null;
            }
        }
    }
    public void customersSave(FileWriter dataFile) throws IOException { //Save customer details into a file
        for(Customer customer : queueArray){
            if(customer == null){
                dataFile.write("null \n"); //Mark an empty position
            }else{
                dataFile.write(customer.CustomerFullDetails() +"\n");
            }
        }
    }
    public void customerDetails(int i, String firstName, String surname, String burgers){
        int noOfBurgers = Integer.parseInt(burgers);
        Customer customer = new Customer(firstName, surname, noOfBurgers);
        queueArray[i] = customer;
    }
    public int incomePerQueue(){
        // calculate the income for the queue.
        int income = 0 ;
        for (Customer customer : queueArray){
            if(customer!= null){
                income += customer.getNoOfBurgers() * 650; //Assuming each burger costs 650
            }
        }
        return income ;
    }
}



