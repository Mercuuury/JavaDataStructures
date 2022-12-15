public class Link {
    public long value;
    public Link next;
    public Link prev;

    public Link(long value) {
        this.value = value;
    }

    public void displayLink() {
        System.out.print("{" + value + "} ");
    }
}
