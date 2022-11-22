import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Node aNode;
        int aKey, size, n, keysPerCell;
        Scanner in = new Scanner(System.in);

        System.out.print("Enter size of hash table: ");
        size = in.nextInt();
        System.out.print("Enter initial number of items: ");
        n = in.nextInt();
        keysPerCell = 10;

        HashTable theHashTable = new HashTable(size);

        for (int j = 0; j < n; j++) {
            aKey = (int) (Math.random() * keysPerCell * size);
            aNode = new Node(aKey);
            theHashTable.insert(aNode);
        }

        theHashTable.display();

        in.close();
    }
}
