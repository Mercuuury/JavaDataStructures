public class IndividualTests {
    public static void main(String[] args) throws Exception {
        // testLinkedList();
        // testDoubleEndedList();
        // testDoublyLinkedList();
        // testSortedList();
    }

    private static void testLinkedList() {
        LinkedList theList = new LinkedList();
        theList.insertFirst(11);
        theList.insertFirst(22);
        theList.insertFirst(33);
        theList.insertFirst(44);
        theList.insertFirst(55);
        theList.displayList();

        Link f = theList.findKey(0);
        if (f != null)
            System.out.println("Found link with key 0: " + f.value);
        else
            System.out.println("Can't find link");

        f = theList.find(55);
        if (f != null)
            System.out.println("Found link with value 55: " + f.value);
        else
            System.out.println("Can't find link");

        Link d = theList.deleteKey(4);
        if (d != null)
            System.out.println("Deleted link with key 4: " + d.value);
        else
            System.out.println("Can't delete link");

        d = theList.delete(33);
        if (d != null)
            System.out.println("Deleted link with value 33: " + d.value);
        else
            System.out.println("Can't delete link");

        theList.displayList();
    }

    private static void testDoubleEndedList() {
        DoubleEndedList theList = new DoubleEndedList();
        theList.insertFirst(11);
        theList.insertFirst(22);
        theList.insertFirst(33);
        theList.insertFirst(44);
        theList.insertFirst(55);
        theList.displayList();

        Link f = theList.findKey(0);
        if (f != null)
            System.out.println("Found link with key 0: " + f.value);
        else
            System.out.println("Can't find link");

        f = theList.find(55);
        if (f != null)
            System.out.println("Found link with value 55: " + f.value);
        else
            System.out.println("Can't find link");

        theList.deleteFirst();
        theList.deleteFirst();

        f = theList.findKey(0);
        if (f != null)
            System.out.println("Found link with key 0: " + f.value);
        else
            System.out.println("Can't find link");

        f = theList.find(44);
        if (f != null)
            System.out.println("Found link with value 44:" + f.value);
        else
            System.out.println("Can't find link");
        theList.displayList();
    }

    private static void testSortedList() {
        SortedList theSortedList = new SortedList();
        theSortedList.insert(20);
        theSortedList.insert(40);
        theSortedList.displayList();

        theSortedList.insert(10);
        theSortedList.insert(30);
        theSortedList.insert(50);
        theSortedList.displayList();

        theSortedList.remove();
        theSortedList.displayList();

        Link f = theSortedList.findKey(0);
        if (f != null)
            System.out.println("Found link with key 0: " + f.value);
        else
            System.out.println("Can't find link");

        f = theSortedList.find(50);
        if (f != null)
            System.out.println("Found link with value 50: " + f.value);
        else
            System.out.println("Can't find link");
    }

    private static void testDoublyLinkedList() {
        DoublyLinkedList theList = new DoublyLinkedList();
        theList.insertFirst(22);
        theList.insertFirst(44);
        theList.insertFirst(66);

        theList.insertLast(11);
        theList.insertLast(33);
        theList.insertLast(55);

        theList.displayForward();
        theList.displayBackward();

        theList.deleteFirst();
        theList.deleteLast();
        theList.delete(11);
        theList.displayForward();

        theList.insertAfter(22, 77);
        theList.insertAfter(33, 88);
        theList.displayForward();
        
        theList.insertAfterKey(3, 100); // .. 33 100 88
        theList.deleteKey(2); // 77
        theList.displayForward();

        Link f = theList.findKey(0);
        if (f != null)
            System.out.println("Found link with key 0: " + f.value);
        else
            System.out.println("Can't find link");

        f = theList.find(44);
        if (f != null)
            System.out.println("Found link with value 44: " + f.value);
        else
            System.out.println("Can't find link");
    }
}
