package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;


public class Esercizio {

    public static void Esercizio1() {

        // Creazione di alcuni oggetti Customer e Order per il test
        Customer customer1 = new Customer(1, 1, "Customer1");
        Customer customer2 = new Customer(2, 2, "Customer2");

        Order order1 = new Order(101, customer1, "Shipped", LocalDate.now().minusDays(5), LocalDate.now().plusDays(2));
        Order order2 = new Order(102, customer1, "Pending", LocalDate.now().minusDays(2), LocalDate.now().plusDays(5));
        Order order3 = new Order(103, customer2, "Delivered", LocalDate.now().minusDays(7), LocalDate.now().minusDays(1));

        // Creazione di una lista di ordini
        List<Order> orders = List.of(order1, order2, order3);

        // Raggruppamento degli ordini per cliente utilizzando Stream e Lambda Expressions
        Map<Customer, List<Order>> ordersByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        // Stampa dei risultati
        ordersByCustomer.forEach((customer, orderList) -> {
            System.out.println("Customer: " + customer.getName());
            orderList.forEach(order -> System.out.println("  Order: " + order.getId() + ", Status: " + order.getStatus()));
        });

    }

    public static void Esercizio2(String[] args) {
        // Creazione di alcuni oggetti Customer, Product e Order per il test
        Customer customer1 = new Customer(1, 1, "Customer1");
        Customer customer2 = new Customer(2, 2, "Customer2");

        Product product1 = new Product(101, "Product1", 10.0);
        Product product2 = new Product(102, "Product2", 20.0);

        Order order1 = new Order(1, customer1, "Shipped", LocalDate.now().minusDays(5), LocalDate.now().plusDays(2));
        order1.addProduct(product1);
        order1.addProduct(product2);

        Order order2 = new Order(2, customer1, "Pending", LocalDate.now().minusDays(2), LocalDate.now().plusDays(5));
        order2.addProduct(product1);

        Order order3 = new Order(3, customer2, "Delivered", LocalDate.now().minusDays(7), LocalDate.now().minusDays(1));
        order3.addProduct(product2);

        // Creazione di una lista di ordini
        List<Order> orders = List.of(order1, order2, order3);

        // Calcolo del totale delle vendite per ogni cliente utilizzando Stream e Lambda Expressions
        Map<Customer, Double> salesByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble(Order::getTotalSales)));

        // Stampa dei risultati
        salesByCustomer.forEach((customer, totalSales) ->
                System.out.println("Customer: " + customer.getName() + ", Total Sales: " + totalSales));
    }


    public static void Esercizio3(String[] args) {
        // Creazione di alcuni oggetti Customer, Product e Order per il test
        Customer customer1 = new Customer(1, 1, "Customer1");
        Customer customer2 = new Customer(2, 2, "Customer2");

        Product product1 = new Product(101, "Product1", 10.0);
        Product product2 = new Product(102, "Product2", 20.0);

        Order order1 = new Order(1, customer1, "Shipped", LocalDate.now().minusDays(5), LocalDate.now().plusDays(2));
        order1.addProduct(product1);
        order1.addProduct(product2);

        Order order2 = new Order(2, customer1, "Pending", LocalDate.now().minusDays(2), LocalDate.now().plusDays(5));
        order2.addProduct(product1);

        Order order3 = new Order(3, customer2, "Delivered", LocalDate.now().minusDays(7), LocalDate.now().minusDays(1));
        order3.addProduct(product2);

        // Creazione di una lista di ordini
        List<Order> orders = List.of(order1, order2, order3);

        // Calcolo della media degli importi degli ordini utilizzando Stream e Lambda Expressions
        double averageAmount = orders.stream()
                .mapToDouble(Order::getTotalAmount)
                .average()
                .orElse(0.0);

        // Stampa del risultato
        System.out.println("Average Order Amount: " + averageAmount);
    }

    public static void Esercizio4(String[] args) {
        // Creazione di alcuni oggetti Product per il test
        Product product1 = new Product(1, 10.0, "Product1", "Category1");
        Product product2 = new Product(2, 20.0, "Product2", "Category2");
        Product product3 = new Product(3, 30.0, "Product3", "Category1");
        Product product4 = new Product(4, 15.0, "Product4", "Category2");
        Product product5 = new Product(5, 25.0, "Product5", "Category1");

        // Creazione di una lista di prodotti
        List<Product> products = List.of(product1, product2, product3, product4, product5);

        // Raggruppamento dei prodotti per categoria e calcolo della somma degli importi utilizzando Stream e Lambda Expressions
        Map<String, Double> totalAmountByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));

        // Stampa dei risultati
        totalAmountByCategory.forEach((category, totalAmount) ->
                System.out.println("Category: " + category + ", Total Amount: " + totalAmount));
    }

    public static void Esercizio5(String[] args) {
        // Creazione di alcuni oggetti Product per il test
        Product product1 = new Product(1, 10.0, "Product1", "Category1");
        Product product2 = new Product(2, 20.0, "Product2", "Category2");
        Product product3 = new Product(3, 30.0, "Product3", "Category1");
        Product product4 = new Product(4, 15.0, "Product4", "Category2");
        Product product5 = new Product(5, 25.0, "Product5", "Category1");

        // Creazione di una lista di prodotti
        List<Product> products = List.of(product1, product2, product3, product4, product5);

        // Raggruppamento dei prodotti per categoria e calcolo della somma degli importi utilizzando Stream e Lambda Expressions
        Map<String, Double> totalAmountByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));

        // Stampa dei risultati
        totalAmountByCategory.forEach((category, totalAmount) ->
                System.out.println("Category: " + category + ", Total Amount: " + totalAmount));
    }
