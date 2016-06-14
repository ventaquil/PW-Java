package pw;

import java.util.Random;

public class Spawner extends Thread {
    private static Spawner instance;

    public static Spawner instance()
    {
        if (instance == null) {
            instance = new Spawner();
        }

        return instance;
    }

    @Override
    public void run()
    {
        EntryQueue q = EntryQueue.instance();
        Timeline t = Timeline.instance();

        while (true) {
            if (q.size() < 3) {
                q.newCar();

                t.waitCycle(30);
            }

            t.waitCycle((new Random()).nextInt(q.size() * 600));
        }
    }

    private Spawner() { }
}
