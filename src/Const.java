public class Const extends AbstractActor {
    int value;

    public Const(int value) {
        super.channelsIn = new Channel[1];
        super.channelsOut = new Channel[1];
        this.value = value;
    }

    public boolean fire() {
        if (channelsIn[0].isEmpty()) { return false; }
        channelsIn[0].receive();
        channelsOut[0].send(value);
        return true;
    }
}
