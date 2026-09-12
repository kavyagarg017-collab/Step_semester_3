public class LibraryMember {

    private String memberId;
    protected int borrowLimit;
    private int booksBorrowed;
    private int[] fineHistory;
    private int fineCount;

    private static int membersEnrolled = 0;
    private static int nextMemberNumber = 101;

    private final String memberNumber;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null ||
            memberId.trim().isEmpty() ||
            memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid memberId");
        }

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException("Invalid borrowLimit");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;

        this.fineHistory = new int[10];
        this.fineCount = 0;

        this.memberNumber = "LIB-" + nextMemberNumber;
        nextMemberNumber++;
        membersEnrolled++;
    }

    public LibraryMember(int borrowLimit) {
        this("LIB" + nextMemberNumber, borrowLimit);
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public void borrowBook(String genre) {
        borrowBook();
    }

    protected void chargeFine(int amount) {
        if (amount <= 0) {
            return;
        }

        if (fineCount == fineHistory.length) {
            int[] newHistory = new int[fineHistory.length * 2];

            for (int i = 0; i < fineHistory.length; i++) {
                newHistory[i] = fineHistory[i];
            }

            fineHistory = newHistory;
        }

        fineHistory[fineCount] = amount;
        fineCount++;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public int[] getFineHistory() {
        int[] copy = new int[fineCount];

        for (int i = 0; i < fineCount; i++) {
            copy[i] = fineHistory[i];
        }

        return copy;
    }

    public int getTotalFine() {
        int total = 0;

        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }

        return total;
    }

    public String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    public static String enrollBatch(String[] memberIds, int borrowLimit) {

        int enrolled = 0;
        int rejected = 0;

        for (String id : memberIds) {
            try {
                new LibraryMember(id, borrowLimit);
                enrolled++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }

    public static boolean isValidRenewalCode(String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        if (code.charAt(0) != 'R') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1)) ||
            !Character.isDigit(code.charAt(2))) {
            return false;
        }

        return Character.isUpperCase(code.charAt(3));
    }

    public static String processNightlyAudit(LibraryMember[] members) {

        int processed = 0;
        int skipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember member : members) {

            if (member == null) {
                skipped++;
                continue;
            }

            processed++;

            if (member instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + skipped + " null skipped | "
                + faculty + " faculty | "
                + regular + " regular";
    }
}