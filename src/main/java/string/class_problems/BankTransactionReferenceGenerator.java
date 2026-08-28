package string.class_problems;

import java.util.Scanner;

public class BankTransactionReferenceGenerator {

    public static String normalizeReference(String raw) {
        raw = raw.trim();

        if (raw.length() < 3) {
            return raw.toUpperCase();
        }

        return raw.substring(0, 3).toUpperCase()
                + raw.substring(3);
    }

    public static String validateAndFormat(String reference) {

        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }

        String bankCode = reference.substring(0, 3);
        String body = reference.substring(3);

        for (int i = 0; i < bankCode.length(); i++) {
            if (!Character.isLetter(bankCode.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        for (int i = 0; i < body.length(); i++) {
            if (!Character.isDigit(body.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        String date = reference.substring(3, 9);
        String sequence = reference.substring(9, 14);

        StringBuilder result = new StringBuilder();

        result.append("[")
              .append(bankCode.toUpperCase())
              .append("] DATE: ")
              .append(date.substring(0, 2))
              .append("/")
              .append(date.substring(2, 4))
              .append("/")
              .append(date.substring(4, 6))
              .append(" | SEQ: ")
              .append(sequence);

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter transaction reference: ");
        String raw = sc.nextLine();

        String normalized = normalizeReference(raw);

        System.out.println(validateAndFormat(normalized));

        sc.close();
    }
}