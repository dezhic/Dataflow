public class Add implements Actor{
    Channel[] channelsIn = new Channel[2];
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
        channelsOut[0].send(channelsIn[0].receive() + channelsIn[1].receive());
    }
}
