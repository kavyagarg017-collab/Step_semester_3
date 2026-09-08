public class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    private static String branchCode;

    static {
        branchCode = "PAGE-TURNER";
    }

    public LoanReceipt(String memberId, String[] bookIds) {

        this.memberId = memberId;

        if (bookIds == null) {
            this.bookIds = null;
        } else {
            this.bookIds = bookIds.clone();
        }
    }

    public String[] getBookIds() {

        if (bookIds == null) {
            return null;
        }

        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {

        String[] updatedBooks = getBookIds();

        if (updatedBooks != null
                && index >= 0
                && index < updatedBooks.length) {

            updatedBooks[index] = newId;
        }

        return new LoanReceipt(memberId, updatedBooks);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }

    public static void main(String[] args) {

        LoanReceipt r = new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"}
        );

        String[] ids = r.getBookIds();

        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        LoanReceipt corrected =
                r.withCorrectedBookId(1, "BK-102");

        System.out.println(r.getBookIds()[1]);
        System.out.println(corrected.getBookIds()[1]);

        LoanReceipt[] receipts = {
                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{"BK-200"},
                        "ROOM 3"
                ),
                null,
                new LoanReceipt(
                        "LIB-002",
                        new String[]{"BK-201"}
                )
        };

        System.out.println(
                processNightlyCirculation(receipts)
        );
    }
}


class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}


class GroupBookingReceipt extends LoanReceipt {

    private final int groupSize;

    public GroupBookingReceipt(
            String memberId,
            String[] bookIds,
            int groupSize) {

        super(memberId, bookIds);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}