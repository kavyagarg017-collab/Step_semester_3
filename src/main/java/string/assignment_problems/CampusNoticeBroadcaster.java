import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface NotificationChannel {
    void send(Student student, Notice notice);
    String getName();
}

class EmailChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {
        System.out.println(
                "[Email -> " + student.getName() + "] " +
                notice.getTitle()
        );
    }

    public String getName() {
        return "Email";
    }
}

class SmsChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {
        System.out.println(
                "[SMS -> " + student.getName() + "] " +
                notice.getTitle()
        );
    }

    public String getName() {
        return "SMS";
    }
}

class AppChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {
        System.out.println(
                "[App -> " + student.getName() + "] " +
                notice.getTitle()
        );
    }

    public String getName() {
        return "App";
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> preferredChannels;

    Student(String name, String department,
            NotificationChannel... channels) {

        this.name = name;
        this.department = department;
        preferredChannels =
                new ArrayList<>(Arrays.asList(channels));
    }

    String getName() {
        return name;
    }

    String getDepartment() {
        return department;
    }

    List<NotificationChannel> getPreferredChannels() {
        return preferredChannels;
    }
}

class Notice {

    private String title;
    private List<String> targetDepartments;

    Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = targetDepartments;
    }

    String getTitle() {
        return title;
    }

    List<String> getTargetDepartments() {
        return targetDepartments;
    }
}

class NoticeBoard {

    private List<Student> students;

    NoticeBoard(List<Student> students) {
        this.students = students;
    }

    void postNotice(Notice notice) {

        if (notice.getTitle() == null ||
                notice.getTitle().trim().isEmpty()) {

            System.out.println(
                    "Cannot post notice: Title is required."
            );
            return;
        }

        if (notice.getTargetDepartments() == null ||
                notice.getTargetDepartments().isEmpty()) {

            System.out.println(
                    "Cannot post notice: At least one target department is required."
            );
            return;
        }

        System.out.println(
                "Notice '" +
                notice.getTitle() +
                "' posted to " +
                String.join(", ",
                        notice.getTargetDepartments())
        );

        for (Student student : students) {

            if (notice.getTargetDepartments()
                    .contains(student.getDepartment())) {

                for (NotificationChannel channel :
                        student.getPreferredChannels()) {

                    channel.send(student, notice);
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster {

    public static void main(String[] args) {

        NotificationChannel email =
                new EmailChannel();

        NotificationChannel sms =
                new SmsChannel();

        NotificationChannel app =
                new AppChannel();

        Student asha =
                new Student(
                        "Asha",
                        "CSE",
                        email,
                        app
                );

        Student ravi =
                new Student(
                        "Ravi",
                        "ECE",
                        sms
                );

        List<Student> students =
                new ArrayList<>();

        students.add(asha);
        students.add(ravi);

        NoticeBoard board =
                new NoticeBoard(students);

        Notice notice1 =
                new Notice(
                        "Lab Closed Tomorrow",
                        Arrays.asList("CSE")
                );

        board.postNotice(notice1);

        Notice notice2 =
                new Notice(
                        "Fee Deadline Extended",
                        Arrays.asList("CSE", "ECE")
                );

        board.postNotice(notice2);

        Notice notice3 =
                new Notice(
                        "Sports Day",
                        new ArrayList<>()
                );

        board.postNotice(notice3);
    }
}