import java.nio.BufferOverflowException;

public class ArrayChannel implements Channel {
    int[] buffer;
    volatile int beg = 0;
    volatile int end = beg;
    final int DEFAULT_BOUND = 8;

    public ArrayChannel() {
        buffer = new int[DEFAULT_BOUND];
    }
    public ArrayChannel(int bound) {
        buffer = new int[bound];
    }
    @Override
    public void set(int i) {
        buffer[end] = i;
        end = (end + 1) % buffer.length;
    }

    public synchronized boolean isEmpty() {
        return beg == end;
    }

    public synchronized boolean isFull() {
        return (end + 1) % buffer.length == beg;
    }

    public synchronized void send(int data) {
        if (isFull()) {
            throw new BufferOverflowException();
        }
        buffer[end] = data;
        end = (end + 1) % buffer.length;
        notify();
    }

    public synchronized int receive() {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.exit(1);
            }
        }
        int idx = beg;
        beg = (beg + 1) % buffer.length;
        return buffer[idx];
    }
    public synchronized int peek() {
        return buffer[beg];
    }
}
