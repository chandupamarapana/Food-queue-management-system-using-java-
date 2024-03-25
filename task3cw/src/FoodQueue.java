import java.io.FileWriter;
import java.io.IOException;

public class FoodQueue {
    Customer[] queueArray;

    public FoodQueue(int size){
        queueArray = new Customer[size];
    }

    public String validateArray(int i){
        try{
            if(queueArray[i] == null) {
                return "X";
            }
            else {
                return "0";
            }
        } catch(Exception e){
            return " ";
        }
    }
    public void addcustomer(Customer customer){
        for(int i = 0; i<queueArray.length; i++){
            if(queueArray[i] == null){
                queueArray[i] = customer;
                break;
            }
        }
    }
    public boolean checkEmpty(){
        return queueArray[0] == null;
    }
    public boolean viewFullQueue(){
        for (Customer customer : queueArray){
            if(customer == null){
                return false;
            }
        }
        return true;
    }
    public boolean indexCheck(int i ) {
        return queueArray[i] == null;
    }
    public int queueSize(){
        return queueArray.length;
    }
    public void removeName(int i){
        System.out.println( queueArray[i].fullName() + " has been removed" );
    }
    public Customer customerSee(int i){
        return queueArray[i];
    }
    public void setPositionToNull(int i){
        queueArray[i] = null;
    }

    public void moveCustomersForward(int position){
        for(int i = position - 1 ; i < queueArray.length - 1; i++){
            if(indexCheck(i) && !indexCheck(i + 1)) {
                queueArray[i] = queueArray[i+1];
                queueArray[i+1] = null;
            }
        }
    }
    public void customersSave(FileWriter dataFile) throws IOException {
        for(Customer customer : queueArray){
            if(customer == null){
                dataFile.write("null\n");
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
        int income = 0 ;
        for (Customer customer : queueArray){
            if(customer!= null){
                income += customer.getNoOfBurgers() * 950;
            }
        }
        return income ;
    }
}



