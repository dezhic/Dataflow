import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation {
    static Queue<Actor> jobs = new ConcurrentLinkedQueue<>();
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
        Actor zero0 = factory.createActor("0");
        Actor zero1 = factory.createActor("0");
        Actor zero2 = factory.createActor("0");
        Actor zero3 = factory.createActor("0");
        Actor zero4 = factory.createActor("0");
        Actor switch0 = factory.createActor("switch");
        Actor switch1 = factory.createActor("switch");
        Actor switch2 = factory.createActor("switch");
        Actor switch3 = factory.createActor("switch");
        Actor switch4 = factory.createActor("switch");
        Actor merge0 = factory.createActor("merge");
        Actor merge1 = factory.createActor("merge");
        Actor merge2 = factory.createActor("merge");
        Actor merge3 = factory.createActor("merge");
        Actor merge4 = factory.createActor("merge");
        Actor merge5 = factory.createActor("merge");
        Actor add0 = factory.createActor("add");
        Actor add1 = factory.createActor("add");
        Actor add2 = factory.createActor("add");
        Actor inc0 = factory.createActor("inc");
        Actor inc1 = factory.createActor("inc");
        Actor dec0 = factory.createActor("dec");
        Actor lt0 = factory.createActor("<");
        Actor[] fork = new Actor[16];
        for (int i = 0; i < fork.length; i++) {
            fork[i] = factory.createActor("fork");
        }

        // Create Channels
        Channel[] ich = new Channel[41];
        for (int i = 0; i < ich.length; i++) {
            ich[i] = factory.createChannel();
        }
        Channel[] bch = new Channel[21];
        for (int i = 0; i < bch.length; i++) {
            bch[i] = factory.createChannel();
        }

        // Initialize Channels
        ich[0].set(0);
        ich[11].set(0);
        ich[26].set(0);
        ich[28].set(0);
        bch[13].set(0);

        // Connect Actors with Channels
        input.connectIn(ich[0], 0);
        input.connectOut(ich[1], 0);

        output.connectIn(ich[33], 0);
        output.connectOut(ich[38], 0);

        zero0.connectIn(ich[6], 0);
        zero0.connectOut(ich[8], 0);

        zero1.connectIn(ich[17], 0);
        zero1.connectOut(ich[19], 0);

        zero2.connectIn(ich[23], 0);
        zero2.connectOut(ich[25], 0);

        zero3.connectIn(ich[35], 0);
        zero3.connectOut(ich[37], 0);

        zero4.connectIn(ich[38], 0);
        zero4.connectOut(ich[39], 0);

        switch0.connectIn(bch[1], 0);
        switch0.connectIn(ich[4], 1);
        switch0.connectOut(ich[9], 0);
        switch0.connectOut(ich[0], 1);

        switch1.connectIn(bch[15], 0);
        switch1.connectIn(ich[21], 1);
        switch1.connectOut(ich[24], 0);
        switch1.connectOut(ich[23], 1);

        switch2.connectIn(bch[17], 0);
        switch2.connectIn(ich[29], 1);
        switch2.connectOut(ich[30], 0);
        switch2.connectOut(ich[31], 1);

        switch3.connectIn(bch[19], 0);
        switch3.connectIn(ich[34], 1);
        switch3.connectOut(ich[36], 0);
        switch3.connectOut(ich[35], 1);

        switch4.connectIn(bch[12], 0);
        switch4.connectIn(ich[11], 1);
        switch4.connectOut(ich[12], 0);
        switch4.connectOut(ich[13], 1);

        merge0.connectIn(bch[13], 0);
        merge0.connectIn(ich[2], 1);
        merge0.connectIn(ich[1], 2);
        merge0.connectOut(ich[3], 0);

        merge1.connectIn(bch[16], 0);
        merge1.connectIn(ich[24], 1);
        merge1.connectIn(ich[25], 2);
        merge1.connectOut(ich[26], 0);

        merge2.connectIn(bch[18], 0);
        merge2.connectIn(ich[32], 1);
        merge2.connectIn(ich[31], 2);
        merge2.connectOut(ich[34], 0);

        merge3.connectIn(bch[20], 0);
        merge3.connectIn(ich[36], 1);
        merge3.connectIn(ich[37], 2);
        merge3.connectOut(ich[28], 0);

        merge4.connectIn(bch[11], 0);
        merge4.connectIn(ich[16], 1);
        merge4.connectIn(ich[19], 2);
        merge4.connectOut(ich[11], 0);

        merge5.connectIn(bch[14], 0);
        merge5.connectIn(ich[40], 1);
        merge5.connectIn(ich[18], 2);
        merge5.connectOut(ich[20], 0);

        add0.connectIn(ich[10], 0);
        add0.connectIn(ich[39], 1);
        add0.connectOut(ich[2], 0);

        add1.connectIn(ich[26], 0);
        add1.connectIn(ich[22], 1);
        add1.connectOut(ich[27], 0);

        add2.connectIn(ich[27], 0);
        add2.connectIn(ich[28], 1);
        add2.connectOut(ich[29], 0);

        inc0.connectIn(ich[12], 0);
        inc0.connectOut(ich[14], 0);

        inc1.connectIn(ich[13], 0);
        inc1.connectOut(ich[15], 0);

        dec0.connectIn(ich[9], 0);
        dec0.connectOut(ich[10], 0);

        lt0.connectIn(ich[8], 0);
        lt0.connectIn(ich[7], 1);
        lt0.connectOut(bch[0], 0);

        fork[0].connectIn(ich[3], 0);
        fork[0].connectOut(ich[4], 0);
        fork[0].connectOut(ich[5], 1);

        fork[1].connectIn(ich[5], 0);
        fork[1].connectOut(ich[6], 0);
        fork[1].connectOut(ich[7], 1);

        fork[2].connectIn(bch[0], 0);
        fork[2].connectOut(bch[1], 0);
        fork[2].connectOut(bch[2], 1);

        fork[3].connectIn(bch[2], 0);
        fork[3].connectOut(bch[6], 0);
        fork[3].connectOut(bch[7], 1);

        fork[4].connectIn(bch[6], 0);
        fork[4].connectOut(bch[5], 0);
        fork[4].connectOut(bch[15], 1);

        fork[5].connectIn(bch[5], 0);
        fork[5].connectOut(bch[4], 0);
        fork[5].connectOut(bch[14], 1);

        fork[6].connectIn(bch[4], 0);
        fork[6].connectOut(bch[3], 0);
        fork[6].connectOut(bch[13], 1);

        fork[7].connectIn(bch[3], 0);
        fork[7].connectOut(bch[11], 0);
        fork[7].connectOut(bch[12], 1);

        fork[8].connectIn(bch[7], 0);
        fork[8].connectOut(bch[8], 0);
        fork[8].connectOut(bch[16], 1);

        fork[9].connectIn(bch[8], 0);
        fork[9].connectOut(bch[9], 0);
        fork[9].connectOut(bch[17], 1);

        fork[10].connectIn(bch[9], 0);
        fork[10].connectOut(bch[10], 0);
        fork[10].connectOut(bch[18], 1);

        fork[11].connectIn(bch[10], 0);
        fork[11].connectOut(bch[19], 0);
        fork[11].connectOut(bch[20], 1);

        fork[12].connectIn(ich[30], 0);
        fork[12].connectOut(ich[33], 0);
        fork[12].connectOut(ich[32], 1);

        fork[13].connectIn(ich[20], 0);
        fork[13].connectOut(ich[21], 0);
        fork[13].connectOut(ich[22], 1);

        fork[14].connectIn(ich[14], 0);
        fork[14].connectOut(ich[40], 0);
        fork[14].connectOut(ich[16], 1);

        fork[15].connectIn(ich[15], 0);
        fork[15].connectOut(ich[17], 0);
        fork[15].connectOut(ich[18], 1);

        start(input);
    }
}
