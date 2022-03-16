import exercises.data.Customer;
import exercises.data.Order;
import exercises.data.Product;

import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;

public class PracticalExercises {
    private static List<Customer> customers;
    private static List<Order> orders;
    private static List<Product> products;

    public static void main(String[] args) {
        populateData();
        /**
         * Exercise 1 — Obtain a list of products belongs to category “Books” with price > 100
         */

        List<Product> books = products.stream()
                .filter(b -> b.getCategory().equals("Books"))
                .filter(b -> b.getPrice() >= 100)
                .collect(Collectors.toList());
        books.forEach(b -> System.out.println(b.getName()));

        /**
         Exercise 2 — Obtain a list of order with products belong to category “Investing”
         Hence, the filter logic looks into the products stream of each order record and use anyMatch()
         to determine if any product fulfill the criteria.

         List<Order> result = orderRepo.findAll()
         .stream()
         .filter(o ->
         o.getProducts()
         .stream()
         .anyMatch(p -> p.getCategory().equalsIgnoreCase("Baby"))
         )
         .collect(Collectors.toList());
         **/

        /**
         Exercise 3 — Obtain a list of product with category = “Toys” and then apply 10% discount

         List<Product> result = productRepo.findAll()
         .stream()
         .filter(p -> p.getCategory().equalsIgnoreCase("Toys"))
         .map(p -> p.withPrice(p.getPrice() * 0.9))
         .collect(Collectors.toList());
         **/

        /**
         *Exercise 4 — Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-202
         * This exercise illustrates the usage of flatMap()or example, if we have 3 order records and each order contains 10 products,
         * then flatMap()will emit 10 data elements for each order record, resulting in 30 (3 x 10) product record output in the stream.
         *
         *  List<Product> result = orderRepo.findAll()
         *   .stream()
         *   .filter(o -> o.getCustomer().getTier() == 2)
         *   .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
         *   .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 4, 1)) <= 0)
         *   .flatMap(o -> o.getProducts().stream())
         *   .distinct()
         *   .collect(Collectors.toList());
         */

        /**
         * Exercise 5 — Get the cheapest products of “Books” category. sorted() / findFirst()
         *   Optional<Product> result = productRepo.findAll()
         *         .stream()
         *         .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
         *         .sorted(Comparator.comparing(Product::getPrice))
         *         .findFirst();
         *
         *         or using min() instead sorted and findFirst -> .min(Comparator.comparing(Product::getPrice));
         */

        /**
         *Exercise 6 — Get the 3 most recent placed order, Comparator.reversed(), limit()
         *     List<Order> result = orderRepo.findAll()
         *         .stream()
         *         .sorted(Comparator.comparing(Order::getOrderDate).reversed())
         *         .limit(3)
         *         .collect(Collectors.toList());
         */

        /**
         * Exercise 7 — Get a list of orders which were ordered on 15-Mar-2021,log the order records to the console
         * and then return its product list. peek()  allows execution of system logic, flatmap for product
         *
         *   List<Product> result = orderRepo.findAll()
         *     .stream()
         *     .filter(o -> o.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
         *     .peek(o -> System.out.println(o.toString()))
         *     .flatMap(o -> o.getProducts().stream())
         *     .distinct()
         *     .collect(Collectors.toList());
         *
         */

        /**
         * Exercise 8 — Calculate total lump sum of all orders placed in Feb 2021. mapToDouble(), sum()
         * Double result = orderRepo.findAll()
         *     .stream()
         *     .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
         *     .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 3, 1)) < 0)
         *     .flatMap(o -> o.getProducts().stream())
         *     .mapToDouble(p -> p.getPrice())
         *     .sum();
         */

        /**
         * Exercise 9 — Calculate order average payment placed on 14-Mar-2021
         * Double result = orderRepo.findAll()
         *         .stream()
         *         .filter(o -> o.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
         *         .flatMap(o -> o.getProducts().stream())
         *         .mapToDouble(p -> p.getPrice())
         *         .average().getAsDouble();
         */

        /**
         * Exercise 10 — Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category “Books”
         * When need to get sum, average, max, min and count at the same time. summaryStatistics(), DoubleSummaryStatistics
         *
         *   DoubleSummaryStatistics statistics = productRepo.findAll()
         *     .stream()
         *     .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
         *     .mapToDouble(p -> p.getPrice())
         *     .summaryStatistics();
         */

        /**
         * Exercise 11 — Obtain a data MAP with order id and order’s product count
         * Collectors.toMap() accepts two arguments for your specify the key and value respectively.
         *     Map<Long, Integer>  result = orderRepo.findAll()
         *         .stream()
         *         .collect(
         *             Collectors.toMap(
         *                 order -> order.getId(),
         *                 order -> order.getProducts().size()
         *                 )
         *             );
         */

        /**
         Exercise 12 — Produce a data map with order records grouped by customer. Collectors.groupingBy()
         Map<Customer, List<Order>> result = orderRepo.findAll()
         .stream()
         .collect(
         Collectors.groupingBy(Order::getCustomer)
         );
         */

        /**
         * Exercise 13 — Produce a data map with order record and product total sum
         *  Function.identity() is used to tell Collectors.toMap() to use the data element as the key.
         *          .stream()
         *         .collect(
         *           Collectors.toMap(
         *               Function.identity(),
         *               order -> order.getProducts().stream()
         *                     .mapToDouble(p -> p.getPrice()).sum()
         *             )
         *           );
         */
        
    }

    private static void populateData() {
        products = Arrays.asList(new Product("CTA", "Books", 120.00),
                new Product("Crypto", "Investing", 620.00),
                new Product("Gwent", "Books", 90.00),
                new Product("NFT", "Books", 220.00));
    }
}
