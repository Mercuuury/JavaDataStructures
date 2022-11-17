import java.util.stream.LongStream;

public class ListTest {
    public static void main(String[] args) {
        Long start;
        Long end;
        long[] arr = LongStream.range(0, 30).toArray();
        ListArray larr = new ListArray(arr);
        LinkedList l1list = new LinkedList(arr);
        DoubleEndedList delist = new DoubleEndedList(arr);
        DoublyLinkedList l2list = new DoublyLinkedList(arr);
        ListIterator l1Iter = l1list.getIterator();
        ListIterator deIter = delist.getIterator();
        ListIterator l2Iter = l2list.getIterator();

        // larr.print();
        // l1list.displayList();
        // delist.displayList();
        // l2list.displayForward();

        int swapX = 0;
        int swapY = 10;

        start = System.nanoTime();
        larr.swap(swapX, swapY);
        end = System.nanoTime();
        System.out.println("Перестановка в массиве выполнена за: " + (end - start) + " нс");
        
        start = System.nanoTime();
        l1list.swap(swapX, swapY);
        end = System.nanoTime();
        System.out.println("Перестановка в односвязном списке выполнена за: " + (end - start) + " нс");

        start = System.nanoTime();
        l2list.swap(swapX, swapY);
        end = System.nanoTime();
        System.out.println("Перестановка в двусвязном списке выполнена за: " + (end - start) + " нс");

        // larr.print();
        // l1list.displayList();
        // delist.displayList();
        // l2list.displayForward();

        boolean moved;
        l1Iter.reset();
        l2Iter.reset();
        deIter.reset();
        System.out.println(">>> Проверка скорости перехода к следующему элементу...");
        start = System.nanoTime();
        moved = l1Iter.nextLink();
        end = System.nanoTime();
        System.out.println("в односвязном списке: " + (end - start) + " нс. Успех: " + moved);
        start = System.nanoTime();
        moved = l1Iter.nextLink();
        end = System.nanoTime();
        System.out.println("в односвязном списке: " + (end - start) + " нс. Успех: " + moved);
        start = System.nanoTime();
        moved = l2Iter.nextLink();
        end = System.nanoTime();
        System.out.println("в двусвязном списке: " + (end - start) + " нс. Успех: " + moved);
        start = System.nanoTime();
        moved = deIter.nextLink();
        end = System.nanoTime();
        System.out.println("в двустороннем списке: " + (end - start) + " нс. Успех: " + moved);

        System.out.println(">>> Проверка скорости перехода к предыдущему элементу...");
        start = System.nanoTime();
        moved = l2Iter.prevLink();
        end = System.nanoTime();
        System.out.println("в двусвязном списке: " + (end - start) + " нс. Успех: " + moved);

        l2Iter.reset();
        deIter.reset();
        System.out.println(">>> Проверка скорости перехода к последнему элементу...");
        start = System.nanoTime();
        l2Iter.moveAtEnd();
        end = System.nanoTime();
        System.out.println("в двусвязном списке: " + (end - start) + " нс");
        start = System.nanoTime();
        deIter.moveAtEnd();
        end = System.nanoTime();
        System.out.println("в двустороннем списке: " + (end - start) + " нс");
    }

    // public static void main(String[] args) {
    //     // testLinkedList();
    //     // testDoubleEndedList();
    //     // testDoublyLinkedList();
    //     // testSortedList();
    // }

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
            System.out.println("Найден узел с ключом 0: " + f.value);
        else
            System.out.println("Не удалось найти узел");

        f = theList.find(55);
        if (f != null)
            System.out.println("Найден узел со значением 55: " + f.value);
        else
            System.out.println("Не удалось найти узел");

        Link d = theList.deleteKey(4);
        if (d != null)
            System.out.println("Удален узел со значением 4: " + d.value);
        else
            System.out.println("Не удалось удалить узел");

        d = theList.delete(33);
        if (d != null)
            System.out.println("Удален узел со значением 33: " + d.value);
        else
            System.out.println("Не удалось удалить узел");

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
            System.out.println("Найден узел с ключом 0: " + f.value);
        else
            System.out.println("Не удалось найти узел");

        f = theList.find(55);
        if (f != null)
            System.out.println("Найден узел со значением 55: " + f.value);
        else
            System.out.println("Не удалось найти узел");

        theList.deleteFirst();
        theList.deleteFirst();

        f = theList.findKey(0);
        if (f != null)
            System.out.println("Найден узел с ключом 0: " + f.value);
        else
            System.out.println("Не удалось найти узел");

        f = theList.find(44);
        if (f != null)
            System.out.println("Найден узел со значением 44:" + f.value);
        else
            System.out.println("Не удалось найти узел");
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
            System.out.println("Найден узел с ключом 0: " + f.value);
        else
            System.out.println("Не удалось найти узел");

        f = theSortedList.find(50);
        if (f != null)
            System.out.println("Найден узел со значением 50: " + f.value);
        else
            System.out.println("Не удалось найти узел");
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
            System.out.println("Найден узел с ключом 0: " + f.value);
        else
            System.out.println("Не удалось найти узел");

        f = theList.find(44);
        if (f != null)
            System.out.println("Найден узел со значением 44: " + f.value);
        else
            System.out.println("Не удалось найти узел");
    }
}
