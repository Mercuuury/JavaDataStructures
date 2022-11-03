//Непрерывная реализация стека чисел.
class StackDouble {
    private static final int DEFSIZE = 16;
    private double[] array;
    private int head;

    public StackDouble() {
        array = new double[DEFSIZE];
        head = 0;
    }

    public final void push(double val) {
        array[head++] = val;
    }

    public final double pop() {
        return array[--head];
    }

    public final double top() {
        return array[head - 1];
    }
}