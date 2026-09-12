public class HonorsStudentMember extends StudentMember {

    private int bonusLimit;

    public HonorsStudentMember(
            String memberId,
            int borrowLimit,
            String course,
            int bonusLimit) {

        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public String displayInfo() {
        return "Honors Student Member | Course: "
                + course
                + " | Bonus Limit: "
                + bonusLimit
                + " | Books Borrowed: "
                + getBooksBorrowed();
    }
}