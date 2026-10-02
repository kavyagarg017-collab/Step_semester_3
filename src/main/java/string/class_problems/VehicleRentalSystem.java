import java.util.*;

public class VehicleRentalSystem {

    interface Vehicle {
        String getType();
        double getRatePerDay();
        boolean isAvailable();
        void setAvailable(boolean available);
    }

    static class Sedan implements Vehicle {
        private boolean available = true;

        public String getType() {
            return "Sedan";
        }

        public double getRatePerDay() {
            return 50;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }

    static class SUV implements Vehicle {
        private boolean available = true;

        public String getType() {
            return "SUV";
        }

        public double getRatePerDay() {
            return 80;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }

    static class Customer {
        private String name;

        Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Rental {
        private Customer customer;
        private Vehicle vehicle;
        private int days;

        Rental(Customer customer, Vehicle vehicle, int days) {
            this.customer = customer;
            this.vehicle = vehicle;
            this.days = days;
        }

        double calculateCharge() {
            return vehicle.getRatePerDay() * days;
        }
    }

    static class RentalSystem {
        private List<Rental> rentals = new ArrayList<>();

        void rent(Customer customer, Vehicle vehicle, int days) {
            if (!vehicle.isAvailable()) {
                System.out.println(vehicle.getType() + " is currently rented.");
                return;
            }

            Rental rental = new Rental(customer, vehicle, days);
            rentals.add(rental);
            vehicle.setAvailable(false);

            System.out.println(vehicle.getType() + " rented successfully by "
                    + customer.getName() + ". Rental charge: $"
                    + rental.calculateCharge());
        }

        void returnVehicle(Vehicle vehicle) {
            vehicle.setAvailable(true);
            System.out.println(vehicle.getType() + " returned successfully.");
        }
    }

    public static void main(String[] args) {
        RentalSystem system = new RentalSystem();

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Vehicle sedan = new Sedan();
        Vehicle suv = new SUV();

        system.rent(c1, sedan, 3);
        system.rent(c2, sedan, 2);
        system.returnVehicle(sedan);
        system.rent(c3, suv, 5);
    }
}