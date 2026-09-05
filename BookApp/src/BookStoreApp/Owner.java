/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BookStoreApp;

/**
 *
 * @author i2wahid
 */

public class Owner extends User {
    //consructor:
    public Owner (String username, String password){
        super(username, password); //call User constructor
    }
    
    // too add book in the BookStore singleton object
    public void addBook(BookStore store, String name, double price){
        store.addBook(name, price); // call the addBook method in the BookStore class
    }
    
    //to remove a book from the BookStore singleton object
    public void deleteBook(BookStore store, Book book){
        store.removeBook(book);
    }
    
    //adds customer into the BookStore singleton object appropriatley
    public void addCustomer(BookStore store, String username, String password){
        store.addCustomer(username, password);
    }
    
    //removes a specific customer from the singleton BookStore object
    public void deleteCustomer(BookStore store, Customer c){
        store.removeCustomer(c);
    }
}