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
        Channel a = factory.createChannel();
        Channel b = factory.createChannel();
        Channel c = factory.createChannel();
        Channel d = factory.createChannel();
        Channel e = factory.createChannel();
        Channel f = factory.createChannel();
        Channel g = factory.createChannel();
        Channel h = factory.createChannel();
        Channel i = factory.createChannel();
        Channel j = factory.createChannel();
        Channel k = factory.createChannel();
        Channel l = factory.createChannel();
        Channel m = factory.createChannel();
        f.set(1);

        Actor fork1 = factory.createActor("fork");
        Actor fork2 = factory.createActor("fork");
        Actor fork3 = factory.createActor("fork");
        Actor switch1 = factory.createActor("switch");
        Actor switch2 = factory.createActor("switch");
        Actor merge = factory.createActor("merge");
        Actor inc = factory.createActor("inc");
        Actor c1 = factory.createActor("1");
        Actor out = new Output();
        List<Actor> actors = new ArrayList<>();
        actors.add(fork1);
        actors.add(fork2);
        actors.add(fork3);
        actors.add(switch1);
        actors.add(switch2);
        actors.add(merge);
        actors.add(inc);
        actors.add(c1);
        actors.add(out);

        fork1.connectIn(a, 0);
        fork1.connectOut(b, 0);
        fork1.connectOut(c, 1);

        fork2.connectIn(c, 0);
        fork2.connectOut(d, 0);
        fork2.connectOut(e, 1);

        fork3.connectIn(i, 0);
        fork3.connectOut(j, 0);
        fork3.connectOut(l, 1);

        switch1.connectIn(d, 0);
        switch1.connectIn(k, 1);
        switch1.connectOut(f, 0);
        switch1.connectOut(m, 1);

        switch2.connectIn(e, 0);
        switch2.connectIn(f, 1);
        switch2.connectOut(g, 0);
        switch2.connectOut(h, 1);

        merge.connectIn(b, 0);
        merge.connectIn(g, 1);
        merge.connectIn(h, 2);
        merge.connectOut(i, 0);

        inc.connectIn(j, 0);
        inc.connectOut(k, 0);

        c1.connectIn(m, 0);
        c1.connectOut(f, 0);

        Channel r = factory.createChannel();
        Channel s = factory.createChannel();

        Actor fork4 = factory.createActor("fork");
        fork4.connectIn(l, 0);
        fork4.connectOut(r, 0);
        fork4.connectOut(s, 1);

        out.connectIn(r, 0);
//
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            Actor input = new Input(System.in);
//            input.connectOut(a, 0);
//            start(input);
//        }
        s.set(0);
        Actor input = new Input(System.in);
        input.connectIn(s, 0);
        input.connectOut(a, 0);
        start(input);



    }
}
