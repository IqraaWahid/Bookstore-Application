/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BookStoreApp;

/**
 *
 * @author i2wahid
 */

//state interface file - state pattern
interface CustomerState {
    public double calculateCost(double totalCost, Customer c); 
    public void updateState(Customer c);
}