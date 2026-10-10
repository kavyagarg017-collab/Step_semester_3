
import java.util.*;

public class CampusVehiclePassSystem {
    interface Chargeable {
        boolean bookChargingBay();
    }

    static class Vehicle {
        String passNumber, owner, type;
        int fee;

        Vehicle(String type, String passNumber, String owner, int fee) {
            this.type = type;
            this.passNumber = passNumber;
            this.owner = owner;
            this.fee = fee;
        }
    }

    static class Bike extends Vehicle {
        Bike(String id, String owner) {
            super("Bike", id, owner, 300);
        }
    }

    static class Car extends Vehicle {
        Car(String id, String owner) {
            super("Car", id, owner, 1000);
        }
    }

    static class EBike extends Vehicle implements Chargeable {
        EBike(String id, String owner) {
            super("EBike", id, owner, 300);
        }

        public boolean bookChargingBay() {
            return true;
        }
    }

    static class ECar extends Vehicle implements Chargeable {
        ECar(String id, String owner) {
            super("ECar", id, owner, 1000);
        }

        public boolean bookChargingBay() {
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new LinkedHashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            if (p[0].equals("PASS")) {
                Vehicle v;

                switch (p[1]) {
                    case "Bike":  v = new Bike(p[2], p[3]); break;
                    case "Car":   v = new Car(p[2], p[3]); break;
                    case "EBike": v = new EBike(p[2], p[3]); break;
                    case "ECar":  v = new ECar(p[2], p[3]); break;
                    default: continue;
                }

                vehicles.put(v.passNumber, v);
                System.out.println(v.passNumber + " (" + v.type +
                        ") pass fee " + v.fee);

            } else if (p[0].equals("CHARGE")) {
                Vehicle v = vehicles.get(p[1]);

                if (v == null) {
                    System.out.println(p[1] + " rejected: pass not found");
                } else if (v instanceof Chargeable) {
                    System.out.println(p[1] + " charging bay allotted");
                } else {
                    System.out.println(p[1] +
                            " rejected: charging unsupported");
                }
            }
        }
        sc.close();
    }
}
