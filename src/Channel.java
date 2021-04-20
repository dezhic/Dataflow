public interface Channel {
    void set(int i);
    void send(int data);
    int receive();
}
