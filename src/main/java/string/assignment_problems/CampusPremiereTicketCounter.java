import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

abstract class Seat {
    protected String seatNumber;

    Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    String getSeatNumber() {
        return seatNumber;
    }

    abstract double getPrice();
}

class RegularSeat extends Seat {

    RegularSeat(String seatNumber) {
        super(seatNumber);
    }

    double getPrice() {
        return 150;
    }
}

class PremiumSeat extends Seat {

    PremiumSeat(String seatNumber) {
        super(seatNumber);
    }

    double getPrice() {
        return 250;
    }
}

class ReclinerSeat extends Seat {

    ReclinerSeat(String seatNumber) {
        super(seatNumber);
    }

    double getPrice() {
        return 400;
    }
}

class Customer {
    private String name;

    Customer(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class Show {
    private String name;
    private LocalDateTime startTime;
    private List<String> bookedSeats;

    Show(String name, LocalDateTime startTime) {
        this.name = name;
        this.startTime = startTime;
        bookedSeats = new ArrayList<>();
    }

    boolean isBooked(String seatNumber) {
        return bookedSeats.contains(seatNumber);
    }

    boolean bookSeat(String seatNumber) {
        if (isBooked(seatNumber)) {
            return false;
        }

        bookedSeats.add(seatNumber);
        return true;
    }

    void releaseSeats(List<Seat> seats) {
        for (Seat seat : seats) {
            bookedSeats.remove(seat.getSeatNumber());
        }
    }

    LocalDateTime getStartTime() {
        return startTime;
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;

    Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = seats;
    }

    boolean confirm() {

        if (seats.size() == 0 || seats.size() > 6) {
            System.out.println("A booking can contain 1 to 6 seats.");
            return false;
        }

        for (Seat seat : seats) {
            if (show.isBooked(seat.getSeatNumber())) {
                System.out.println(
                        "Seat " + seat.getSeatNumber() +
                        " is already booked for this show."
                );
                return false;
            }
        }

        for (Seat seat : seats) {
            show.bookSeat(seat.getSeatNumber());
        }

        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        System.out.printf(
                "Booking confirmed for %s: ",
                customer.getName()
        );

        for (Seat seat : seats) {
            System.out.print(seat.getSeatNumber() + " ");
        }

        System.out.printf("Total: ₹%.2f%n", total);

        return true;
    }

    void cancel() {

        if (LocalDateTime.now().isAfter(show.getStartTime())) {
            System.out.println("Cancellation not allowed after show starts.");
            return;
        }

        show.releaseSeats(seats);

        System.out.print(
                customer.getName() +
                "'s booking cancelled. Seats "
        );

        for (Seat seat : seats) {
            System.out.print(seat.getSeatNumber() + " ");
        }

        System.out.println("released.");
    }
}

public class CampusPremiereTicketCounter {

    public static void main(String[] args) {

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Show show = new Show(
                "Campus Premiere",
                LocalDateTime.now().plusHours(2)
        );

        List<Seat> ashaSeats = new ArrayList<>();
        ashaSeats.add(new RegularSeat("A1"));
        ashaSeats.add(new RegularSeat("A2"));
        ashaSeats.add(new PremiumSeat("F5"));

        Booking b1 =
                new Booking(asha, show, ashaSeats);

        b1.confirm();

        List<Seat> raviSeats = new ArrayList<>();
        raviSeats.add(new ReclinerSeat("F2"));

        Booking b2 =
                new Booking(ravi, show, raviSeats);

        b2.confirm();

        b1.cancel();

        List<Seat> nehaSeats = new ArrayList<>();
        nehaSeats.add(new RegularSeat("A2"));

        Booking b3 =
                new Booking(neha, show, nehaSeats);

        b3.confirm();
    }
}