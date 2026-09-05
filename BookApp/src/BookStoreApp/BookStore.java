/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BookStoreApp;

/**
 *
 * @author i2wahid
 */

import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class BookStore{
    //instance variables:
    private static BookStore instance; // singleton: desired object
    private ArrayList <Book> books;
    private ArrayList<Customer> customers;
    private Owner owner;
    
    // Singleton constructor:
    private BookStore(){
        //setting initial values of instance variables:
        books = new ArrayList<>();
        customers = new ArrayList<>();
        owner = new Owner ("admin", "admin");
    }
    
    //singleton method: returns the single object
    public static BookStore getInstance(){
        if(instance==null){ // if object alerady doesn't exist
            instance = new BookStore(); // create object
        }
        return instance; //otherwise return the object already created
    }
    
    //returns the books available
    public ArrayList<Book> getBooks(){
        return books;
    }
    
    //returns the customers
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    
    //returns the owner
    public Owner getOwner(){
        return owner;
    }
    
    // BOOK methods:
    // adds a book to the store (only if the book is not in the store)
    public void addBook(String name, double price){
        for(Book b : books){ // enhanced for loop to check all the book in the store
            if(b.getName().equalsIgnoreCase(name)){ // checks if the book name matches any book name already in the store (in the books ArrayList)
                return; // if match found, then end this method (meaning no book added)
            }
        }
        books.add(new Book(name, price)); // otherwise add the book (object) to the store (books ArrayList)
    }
    
    //removes a specific book from the store
    public void removeBook(Book b){
        books.remove(b);
    }
    
    //CUSTOMER methods:
    //Adds a new customer, if they don't have an account already:
    public void addCustomer(String username, String password){
        for(Customer c : customers){ // using enhanced for loop to check if this username already exists in the system
            if(c.getUsername().equals(username)){ // comparing customer's username with the username's already stored in the systen
                return; // if same username already exists, no new customer is added (exit function)
            }
        }
        customers.add(new Customer(username, password)); // otherwise, if this username does not already exist in the system, then a new customer is added to the system with their username and password
    }
    
    //remove a specific customer from the system
    public void removeCustomer(Customer c){
        customers.remove(c);
    }
    
    //checks login, and returns the correct customer
    public Customer loginCustomer(String username, String password){
        for (Customer c : customers){ // loop through the customers ArrayList
            if(c.validateLogin(username, password)){ // check if username and password match those in the system already
                return c; // if match found return that customer
            }
        }
        return null; // otherwise, login failed
    }
    
    // returns a customer by username
    public Customer findCustomer(String username){
        for(Customer c : customers){ // loops through the enhanced for loop of Customers
            if(c.getUsername().equals(username)){ // checks if the given username matches any username of the customers ArrayList
                return c; // if match found, return that customer
            }
        }
        return null; //otherwise, no customer found with that username, return null
    }
    
    // OWNER login:
    // validates owner login (admin admin)
    public boolean validateOwner(String u, String p){
        return owner.validateLogin(u,p); // returns true if login was sucessful otherwise false
    }
    
    // ------------- FILE HANDLING -----------------
    public void loadData(){
        try{
            books.clear(); //clear existing books to avoid duplicates 
            customers.clear(); //clear existing customers to avoid duplicates
            
            // load books:
            File booksFile = new File("books.txt");
            
            //vreify if the file exists:
            if(booksFile.exists()){
                Scanner booksScanner = new Scanner(booksFile);
                
                while(booksScanner.hasNextLine()){ //basically until EOF hasn't been reached...
                    String line = booksScanner.nextLine();
                    String [] parts = line.split(","); //////////////////////////////////// what's this doing
                    
                    // format: name, price
                    if(parts.length==2){
                        String name=parts[0];
                        double price=Double.parseDouble(parts[1]); // converts the price from String to double
                        
                        books.add(new Book(name, price)); // adss book into the system (into the books ArrayList)
                    }
                }
                booksScanner.close(); //closing the Scanner object for bookScanner
            }
            
            // load customers:
            File customersFile = new File("customers.txt"); //create new file for Customers info
            
            //if the file exists
            if(customersFile.exists()){
                Scanner customersScanner = new Scanner (customersFile);
                
                while(customersScanner.hasNextLine()){ // do until EOF reached basically
                    String line = customersScanner.nextLine();
                    String [] parts = line.split(",");
                    
                    //format: username, password, points
                    if(parts.length==3){
                        String username = parts[0];
                        String password = parts[1];
                        int points = Integer.parseInt(parts[2]); //convert reading from String to integer, to store the third element which is points in a type integer variable
                        
                        Customer c = new Customer (username, password);
                        c.addPoints(points); //adds points and updates status of customer
                        customers.add(c); //adds the customer to the customers ArrayList (adds customer to the system)
                    }
                }
                customersScanner.close(); //closing Scanner object for customersScanner
            } 
        }
        catch(Exception e){ //if exception caught
            e.printStackTrace(); //print error details
        }
    }
    
    //saves current data to files when app closes
    public void saveData(){
        try{
            // save books:
            PrintWriter booksWriter = new PrintWriter(new File("books.txt"));
            for(Book b : books){
                //format: name, price
                booksWriter.println(b.getName() + "," + b.getPrice());
            }
            
            booksWriter.close(); //closing PrintWriter object writing into the file
        
            //saves customers:
            PrintWriter customersWriter = new PrintWriter (new File("customers.txt"));
            for(Customer c : customers){
                //format: username, password, points
                customersWriter.println(c.getUsername() + "," + c.getPassword() + "," + c.getPoints());
            }
            customersWriter.close(); //closing PrintWriter object writing into the file
        }
        catch (Exception e){
            e.printStackTrace(); //print error details
        }
    }
}