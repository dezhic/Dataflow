public class EqualTo extends AbstractActor {
    public EqualTo() {
        super.channelsIn = new Channel[2];
        super.channelsOut = new Channel[1];
    }

    public boolean fire() {
        if (channelsIn[0].isEmpty() || channelsIn[1].isEmpty()) { return false; }
        int t0 = channelsIn[0].receive();
        int t1 = channelsIn[1].receive();
        if (t0 == t1) {
            channelsOut[0].send(1);
        } else {
            channelsOut[0].send(0);
        }
        return true;
    }
}
