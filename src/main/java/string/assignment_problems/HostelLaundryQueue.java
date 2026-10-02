abstract class WashType {
    abstract int getDuration();
    abstract double getCharge();
    abstract String getName();
}

class QuickWash extends WashType {
    int getDuration() {
        return 30;
    }

    double getCharge() {
        return 20;
    }

    String getName() {
        return "Quick";
    }
}

class NormalWash extends WashType {
    int getDuration() {
        return 45;
    }

    double getCharge() {
        return 30;
    }

    String getName() {
        return "Normal";
    }
}

class HeavyWash extends WashType {
    int getDuration() {
        return 60;
    }

    double getCharge() {
        return 45;
    }

    String getName() {
        return "Heavy";
    }
}

class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class WashingMachine {
    private String id;
    private boolean busy;

    WashingMachine(String id) {
        this.id = id;
        this.busy = false;
    }

    String getId() {
        return id;
    }

    boolean isBusy() {
        return busy;
    }

    boolean startWash(Student student, WashType type) {
        if (busy) {
            System.out.println("Machine " + id + " is currently busy.");
            return false;
        }

        busy = true;

        System.out.println(type.getName() + " wash started on " + id +
                " for " + student.getName() +
                " (" + type.getDuration() + " min).");

        System.out.printf("Charge: ₹%.2f%n", type.getCharge());

        return true;
    }

    void completeWash() {
        if (busy) {
            busy = false;
            System.out.println("Wash cycle completed. " + id + " is now free.");
        }
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    void start() {
        machine.startWash(student, washType);
    }

    void complete() {
        machine.completeWash();
    }
}

public class HostelLaundryQueue {
    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashCycle cycle1 =
                new WashCycle(asha, m1, new QuickWash());

        cycle1.start();

        new WashCycle(ravi, m1, new HeavyWash()).start();

        WashCycle cycle2 =
                new WashCycle(ravi, m2, new HeavyWash());

        cycle2.start();

        cycle1.complete();

        new WashCycle(neha, m1, new NormalWash()).start();
    }
}