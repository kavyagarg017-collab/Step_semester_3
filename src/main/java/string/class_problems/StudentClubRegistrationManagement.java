
import java.util.*;

public class StudentClubRegistrationManagement {
    static class Member {
        String rollNumber, name;

        Member(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Member> members = new LinkedHashMap<>();
        List<String[]> queries = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            if (p[0].equals("ADD")) {
                if (members.containsKey(p[1])) {
                    System.out.println("duplicate rejected");
                } else {
                    members.put(p[1], new Member(p[1], p[2]));
                    System.out.println("Added");
                }
            } else if (p[0].equals("CONTAINS")) {
                queries.add(new String[]{p[1], p[2]});
            }
        }

        System.out.println("member count " + members.size());

        for (String[] q : queries) {
            boolean found = members.containsKey(q[0]);
            System.out.println("contains: " + found);
        }

        sc.close();
    }
}
