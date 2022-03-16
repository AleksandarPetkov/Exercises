package exercises.data;

import java.util.List;

public class Order {
    private static int id;
    private String status;
    private Customer customer;
    private List<Product> productList;

    public Order(List<Product> productList) {
        this.productList = productList;
        id++;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
