public class Fork extends AbstractActor {
    int id;
    public Fork() {
        super.channelsIn = new Channel[1];
        super.channelsOut = new Channel[2];
    }
    public Fork(int id) {
        this();
        this.id = id;
    }

    public boolean fire() {
        if (channelsIn[0].isEmpty()) { return false; }
        int token = channelsIn[0].receive();
        channelsOut[0].send(token);
        channelsOut[1].send(token);
        return true;
    }
}
