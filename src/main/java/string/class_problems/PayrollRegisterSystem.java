
import java.util.*;

public class PayrollRegisterSystem {
    static abstract class Staff {
        String name;

        Staff(String name) {
            this.name = name;
        }

        abstract int calculatePay();
        abstract String getType();

        String payslip() {
            return "Payslip[name=" + name + ", type=" + getType() +
                    ", pay=" + calculatePay() + "]";
        }
    }

    static class FullTime extends Staff {
        int salary;

        FullTime(String name, int salary) {
            super(name);
            this.salary = salary;
        }

        int calculatePay() { return salary; }
        String getType() { return "FullTime"; }
    }

    static class PartTime extends Staff {
        int hours, rate;

        PartTime(String name, int hours, int rate) {
            super(name);
            this.hours = hours;
            this.rate = rate;
        }

        int calculatePay() { return hours * rate; }
        String getType() { return "PartTime"; }
    }

    static class Intern extends Staff {
        int stipend;

        Intern(String name, int stipend) {
            super(name);
            this.stipend = stipend;
        }

        int calculatePay() { return stipend; }
        String getType() { return "Intern"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Staff> staffList = new ArrayList<>();
        int total = 0;
        Staff topEarner = null;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");
            Staff s;

            switch (p[0]) {
                case "FullTime":
                    s = new FullTime(p[1], Integer.parseInt(p[2]));
                    break;
                case "PartTime":
                    s = new PartTime(p[1], Integer.parseInt(p[2]),
                            Integer.parseInt(p[3]));
                    break;
                case "Intern":
                    s = new Intern(p[1], Integer.parseInt(p[2]));
                    break;
                default:
                    continue;
            }

            staffList.add(s);
        }

        for (Staff s : staffList) {
            System.out.println(s.payslip());
            int pay = s.calculatePay();
            total += pay;

            if (topEarner == null ||
                    pay > topEarner.calculatePay()) {
                topEarner = s;
            }
        }

        System.out.println("Total " + total);

        if (topEarner != null)
            System.out.println("top earner " + topEarner.name);

        sc.close();
    }
}
