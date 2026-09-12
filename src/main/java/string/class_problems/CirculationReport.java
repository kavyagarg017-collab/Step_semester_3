public class CirculationReport {

    public static String batchPrint(LibraryMember[] members) {

        StringBuilder report = new StringBuilder();

        for (LibraryMember member : members) {

            report.append(member.displayInfo());

            if (member instanceof StudentMember) {

                StudentMember student =
                        (StudentMember) member;

                report.append(" | Course via downcast: ")
                      .append(student.course);
            }

            report.append(" | ");
        }

        return report.toString();
    }
}