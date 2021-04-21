public interface Actor {

    void connectIn(Channel c, int i);
    void connectOut(Channel c, int i);
    boolean fire();
}
