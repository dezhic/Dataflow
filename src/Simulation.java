import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation {
    static Queue<Actor> jobs = new ConcurrentLinkedDeque<Actor>();
    static ExecutorService es;
    static long startTime;

    static void start(Actor input) {
        jobs.add(input);
        startTime = System.currentTimeMillis();
        work();
    }

    static void work() {
//        System.out.println("jobs.size: " + jobs.size());
        while (!jobs.isEmpty()) {
            Actor a = null;
            try {
                a = jobs.remove();
            } catch (Exception e) {
                // pass
            }
            if (a != null) {
                es.execute((AbstractActor) a);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            es = Executors.newFixedThreadPool(1);
        } else if (args.length == 1){
            int t = 1;
            try {
                t = Integer.parseInt(args[0]);
                es = Executors.newFixedThreadPool(t);
            } catch (NumberFormatException e) {
                System.out.println("Invalid arguments");
                System.exit(1);
            }
        } else {
            System.out.println("Invalid arguments");
            System.exit(1);
        }

        Factory factory = new Factory();

        // Create Actors
        Actor input = factory.createActor("input");
        Actor output = factory.createActor("output");
        Actor zero1 = factory.createActor("0");
        Actor zero2 = factory.createActor("0");
        Actor zero3 = factory.createActor("0");
        Actor zero4 = factory.createActor("0");
        Actor zero5 = factory.createActor("0");
        Actor switch1 = factory.createActor("switch");
        Actor switch2 = factory.createActor("switch");
        Actor switch3 = factory.createActor("switch");
        Actor switch4 = factory.createActor("switch");
        Actor switch5 = factory.createActor("switch");
        Actor add1 = factory.createActor("add");
        Actor add2 = factory.createActor("add");
        Actor add3 = factory.createActor("add");
        Actor inc1 = factory.createActor("inc");
        Actor inc2 = factory.createActor("inc");
        Actor dec1 = factory.createActor("dec");
        Actor lt1 = factory.createActor("<");
        Actor[] fork = new Actor[11];
        for (int i = 0; i < fork.length; i++) {
            fork[i] = factory.createActor("fork");
        }

        // Create Channels
        Channel[] ich = new Channel[29];
        for (int i = 0; i < ich.length; i++) {
            ich[i] = factory.createChannel();
        }
        Channel[] bch = new Channel[9];
        for (int i = 0; i < bch.length; i++) {
            bch[i] = factory.createChannel();
        }

        // Initialize Channels

        // Connect Actors with Channels


        start(input);
    }
}
