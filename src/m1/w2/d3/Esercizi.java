package m1.w2.d3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class Esercizi {
    public static void main(String[] args) {
        // Esercizio 1
        Product p1 = new Product(1, 1000000, "Ferrari LaFerrari", "Boys");
        Product p2 = new Product(2, 200, "PS4", "Svago");
        Product p3 = new Product(3, 5, "Ciuccio", "Boys");
        Product p4 = new Product(4, 200, "Harry Potter e i diamanti di diamante", "Books");
        Product p5 = new Product(5, 1000, "Fast & Furious, Tokyo Rich", "Books");

        List<Product> lista1 =  Stream.of(p1, p2, p3, p4, p5).filter(p -> p.getCategory() == "Books" && p.getPrice() > 100).toList();
        System.out.println(lista1);

        // Esercizio 2
        Customer c1 = new Customer(1, 2, "Gabriele");
        Customer c2 = new Customer(2, 1, "Marco");
        Customer c3 = new Customer(3, 2, "Samuele");
        Customer c4 = new Customer(4, 2, "Simone");
        Customer c5 = new Customer(5, 1, "Vincenzo");

        Order o1 = new Order(1, c3,"pending", LocalDate.parse("2021-02-02"), LocalDate.parse("2024-02-03"));
        o1.addProduct(p1);
        o1.addProduct(p4);
        o1.addProduct(p5);
        Order o2 = new Order(2, c2,"delivered", LocalDate.parse("2023-12-05"), LocalDate.parse("2023-12-06"));
        o2.addProduct(p2);
        o2.addProduct(p3);
        Order o3 = new Order(3, c4,"pending", LocalDate.parse("2021-03-02"), LocalDate.parse("2021-03-04"));
        o3.addProduct(p1);
        o3.addProduct(p5);
        Order o4 = new Order(4, c5,"pending", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-02"));
        o4.addProduct(p3);
        Order o5 = new Order(5, c1,"delivered", LocalDate.parse("2023-11-06"), LocalDate.parse("2022-11-07"));
        o5.addProduct(p4);
        o5.addProduct(p5);

        List<Order> lista2 = Stream.of(o1, o2, o3, o4, o5).filter(o -> o.getProducts().stream().anyMatch(p -> p.getCategory() == "Baby")).toList();
        System.out.println(lista2);

        // Esercizio 3
        List<Product> lista3 = Stream.of(p1, p2, p3, p4, p5).filter(p -> p.getCategory() == "Boys").map(p -> {
            p.setPrice(p.getPrice() * 0.9);
            return p;
        }).toList();
        System.out.println(lista3);

        // Esercizio 4
        Stream.of(p1, p2, p3, p4, p5).filter(p -> p.getCategory() == "Boys").map(p -> {
            p.setPrice(p.getPrice() / 0.9);
            return p;
        }).toList();

        HashSet<Product> prodottiTier2 = new HashSet<>();
        List<Product> lista4 = Stream.of(o1, o2, o3, o4, o5)
                .filter(o -> (o.getCustomer().getTier() == 2 && o.getOrderDate().isAfter(LocalDate.parse("2021-02-01")) && o.getOrderDate().isBefore(LocalDate.parse("2021-04-01"))))
                .flatMap(o -> o.getProducts().stream()).distinct().toList();
        //.forEach(o -> o.getProducts().stream().forEach(p -> prodottiTier2.add(p)));
        System.out.println(lista4);
        //System.out.println(prodottiTier2);
    }
}
