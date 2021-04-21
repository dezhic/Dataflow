import java.util.Scanner;

public class Input extends AbstractActor {
    int data;

    public Input(int data) {
        this.data = data;
        super.channelsOut = new Channel[1];
    }

    public boolean fire() {
        channelsOut[0].send(data);
        return true;
    }
}
