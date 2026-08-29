public class Payment {

    static double totalCollected = 0;

    public void pay(double amount) {
        System.out.println("Paid (cash): Rs " + amount);
    }

    public static void processTransaction(Payment payment, double amount) {

        if (payment instanceof CardPayment) {

            CardPayment cardPayment = (CardPayment) payment;

            double chargedAmount =
                cardPayment.payWithProcessingFee(amount);

            System.out.println(
                "Charged (card, incl. fee): Rs " + chargedAmount
            );

            totalCollected += chargedAmount;

        } else {

            payment.pay(amount);

            totalCollected += amount;
        }
    }

    public static void main(String[] args) {

        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = {
            100,
            50,
            200,
            75,
            120
        };

        for (int i = 0; i < payments.length; i++) {

            processTransaction(payments[i], amounts[i]);
        }

        System.out.println(
            "Total Collected: Rs " + totalCollected
        );
    }
}

class CardPayment extends Payment {

    public double payWithProcessingFee(double amount) {
        return amount + (amount * 0.02);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid (card): Rs " + amount);
    }
}