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
        Actor input = new Input(System.in);




        start(input);
    }
}
