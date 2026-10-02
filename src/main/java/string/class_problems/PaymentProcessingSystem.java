import java.util.*;

public class PaymentProcessingSystem {

    static class Product {
        private String name;
        private double price;

        Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        double getPrice() {
            return price;
        }

        String getName() {
            return name;
        }
    }

    static class Customer {
        private String name;

        Customer(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    interface PaymentMethod {
        boolean processPayment(double amount);
        String getName();
    }

    static class CreditCard implements PaymentMethod {

        public boolean processPayment(double amount) {
            System.out.println("Credit Card payment is successful.");
            return true;
        }

        public String getName() {
            return "Credit Card";
        }
    }

    static class PayPal implements PaymentMethod {

        public boolean processPayment(double amount) {
            System.out.println("PayPal payment failed.");
            return false;
        }

        public String getName() {
            return "PayPal";
        }
    }

    static class BankTransfer implements PaymentMethod {

        public boolean processPayment(double amount) {
            System.out.println("Bank Transfer payment is successful.");
            return true;
        }

        public String getName() {
            return "Bank Transfer";
        }
    }

    static class Order {
        private Customer customer;
        private List<Product> products = new ArrayList<>();
        private String status = "Pending";

        Order(Customer customer) {
            this.customer = customer;
        }

        void addProduct(Product product) {
            products.add(product);
        }

        double getTotal() {
            double total = 0;

            for (Product p : products) {
                total += p.getPrice();
            }

            return total;
        }

        boolean pay(PaymentMethod method) {

            if (products.isEmpty()) {
                System.out.println("Cannot process payment for an empty order.");
                return false;
            }

            System.out.println("Payment initiated via "
                    + method.getName()
                    + " for Order.");

            boolean success = method.processPayment(getTotal());

            if (success) {
                status = "Paid";
                System.out.println("Order status: Paid.");
            } else {
                System.out.println("Order status: Pending.");
            }

            return success;
        }

        String getStatus() {
            return status;
        }
    }

    public static void main(String[] args) {

        Customer x = new Customer("Customer X");
        Customer y = new Customer("Customer Y");
        Customer z = new Customer("Customer Z");

        Product a = new Product("Product A", 100);
        Product b = new Product("Product B", 50);
        Product c = new Product("Product C", 75);

        Order orderX = new Order(x);
        orderX.addProduct(a);
        orderX.addProduct(b);

        System.out.println("Order created for Customer X.");
        orderX.pay(new CreditCard());

        Order emptyOrder = new Order(y);
        emptyOrder.pay(new CreditCard());

        Order orderZ = new Order(z);
        orderZ.addProduct(c);

        System.out.println("Order created for Customer Z.");
        orderZ.pay(new PayPal());
    }
}