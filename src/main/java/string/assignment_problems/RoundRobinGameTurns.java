public class RoundRobinGameTurns {
    static class Node {
        String name;
        Node next;

        Node(String name) {
            this.name = name;
        }
    }

    Node head;
    Node tail;

    void addLast(String name) {
        Node newNode = new Node(name);

        if (head == null) {
            head = tail = newNode;
            tail.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
    }

    void printTurns(int turns) {
        if (head == null || turns <= 0) {
            return;
        }

        Node current = head;

        for (int i = 0; i < turns; i++) {
            if (i > 0) {
                System.out.print(" ");
            }

            System.out.print(current.name);
            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        RoundRobinGameTurns game = new RoundRobinGameTurns();

        game.addLast("Asha");
        game.addLast("Ravi");
        game.addLast("Neha");

        game.printTurns(7);
    }
}