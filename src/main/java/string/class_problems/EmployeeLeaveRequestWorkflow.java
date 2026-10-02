public class EmployeeLeaveRequestWorkflow {

    enum Status {
        PENDING, APPROVED, REJECTED
    }

    static abstract class Employee {
        protected String name;

        Employee(String name) {
            this.name = name;
        }

        abstract boolean canTakeLeave(int days);
        abstract String getType();
    }

    static class FullTimeEmployee extends Employee {
        FullTimeEmployee(String name) {
            super(name);
        }

        boolean canTakeLeave(int days) {
            return days <= 20;
        }

        String getType() {
            return "FullTimeEmployee";
        }
    }

    static class PartTimeEmployee extends Employee {
        PartTimeEmployee(String name) {
            super(name);
        }

        boolean canTakeLeave(int days) {
            return days <= 10;
        }

        String getType() {
            return "PartTimeEmployee";
        }
    }

    static class Contractor extends Employee {
        Contractor(String name) {
            super(name);
        }

        boolean canTakeLeave(int days) {
            return days <= 5;
        }

        String getType() {
            return "Contractor";
        }
    }

    static class LeaveRequest {
        private Employee employee;
        private String dates;
        private int days;
        private Status status;

        LeaveRequest(Employee employee, String dates, int days) {
            this.employee = employee;
            this.dates = dates;
            this.days = days;
            this.status = Status.PENDING;
        }

        void approve() {
            if (status != Status.PENDING) {
                System.out.println("Cannot change request status from "
                        + status + " to Approved.");
                return;
            }

            if (employee.canTakeLeave(days)) {
                status = Status.APPROVED;
                System.out.println(employee.name
                        + "'s leave request (" + dates + ") approved. Status: Approved.");
            } else {
                status = Status.REJECTED;
                System.out.println(employee.name
                        + "'s leave request rejected. Status: Rejected.");
            }
        }

        void reject() {
            if (status != Status.PENDING) {
                System.out.println("Cannot change request status from "
                        + status + " to Rejected.");
                return;
            }

            status = Status.REJECTED;
            System.out.println(employee.name
                    + "'s leave request (" + dates + ") rejected. Status: Rejected.");
        }

        void makePending() {
            if (status != Status.PENDING) {
                System.out.println("Cannot change request status from "
                        + status + " to Pending.");
            }
        }

        Status getStatus() {
            return status;
        }
    }

    static class Manager {
        void review(LeaveRequest request, boolean approve) {
            if (approve)
                request.approve();
            else
                request.reject();
        }
    }

    public static void main(String[] args) {

        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest r1 =
                new LeaveRequest(john, "Jan 1-5", 5);

        LeaveRequest r2 =
                new LeaveRequest(jane, "Feb 10-11", 2);

        Manager manager = new Manager();

        System.out.println("Leave request submitted for John (Jan 1-5). Status: Pending.");
        manager.review(r1, true);

        System.out.println("Leave request submitted for Jane (Feb 10-11). Status: Pending.");
        manager.review(r2, false);

        r1.makePending();
    }
}