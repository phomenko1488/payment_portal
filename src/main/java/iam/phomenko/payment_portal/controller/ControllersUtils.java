package iam.phomenko.payment_portal.controller;

public class ControllersUtils {
    public static boolean validID(String id) {
        try {
            Long idLong = Long.valueOf(id);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
