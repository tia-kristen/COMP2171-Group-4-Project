package Persistence;

public class Customer {
    private String customer_name;
    private String customer_email;

    public Customer(String customer_name, String customer_email){
        this.customer_email = customer_email;
        this.customer_name = customer_name;
    }

    public String getEmail() {
        return customer_email;
    }

    public String getName() {
        return customer_name;
    }
}
