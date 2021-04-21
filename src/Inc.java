public class Inc extends AbstractActor {
    public Inc() {
        super.channelsIn = new Channel[1];
        super.channelsOut = new Channel[1];
    }

    public boolean fire() {
        if (channelsIn[0].isEmpty()) { return false; }
        int token = channelsIn[0].receive();
        channelsOut[0].send(token + 1);
        return true;
    }
}
