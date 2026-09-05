/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BookStoreApp;

/**
 *
 * @author i2wahid
 */

import java.util.List;

public class Test {

    public static void main(String[] args) {

        // Get the single BookStore instance
        BookStore store = BookStore.getInstance();

        // Get the owner
        Owner owner = store.getOwner();

        // ================= OWNER ACTIONS =================

        // Add books to store
        owner.addBook(store, "book1", 50);
        owner.addBook(store, "book2", 100);
        owner.addBook(store, "book3", 200);
        owner.addBook(store, "book4", 500);

        // Add a customer
        owner.addCustomer(store, "Anna", "123");

        // ================= CUSTOMER LOGIN =================

        Customer customer = store.loginCustomer("Anna", "123");

        if (customer == null) {
            System.out.println("Login failed!");
            return;
        }

        System.out.println("Customer: " + customer.getUsername());
        System.out.println("Points: " + customer.getPoints());
        System.out.println("Status: " + customer.getState().getClass().getSimpleName());

        // ================= FIRST PURCHASE =================
        System.out.println("\n--- First Purchase (200, 500) ---");

        List<Book> purchase1 = List.of(
                store.getBooks().get(2), // book3
                store.getBooks().get(3)  // book4
        );

        double total1 = customer.buyBooks(purchase1);

        System.out.println("Total Cost: " + total1);
        System.out.println("Points After Purchase: " + customer.getPoints());
        System.out.println("Status: " + customer.getState().getClass().getSimpleName());

        // ================= SECOND PURCHASE (REDEEM) =================
        System.out.println("\n--- Second Purchase (Redeem, 50) ---");

        List<Book> purchase2 = List.of(
                store.getBooks().get(0) // book1
        );

        double total2 = customer.redeemandBuy(purchase2);

        System.out.println("Total Cost: " + total2);
        System.out.println("Points After Purchase: " + customer.getPoints());
        System.out.println("Status: " + customer.getState().getClass().getSimpleName());

        // ================= THIRD PURCHASE (REDEEM) =================
        System.out.println("\n--- Third Purchase (Redeem, 100) ---");

        List<Book> purchase3 = List.of(
                store.getBooks().get(1) // book2
        );

        double total3 = customer.redeemandBuy(purchase3);

        System.out.println("Total Cost: " + total3);
        System.out.println("Points After Purchase: " + customer.getPoints());
        System.out.println("Status: " + customer.getState().getClass().getSimpleName());
    }
}