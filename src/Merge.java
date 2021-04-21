public class Merge extends AbstractActor {
    public Merge() {
        super.channelsIn = new Channel[3];
        super.channelsOut = new Channel[1];
    }

    public boolean fire() {
        if ((channelsIn[0].isEmpty()) ||
                (channelsIn[0].peek() == 0 && channelsIn[2].isEmpty()) ||
                (channelsIn[0].peek() != 0 && channelsIn[1].isEmpty())) {
            return false;
        }
        if (channelsIn[0].receive() == 0) {
            // false
            channelsOut[0].send(channelsIn[2].receive());
        } else {
            // true
            channelsOut[0].send(channelsIn[1].receive());
        }
        return true;
    }
}
