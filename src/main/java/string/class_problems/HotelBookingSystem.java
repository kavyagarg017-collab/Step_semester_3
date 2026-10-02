import java.time.LocalDate;
import java.util.*;

public class HotelBookingSystem {

    interface Room {
        String getType();
        double calculatePrice(long days);
        boolean isAvailable(LocalDate start, LocalDate end);
    }

    static abstract class BaseRoom implements Room {
        protected String roomNumber;
        protected List<Reservation> reservations = new ArrayList<>();

        BaseRoom(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public boolean isAvailable(LocalDate start, LocalDate end) {
            for (Reservation r : reservations) {
                if (start.isBefore(r.endDate)
                        && end.isAfter(r.startDate)) {
                    return false;
                }
            }

            return true;
        }

        void addReservation(Reservation reservation) {
            reservations.add(reservation);
        }

        void removeReservation(Reservation reservation) {
            reservations.remove(reservation);
        }
    }

    static class StandardRoom extends BaseRoom {
        StandardRoom(String roomNumber) {
            super(roomNumber);
        }

        public String getType() {
            return "Standard Room";
        }

        public double calculatePrice(long days) {
            return days * 100;
        }
    }

    static class DeluxeRoom extends BaseRoom {
        DeluxeRoom(String roomNumber) {
            super(roomNumber);
        }

        public String getType() {
            return "Deluxe Room";
        }

        public double calculatePrice(long days) {
            return days * 180;
        }
    }

    static class Suite extends BaseRoom {
        Suite(String roomNumber) {
            super(roomNumber);
        }

        public String getType() {
            return "Suite";
        }

        public double calculatePrice(long days) {
            return days * 300;
        }
    }

    static class Customer {
        String name;

        Customer(String name) {
            this.name = name;
        }
    }

    static class Reservation {
        Customer customer;
        Room room;
        LocalDate startDate;
        LocalDate endDate;
        boolean cancelled;

        Reservation(Customer customer, Room room,
                    LocalDate startDate, LocalDate endDate) {

            this.customer = customer;
            this.room = room;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        long getDays() {
            return java.time.temporal.ChronoUnit.DAYS.between(
                    startDate, endDate);
        }

        double getPrice() {
            return room.calculatePrice(getDays());
        }

        void cancel() {
            if (!cancelled) {
                cancelled = true;

                if (room instanceof BaseRoom) {
                    ((BaseRoom) room).removeReservation(this);
                }

                System.out.println("Reservation for " + customer.name
                        + " cancelled successfully.");
            }
        }
    }

    static class Hotel {
        Reservation reserve(Customer customer,
                            Room room,
                            LocalDate start,
                            LocalDate end) {

            if (!room.isAvailable(start, end)) {
                System.out.println(room.getType()
                        + " " + getRoomNumber(room)
                        + " is not available from "
                        + start + " to " + end + ".");
                return null;
            }

            Reservation reservation =
                    new Reservation(customer, room, start, end);

            if (room instanceof BaseRoom) {
                ((BaseRoom) room).addReservation(reservation);
            }

            System.out.println("Reservation confirmed for "
                    + customer.name + ", "
                    + room.getType() + " "
                    + getRoomNumber(room)
                    + " (" + start + " - " + end + "). Price: $"
                    + reservation.getPrice());

            return reservation;
        }

        private String getRoomNumber(Room room) {
            if (room instanceof BaseRoom) {
                return ((BaseRoom) room).roomNumber;
            }

            return "";
        }
    }

    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        Room room101 = new StandardRoom("101");
        Room room201 = new DeluxeRoom("201");

        LocalDate jan1 = LocalDate.of(2026, 1, 1);
        LocalDate jan5 = LocalDate.of(2026, 1, 5);

        System.out.println("Checking availability...");
        System.out.println(room101.isAvailable(jan1, jan5)
                ? "Standard Room 101 is available from Jan 1 to Jan 5."
                : "Room unavailable.");

        Reservation r1 =
                hotel.reserve(a, room101, jan1, jan5);

        hotel.reserve(b, room101,
                LocalDate.of(2026, 1, 3),
                LocalDate.of(2026, 1, 7));

        if (r1 != null) {
            r1.cancel();
        }

        hotel.reserve(c, room201,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 12));
    }
}