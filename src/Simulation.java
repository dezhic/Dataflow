import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation {
    static Queue<Actor> jobs = new ConcurrentLinkedDeque<Actor>();
    static ExecutorService es = Executors.newFixedThreadPool(4);
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
        Factory factory = new Factory();

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

        Channel[] ich = new Channel[30];
        for (int i = 0; i < ich.length; i++) {
            ich[i] = factory.createChannel();
        }
        Channel[] bch = new Channel[9];
        for (int i = 0; i < bch.length; i++) {
            bch[i] = factory.createChannel();
        }

        input.connectIn(ich[8], 0);
        input.connectOut(ich[0], 0);

        output.connectIn(ich[27], 0);

        zero1.connectIn(ich[3], 0);
        zero1.connectOut(ich[5], 0);

        zero2.connectIn(ich[13], 0);
        zero2.connectOut(ich[14], 0);

        zero3.connectIn(ich[25], 0);
        zero3.connectOut(ich[21], 0);

        zero4.connectIn(ich[17], 0);
        zero4.connectOut(ich[18], 0);

        zero5.connectIn(ich[28], 0);
        zero5.connectOut(ich[29], 0);

        switch1.connectIn(bch[1], 0);
        switch1.connectIn(ich[1], 1);
        switch1.connectOut(ich[6], 0);
        switch1.connectOut(ich[8], 1);

        switch2.connectIn(bch[3], 0);
        switch2.connectIn(ich[14], 1);
        switch2.connectOut(ich[9], 0);
        switch2.connectOut(ich[10], 1);

        switch3.connectIn(bch[5], 0);
        switch3.connectIn(ich[16], 1);
        switch3.connectOut(ich[18], 0);
        switch3.connectOut(ich[17], 1);

        switch4.connectIn(bch[7], 0);
        switch4.connectIn(ich[24], 1);
        switch4.connectOut(ich[21], 0);
        switch4.connectOut(ich[25], 1);

        switch5.connectIn(bch[8], 0);
        switch5.connectIn(ich[22], 1);
        switch5.connectOut(ich[23], 0);
        switch5.connectOut(ich[24], 1);

        add1.connectIn(ich[7], 0);
        add1.connectIn(ich[29], 1);
        add1.connectOut(ich[0], 0);

        add2.connectIn(ich[18], 0);
        add2.connectIn(ich[19], 1);
        add2.connectOut(ich[20], 0);

        add3.connectIn(ich[20], 0);
        add3.connectIn(ich[21], 1);
        add3.connectOut(ich[22], 0);

        inc1.connectIn(ich[9], 0);
        inc1.connectOut(ich[11], 0);

        inc2.connectIn(ich[10], 0);
        inc2.connectOut(ich[12], 0);

        dec1.connectIn(ich[6], 0);
        dec1.connectOut(ich[7], 0);

        lt1.connectIn(ich[5], 0);
        lt1.connectIn(ich[4], 1);
        lt1.connectOut(bch[0], 0);







        start(input);
    }
}
