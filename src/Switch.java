public class Switch extends AbstractActor {
    public Switch() {
        super.channelsIn = new Channel[2];
        super.channelsOut = new Channel[2];
    }

    public boolean fire() {
        if (channelsIn[0].isEmpty() || channelsIn[1].isEmpty()) {
            return false;
        }
        if (channelsIn[0].receive() == 0) {
            // false
            channelsOut[1].send(channelsIn[1].receive());
        } else {
            // true
            channelsOut[0].send(channelsIn[1].receive());
        }
        return true;
    }
}
