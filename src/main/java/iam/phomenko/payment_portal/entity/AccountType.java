package iam.phomenko.payment_portal.entity;

public enum AccountType {
    CARD;
    private final static String cardString = "card/simple";
    public static AccountType fromString(String s){
        if (s.equals(cardString))
            return CARD;
        return null;
    }
}
