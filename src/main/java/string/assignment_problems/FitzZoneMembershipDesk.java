interface MembershipPlan {
    double calculateFee();
    String getName();
}

class MonthlyPlan implements MembershipPlan {

    public double calculateFee() {
        return 1000;
    }

    public String getName() {
        return "Monthly";
    }
}

class QuarterlyPlan implements MembershipPlan {

    public double calculateFee() {
        return 3000 * 0.90;
    }

    public String getName() {
        return "Quarterly";
    }
}

class AnnualPlan implements MembershipPlan {

    public double calculateFee() {
        return 12000 * 0.75;
    }

    public String getName() {
        return "Annual";
    }
}

class Member {
    private String name;

    Member(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class Membership {

    private Member member;
    private MembershipPlan plan;
    private String status;

    Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.status = "Active";
    }

    void create() {
        System.out.printf(
                "%s membership created for %s. Fee: ₹%.2f. Status: %s.%n",
                plan.getName(),
                member.getName(),
                plan.calculateFee(),
                status
        );
    }

    void checkIn() {

        if (!status.equals("Active")) {
            System.out.println(
                    member.getName() +
                    " check-in denied: membership is " +
                    status + "."
            );
            return;
        }

        System.out.println(
                member.getName() +
                " checked in successfully."
        );
    }

    void freeze() {

        if (status.equals("Expired")) {
            System.out.println(
                    "Cannot freeze an Expired membership."
            );
            return;
        }

        if (!status.equals("Active")) {
            System.out.println(
                    "Cannot freeze. Current status: " +
                    status
            );
            return;
        }

        status = "Frozen";

        System.out.println(
                member.getName() +
                "'s membership status: Frozen."
        );
    }

    void unfreeze() {

        if (status.equals("Expired")) {
            System.out.println(
                    "Cannot unfreeze an Expired membership."
            );
            return;
        }

        if (!status.equals("Frozen")) {
            System.out.println(
                    "Cannot unfreeze. Current status: " +
                    status
            );
            return;
        }

        status = "Active";

        System.out.println(
                member.getName() +
                "'s membership status: Active."
        );
    }

    void expire() {
        status = "Expired";

        System.out.println(
                member.getName() +
                "'s membership expired. Status: Expired."
        );
    }
}

public class FitzZoneMembershipDesk {

    public static void main(String[] args) {

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership =
                new Membership(
                        asha,
                        new QuarterlyPlan()
                );

        Membership raviMembership =
                new Membership(
                        ravi,
                        new MonthlyPlan()
                );

        ashaMembership.create();
        raviMembership.create();

        ashaMembership.checkIn();
        ashaMembership.freeze();
        ashaMembership.checkIn();

        ashaMembership.unfreeze();

        raviMembership.expire();
        raviMembership.freeze();
    }
}