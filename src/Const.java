public class Const implements Actor {
    Channel[] channelsIn = new Channel[1];
    Channel[] channelsOut = new Channel[1];
    int value;

    public Const(int value) {
        this.value = value;
    }
    @Override
    public void connectIn(Channel c, int i) {
        channelsIn[i] = c;
    }

    @Override
    public void connectOut(Channel c, int i) {
        channelsOut[i] = c;
    }

    public void fire() {
        channelsIn[0].receive();
        channelsOut[0].send(value);
    }
}
