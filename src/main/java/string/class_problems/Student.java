package string.assignment_problems;

public class Student {
    String name;
    String attendance;

    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    Student(String name, String attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }

    static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }

    public static void main(String[] args) {
        Student s1 = new Student("Ravi", "Present");
        Student s2 = new Student("Anitha", "Present");

        Student.printCollegeInfo();
    }
}