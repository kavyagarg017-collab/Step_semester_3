public class PremiumLibraryMember extends LibraryMember {

    private String loyaltyPerks;

    public PremiumLibraryMember() {
        loyaltyPerks = "Premium";
    }

    public String getLoyaltyPerks() {
        return loyaltyPerks;
    }

    public static void main(String[] args) {

        System.out.println(
                AccessChecker.classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )
        );

        System.out.println(
                AccessChecker.classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );
    }
}