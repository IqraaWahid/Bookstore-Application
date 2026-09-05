/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author i2wahid
 */


package BookStoreApp;
import java.util.List;

//context file - state pattern
public class Customer extends User {
    //instance variables:
    private int points;
    private CustomerState Customerstatus; //state pattern
  
    public Customer(String username, String password) {
        super(username, password);
        points = 0; //new customers
        //state pattern
        Customerstatus = new SilverState(); //customers with less than 1000 points start at silver
    }
    
    //calculates the cost of all books a customer will buy
    public double buyBooks(List<Book> books) {
        double totalCost = 0;
        for (int i = 0; i < books.size(); i++){
            //get each book from the list
            Book cost = books.get(i);

            //access the price and calculate the total
            totalCost = cost.getPrice() + totalCost;
        }

        earnPoints(totalCost); //customer gets points added

        return totalCost;

    }

    public double redeemandBuy(List<Book> books) {
        int pointsUsed = 0;
        double totalCost= 0; 
        double finalCost;
        int currentPoints;
        int pointsNeeded;
        double pointsWorth;

        //calculate the total cost from books
        for (int i = 0; i < books.size(); i++){
            Book cost = books.get(i);
            totalCost = cost.getPrice() + totalCost;
        }

        currentPoints = getPoints();   

        //The number of points needed to pay for the books
        pointsNeeded = (int) (totalCost * 100);

        //use only the points needed to cover the cost, keep remaining points. 
        if (pointsNeeded == currentPoints || pointsNeeded<currentPoints  ){

            pointsUsed = pointsNeeded;

            pointsWorth = pointsUsed/100.0;

            finalCost = totalCost - pointsWorth;

            points = currentPoints - pointsUsed; //update points.         
        }

        else { //use all available points. After purchase will be 0 
            pointsUsed = currentPoints;
            pointsWorth = pointsUsed/100.0;
            finalCost= totalCost - pointsWorth;
            points = 0; 
        }

        if (finalCost< 0 ){
            finalCost=0;     
        }

        // earn points from the remaining amount paid
        earnPoints(finalCost);

        // update customer state after points change
        Customerstatus.updateState(this);

        return finalCost;
    }

    public int earnPoints(double cost) {
        int pointsEarned = (int)(cost * 10);
        addPoints(pointsEarned);
        return pointsEarned;
    }

    public void setState(CustomerState s){
        Customerstatus = s;
    }

    public CustomerState getState(){
        return Customerstatus;
    }

    public int getPoints(){
        return points;
    }

    public void addPoints( int p){
        points = points + p;
        Customerstatus.updateState(this);
    }
}