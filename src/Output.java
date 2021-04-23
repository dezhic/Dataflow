public class Output extends AbstractActor {
    public Output() {
        super();
        super.channelsIn = new Channel[1];
        super.channelsOut = new Channel[1];
    }

    public boolean fire() {
        if (channelsIn[0].isEmpty()) { return false; }
        int data = channelsIn[0].receive();
        System.out.println(data);
        channelsOut[0].send(data);
        return true;
    }
}
