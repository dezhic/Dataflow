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

    /*
        synchronization not needed because:
            1. only the receiver can cause a non-empty buffer to be empty;
            2. only the receiver is checking isEmpty;
            3. it doesn't matter if the receiver sees staled `end`, as
                it will eventually see the change when spinning.
     */
    public boolean isEmpty() {
        return beg == end;
    }

    /*
        synchronization is needed, because multiple senders can cause
        the buffer to be full.
     */
    public synchronized boolean isFull() {
        return (end + 1) % buffer.length == beg;
    }

    /* send should be synchronized because it might be called by
        more than one senders.
     */
    public synchronized void send(int data) {
        if (isFull()) {
            throw new BufferOverflowException();
        }
        buffer[end] = data;
        end = (end + 1) % buffer.length;
    }

    /* receive is not synchronized because there's only
        one receiver (that modifies `beg`) */
    public int receive() {
        while (isEmpty()) {
        };  // Spin
        int idx = beg;
        beg = (beg + 1) % buffer.length;
        return buffer[idx];
    }
    public int peek() {
        return buffer[beg];
    }
}
