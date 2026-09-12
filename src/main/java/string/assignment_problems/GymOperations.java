public class GymOperations {

    public static String classifyGeneration(
            GymMember member) {

        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof PremiumMember) {
            return "Direct premium descendant";
        }

        return "Standard Member";
    }

    public static int getTotalSessionsAttended(
            GymMember[] members) {

        int total = 0;

        for (GymMember member : members) {

            if (member != null) {
                total += member.getSessionsAttended();
            }
        }

        return total;
    }

    public static String batchPrint(
            GymMember[] members) {

        StringBuilder report =
                new StringBuilder();

        for (GymMember member : members) {

            report.append(member.displayInfo());

            if (member instanceof PremiumMember) {

                PremiumMember premium =
                        (PremiumMember) member;

                report.append(" | Trainer via downcast: ")
                      .append(premium.trainerName);
            }

            report.append(" | ");
        }

        return report.toString();
    }
}