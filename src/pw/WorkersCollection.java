package pw;

import pw.objects.Worker;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class WorkersCollection {
    private static WorkersCollection instance;
    private List<Worker> workers;

    public void add(Worker worker)
    {
        workers.add(worker);

        worker.start();
    }

    public Integer getWorkersCount()
    {
        return workers.size();
    }

    public synchronized static WorkersCollection instance()
    {
        if (instance == null) {
            instance = new WorkersCollection();
        }

        return instance;
    }

    public void notifyWorkers()
    {
        Worker worker;

        for (int i = 0, j = workers.size(); i < j; i++) {
            try {
                worker = workers.get(i);
                synchronized (worker) {
                    worker.notify();
                }
            } catch (IndexOutOfBoundsException e) { }
        }
    }

    public void paint(Graphics g)
    {
        for (int i = 0, j = workers.size(); i < j; i++) {
            workers.get(i)
                   .paint(g);
        }
    }

    private WorkersCollection()
    {
        workers = new ArrayList<Worker>();
    }
}
