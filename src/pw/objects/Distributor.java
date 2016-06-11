package pw.objects;

import pw.DistributorCollection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Distributor {
    private Boolean canGo;
    private Boolean carOn;
    private Integer number;
    private Semaphore s;
    private Lock w;

    public static Distributor addNew()
    {
        if (DistributorCollection.instance()
                                 .getDistributorsCount() < 3) {
            return new Distributor();
        }

        return null;
    }

    public synchronized Boolean capture(Car car)
    {
        if (carOn) {
            return false;
        }

        if ((((car.getQ() - 7) / 2) + 1) == number) {
            canGo = false;
            carOn = true;
        }

        return carOn;
    }

    public synchronized Boolean canGo()
    {
        return canGo;
    }

    public synchronized Boolean carOn()
    {
        return carOn;
    }

    private Distributor() {
        DistributorCollection distributorCollection = DistributorCollection.instance();

        canGo = false;

        carOn = false;

        number = distributorCollection.getDistributorsCount() + 1;

        s = new Semaphore(1);

        w = new ReentrantLock();

        distributorCollection.add(this);
    }

    public synchronized void free(Car car)
    {
        if (((car.getQ() - 7) / 2) + 1 == number) {
            s.release();

            carOn = false;
            canGo = false;
        }
    }

    public synchronized void freeCar()
    {
        canGo = true;
    }

    public Integer getNumber()
    {
        return number;
    }

    public Semaphore getSemaphore()
    {
        return s;
    }

    public synchronized void workerUnlock()
    {
        w.unlock();
    }

    public synchronized Boolean workerTryLock()
    {
        return w.tryLock();
    }
}
