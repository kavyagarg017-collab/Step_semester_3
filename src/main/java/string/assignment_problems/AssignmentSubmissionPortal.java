import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    protected String title;
    protected double maxMarks;
    protected LocalDate dueDate;

    Assignment(String title, double maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    abstract double applyLatePenalty(double marks, long lateDays);

    String getTitle() {
        return title;
    }

    double getMaxMarks() {
        return maxMarks;
    }

    LocalDate getDueDate() {
        return dueDate;
    }
}

class CodingAssignment extends Assignment {

    CodingAssignment(String title, double maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    double applyLatePenalty(double marks, long lateDays) {
        double penalty = lateDays * 0.10;
        return marks * (1 - penalty);
    }
}

class WrittenAssignment extends Assignment {

    WrittenAssignment(String title, double maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    double applyLatePenalty(double marks, long lateDays) {
        double penalty = lateDays * 0.20;
        return marks * (1 - penalty);
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

class Submission {
    private Student student;
    private Assignment assignment;
    private LocalDate submissionDate;
    private String status;
    private Double finalMarks;

    Submission(Student student, Assignment assignment,
               LocalDate submissionDate) {

        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = "Pending";
    }

    void submit() {
        if (!status.equals("Pending")) {
            System.out.println("Cannot submit again. Status: " + status);
            return;
        }

        status = "Submitted";

        long lateDays = Math.max(
                0,
                ChronoUnit.DAYS.between(
                        assignment.getDueDate(),
                        submissionDate
                )
        );

        System.out.println(
                student.getName() + "'s submission for '" +
                assignment.getTitle() + "' received " +
                (lateDays == 0 ? "(on time)" :
                        "(" + lateDays + " days late)") +
                ". Status: Submitted."
        );
    }

    void grade(double marks) {

        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade. Submission is not submitted.");
            return;
        }

        long lateDays = Math.max(
                0,
                ChronoUnit.DAYS.between(
                        assignment.getDueDate(),
                        submissionDate
                )
        );

        finalMarks =
                assignment.applyLatePenalty(marks, lateDays);

        status = "Graded";

        System.out.printf(
                "%s graded: %.2f/%.0f after %d%% late penalty. Status: Graded.%n",
                student.getName(),
                finalMarks,
                assignment.getMaxMarks(),
                lateDays *
                        (assignment instanceof CodingAssignment ? 10 : 20)
        );
    }

    void resubmit() {
        if (status.equals("Graded")) {
            System.out.println(
                    "Cannot resubmit: '" +
                    assignment.getTitle() +
                    "' has already been graded."
            );
        } else {
            System.out.println("Resubmission allowed only before grading.");
        }
    }
}

public class AssignmentSubmissionPortal {

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding =
                new CodingAssignment(
                        "Linked List Lab",
                        50,
                        LocalDate.of(2026, 3, 10)
                );

        Assignment written =
                new WrittenAssignment(
                        "Design Essay",
                        50,
                        LocalDate.of(2026, 3, 12)
                );

        Submission s1 =
                new Submission(
                        asha,
                        coding,
                        LocalDate.of(2026, 3, 10)
                );

        Submission s2 =
                new Submission(
                        ravi,
                        written,
                        LocalDate.of(2026, 3, 14)
                );

        s1.submit();
        s2.submit();

        s1.grade(45);
        s2.grade(40);

        s1.resubmit();
    }
}