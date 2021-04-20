public class Fork implements Actor {
    Channel[] channelsIn = new Channel[1];
    Channel[] channelsOut = new Channel[2];
    @Override
    public void connectIn(Channel c, int i) {
        channelsIn[i] = c;
    }

    @Override
    public void connectOut(Channel c, int i) {
        channelsOut[i] = c;
    }

    public void fire() {
        int token = channelsIn[0].receive();
        channelsOut[0].send(token);
        channelsOut[1].send(token);
    }
}
