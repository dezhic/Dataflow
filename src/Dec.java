public class Dec implements Actor {
    Channel[] channelsIn = new Channel[1];
    Channel[] channelsOut = new Channel[1];

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
        channelsOut[0].send(token - 1);
    }
}
