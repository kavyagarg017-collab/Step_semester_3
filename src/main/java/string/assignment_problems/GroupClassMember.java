public class GroupClassMember extends GymMember {

    private String className;

    public GroupClassMember(
            String memberId,
            int monthlyFee,
            String className) {

        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    public String displayInfo() {
        return "Group Class Member | Class: "
                + className
                + " | Sessions: "
                + getSessionsAttended();
    }
}