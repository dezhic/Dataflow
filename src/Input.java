import java.util.Scanner;

public class Input extends AbstractActor {
    Scanner sc;

    public Input() {
        super.channelsOut = new Channel[1];
    }

    public boolean fire() {
        int data = Integer.parseInt(sc.nextLine());
        channelsOut[0].send(data);
        return true;
    }
}
