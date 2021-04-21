public interface Channel {
    void set(int i);
    void send(int data);
    int receive();
    boolean isEmpty();
    int peek();
    void setDest(Actor dest);
}
