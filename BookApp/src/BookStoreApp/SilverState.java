/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BookStoreApp;

/**
 *
 * @author i2wahid
 */

//concrete state - state pattern
public class SilverState implements CustomerState {
    @Override 
    public double calculateCost (double totalCost, Customer c){
        return totalCost;
    }
   
    @Override
    public void updateState(Customer c){
        //Go to gold state, else remain in silver state
        if (c.getPoints()== 1000 || c.getPoints()> 1000){
            c.setState(new GoldState());
        }
    } 
}