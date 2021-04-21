public class Output extends AbstractActor {
    public Output() {
        super.channelsIn = new Channel[1];
        super.channelsOut = new Channel[1];
    }

    public boolean fire() {
        if (channelsIn[0].isEmpty()) { return false; }
        System.out.println(channelsIn[0].receive());
        return true;
    }
}
