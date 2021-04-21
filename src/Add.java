public class Add extends AbstractActor {
    public Add() {
        super.channelsIn = new Channel[2];
        super.channelsOut = new Channel[1];
    }

    public boolean fire() {
        if (channelsIn[0].isEmpty() || channelsIn[1].isEmpty()) { return false; }
        channelsOut[0].send(channelsIn[0].receive() + channelsIn[1].receive());
        return true;
    }
}
