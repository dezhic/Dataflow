import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Input extends AbstractActor {
    InputStream inputStream;
    Scanner sc;
    public Input(InputStream in) {
        super();
        this.inputStream = in;
        sc = new Scanner(inputStream);
        super.channelsIn = new Channel[1];
        super.channelsOut = new Channel[1];
    }

    public boolean fire() {
        if (channelsIn[0].isEmpty()) { return false; }
        int token = channelsIn[0].receive();
        if (!sc.hasNextLine()) {
            long elapsed = System.currentTimeMillis() - Simulation.startTime;
            System.err.println("Elapsed time: " + elapsed);
            System.exit(0);
        }
        int data = Integer.parseInt(sc.nextLine());
        channelsOut[0].send(data);

        return true;
    }
}
