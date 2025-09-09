
/**
 * Write a description of class TicketMachine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TicketMachine
{
    private int price;
    
    private int balance;
    
    private int total;
    
    public TicketMachine(int cost){
        price = cost;
        balance = 0;
        total = 0;
    }
    
    public int getPrice(){
        return price;
    }
    
    public int getBalance(){
        return balance;
    }
    
    public void insertMoney(int amount){
        if(amount > 0){
            balance = balance + amount;
        } else {
            System.out.println("Use a positive amount rather than: "+ amount);
        }
        
    }
    
    public void printTicket(){
        if(balance >= price){
            System.out.println("##################");
            System.out.println("# The BlueJ Line");
            System.out.println("# Ticket");
            System.out.println("# " + price + " cents.");
            System.out.println("##################");
            System.out.println();
            total = total + price;
            balance = balance - price;
        } else {
            System.out.println("You must insert at least: " + 
                (price - balance) + " cents.");
            
        }
    }
    
    public int refundBalance(){
            int amountToRefund;
            amountToRefund = balance;
            balance = 0;
            return amountToRefund;
    }
}