public abstract class AbstractActor implements Actor, Runnable {
    Channel[] channelsIn;
    Channel[] channelsOut;

    @Override
    public void connectIn(Channel c, int i) {
        channelsIn[i] = c;
        c.setDest(this);
    }

    @Override
    public void connectOut(Channel c, int i) {
        channelsOut[i] = c;
    }

    public void run() {
        fire();
    }

    @Override
    public abstract boolean fire();
}
