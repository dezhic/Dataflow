public class Cdr extends AbstractActor {
    boolean fired = false;
    public Cdr() {
        super();
        super.channelsIn = new Channel[1];
        super.channelsOut = new Channel[1];
    }

    @Override
    public boolean fire() {
        if (channelsIn[0].isEmpty()) { return false; }
        int token = channelsIn[0].receive();
        if (fired) {
            channelsOut[0].send(token);
        } else {
            fired = true;
        }
        return true;
    }
}
