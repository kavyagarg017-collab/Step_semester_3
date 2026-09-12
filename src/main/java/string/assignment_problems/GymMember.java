public class GymMember {

    private String memberId;
    protected int monthlyFee;
    private int sessionsAttended;

    private int[] lateFeeHistory;
    private int lateFeeCount;

    private int feesPaid;

    private static int membersEnrolled = 0;
    private static int nextMembershipNumber = 2001;

    public final String membershipNumber;

    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null ||
            memberId.trim().isEmpty() ||
            memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid memberId");
        }

        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("Invalid monthlyFee");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;

        this.lateFeeHistory = new int[10];
        this.lateFeeCount = 0;
        this.feesPaid = 0;

        this.membershipNumber = "GYM-" + nextMembershipNumber;
        nextMembershipNumber++;

        membersEnrolled++;
    }

    public GymMember(int monthlyFee) {
        this("MEM" + nextMembershipNumber, monthlyFee);
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    protected void chargeLateFee(int amount) {

        if (amount <= 0) {
            return;
        }

        if (lateFeeCount == lateFeeHistory.length) {
            int[] newHistory =
                new int[lateFeeHistory.length * 2];

            for (int i = 0; i < lateFeeHistory.length; i++) {
                newHistory[i] = lateFeeHistory[i];
            }

            lateFeeHistory = newHistory;
        }

        lateFeeHistory[lateFeeCount] = amount;
        lateFeeCount++;
    }

    public int[] getLateFeeHistory() {

        int[] copy = new int[lateFeeCount];

        for (int i = 0; i < lateFeeCount; i++) {
            copy[i] = lateFeeHistory[i];
        }

        return copy;
    }

    public int getTotalFees() {

        int total = 0;

        for (int i = 0; i < lateFeeCount; i++) {
            total += lateFeeHistory[i];
        }

        return total;
    }

    public void payFee(int amount) {

        if (amount > 0) {
            feesPaid += amount;
        }
    }

    public void payFee(int amount, String mode) {
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public String displayInfo() {
        return "Standard Member | Sessions: "
                + sessionsAttended;
    }

    public static String signUpBatch(
            String[] memberIds,
            int monthlyFee) {

        int signedUp = 0;
        int rejected = 0;

        for (String id : memberIds) {

            try {
                new GymMember(id, monthlyFee);
                signedUp++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Signed Up: " + signedUp
                + " | Rejected: " + rejected;
    }

    public static boolean isValidReferralCode(String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        if (code.charAt(0) != 'G') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1)) ||
            !Character.isDigit(code.charAt(2))) {
            return false;
        }

        return Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    public static String processWeeklyCheckIn(
            GymMember[] members) {

        int processed = 0;
        int skipped = 0;
        int group = 0;
        int individual = 0;

        for (GymMember member : members) {

            if (member == null) {
                skipped++;
                continue;
            }

            processed++;

            if (member instanceof GroupClassMember) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | "
                + skipped + " null skipped | "
                + group + " group | "
                + individual + " individual";
    }
}