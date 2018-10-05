package world.hello.helloworld.xns;

class LinkedListIntersection {

    private static Node solution(Node head1, Node head2) {

        final Node h1 = head1;
        final Node h2 = head2;

        int count1 = count(head1);
        int count2 = count(head2);

        head1 = h1;
        head2 = h2;

        if (count1 >= count2) {
            return getIntersection(head1, head2, count1 - count2);
        } else {
            return getIntersection(head2, head1, count2 - count1);
        }
    }

    private static Node getIntersection(Node head1, Node head2, int diff) {

        final Node h1 = head1;
        final Node h2 = head2;

        Node headTemp = head1;
        for (int i = 0; i < diff; i++) {
            headTemp = headTemp.next;
        }

        while (head2 != null && head1 != null) {

            if (head1 == head2) {
                return head2;
            }

            head1 = head1.next;
            head2 = head2.next;
        }

        head1 = h1;
        head2 = h2;

        while (head2 != null && headTemp != null) {

            if (head2 == headTemp) {
                return head2;
            }

            head2 = head2.next;
            headTemp = headTemp.next;
        }

        return null;
    }

    private static int count(Node head) {
        int count = 0;
        while (head != null) {
            head = head.next;
            ++count;
        }
        return count;
    }

    public static void main(String[] args) {

        Node head1 = new Node("A");
        head1.next = new Node("B");
        head1.next.next = new Node("C");
        head1.next.next.next = new Node("P");
        head1.next.next.next.next = new Node("Q");
        head1.next.next.next.next.next = new Node("R");

        Node head2 = new Node("X");
        head2.next = new Node("Y");
        head2.next.next = head1.next.next.next;
        //head2.next.next = head1.next.next;
        //head2.next.next = new Node("Z");

        String data = "None";
        Node intersection = solution(head1, head2);
        if (intersection != null) {
            data = intersection.data;
        }

        System.out.println("Intersection : " + data);
    }

    private static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
        }
    }
}