public class Switch implements Actor {
    Channel[] channelsIn = new Channel[2];
    Channel[] channelsOut = new Channel[2];
    @Override
    public void connectIn(Channel c, int i) {
        channelsIn[i] = c;
    }

    @Override
    public void connectOut(Channel c, int i) {
        channelsOut[i] = c;
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
