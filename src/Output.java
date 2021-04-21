public class Output extends AbstractActor {
    public Output() {
        super.channelsIn = new Channel[1];
        super.channelsOut = new Channel[1];
    }

    public boolean fire() {
        if (channelsIn[0].isEmpty()) { return false; }
        int data = channelsIn[0].receive();
        long elapsed = System.currentTimeMillis() - Simulation.startTime;
        System.out.println(data + " (" + elapsed + ")");
        channelsOut[0].send(data);
        return true;
    }
}
