import java.util.Scanner;

public class Input implements Actor {
    Channel[] channelsIn = new Channel[1];
    Channel[] channelsOut = new Channel[1];
    Scanner sc = new Scanner(System.in);
    @Override
    public void connectIn(Channel c, int i) {
        channelsIn[i] = c;
    }

    @Override
    public void connectOut(Channel c, int i) {
        channelsOut[i] = c;
    }

    public boolean fire() {
        int data = Integer.parseInt(sc.nextLine());
        channelsOut[0].send(data);
        return true;
    }
}
