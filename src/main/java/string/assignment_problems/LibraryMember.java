public class LibraryMember {

    private String membershipPin;

    String branchCode;

    protected double finesOwed;

    public String displayName;

    private String membershipId;
    private String name;
    private boolean premiumMember;

    private String securityAnswerHash;

    public LibraryMember() {
        membershipId = null;
        name = null;
        premiumMember = false;
        securityAnswerHash = null;
    }

    public void setMembershipId(String id) {
        if (membershipId == null) {
            membershipId = id;
        }
    }

    public String getMembershipId() {
        return membershipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {

        if (securityAnswerHash == null && answer != null) {

            StringBuilder transformed = new StringBuilder();

            for (int i = answer.length() - 1; i >= 0; i--) {
                transformed.append((char) (answer.charAt(i) + 1));
            }

            securityAnswerHash = transformed.toString();
        }
    }
}