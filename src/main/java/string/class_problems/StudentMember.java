public class StudentMember extends LibraryMember {

    protected String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }

    @Override
    public String displayInfo() {
        return "Student Member | Course: " + course
                + " | Books Borrowed: " + getBooksBorrowed();
    }
}