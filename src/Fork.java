public class Fork extends AbstractActor {
    public Fork() {
        super();
        super.channelsIn = new Channel[1];
        super.channelsOut = new Channel[2];
    }

    public boolean fire() {
        if (channelsIn[0].isEmpty()) { return false; }
        int token = channelsIn[0].receive();
        channelsOut[0].send(token);
        channelsOut[1].send(token);
        return true;
    }
}
