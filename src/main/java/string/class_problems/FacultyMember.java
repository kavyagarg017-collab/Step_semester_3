public class FacultyMember extends LibraryMember {

    private String department;

    public FacultyMember(
            int borrowLimit,
            String department) {

        super(borrowLimit);
        this.department = department;
    }

    @Override
    public String displayInfo() {
        return "Faculty Member | Department: "
                + department
                + " | Books Borrowed: "
                + getBooksBorrowed();
    }
}