class Student {
    static String collegeName;
    static String academicYear;

    String name;

    static {
        collegeName = "SRM University";
        academicYear = "2026";
        System.out.println("College info loaded");
    }

    Student(String name) {
        this.name = name;
        System.out.println("Student record created: " + name);
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        Student[] students = new Student[names.length];

        for (int i = 0; i < names.length; i++) {
            students[i] = new Student(names[i]);
        }
    }
}