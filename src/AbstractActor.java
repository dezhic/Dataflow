public abstract class AbstractActor implements Actor, Runnable {
    Channel[] channelsIn;
    Channel[] channelsOut;

    public AbstractActor() {
        Simulation.actors.add(this);
    }

    @Override
    public void connectIn(Channel c, int i) {
        channelsIn[i] = c;
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
