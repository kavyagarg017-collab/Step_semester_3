class Employee {
    String employeeId;
    String name;
    double salary;

    Employee(String employeeId, String name, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }

    void raiseSalary(double salary) {
        this.salary += salary;
    }

    void printSalary() {
        System.out.println(employeeId + " | Final Salary: Rs " + salary);
    }

    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("E-101", "Divya", 40000),
            new Employee("E-102", "Arjun", 55000),
            new Employee("E-103", "Karthik", 62000),
            new Employee("E-104", "Anitha", 48000)
        };

        for (Employee employee : employees) {
            employee.raiseSalary(5000);
            employee.printSalary();
        }
    }
}