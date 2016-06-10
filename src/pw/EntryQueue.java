package pw;

import pw.objects.Car;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ventaquil
 * @package pw
 * @version 0.1.0-alpha
 */
public class EntryQueue {
    private static EntryQueue instance;
    private List<Car> queue;

    /**
     * Private class constructor. Creates new List of Car objects.
     */
    private EntryQueue()
    {
        queue = new ArrayList<Car>();
    }

    /**
     * 
     * @param car
     * @return
     */
    public Integer getQueueIndex(Car car)
    {
        return queue.indexOf(car);
    }

    /**
     * Method returns existing instance of EntryQueue. If instance does not exists method creates it.
     * 
     * @return EntryQueue instance.
     */
    public static EntryQueue instance()
    {
        if (instance == null) {
            instance = new EntryQueue();
        }

        return instance;
    }

    /**
     * 
     * @return
     */
    public synchronized Car newCar()
    {
        Car car = CarsCollection.instance()
                                .newCar(queue.size() * 2);

        put(car);

        return car;
    }

    /**
     * Method returns first Car queue object from queue. If queue is empty method returns null.
     * 
     * @return First Car object in queue. Null if queue is empty.
     */
    public synchronized Car pop()
    {
        if (queue.size() > 0) {
            Car car = queue.get(0);
    
            queue.remove(0);
    
            return car;
        } else {
            return null;
        }
    }

    /**
     * Add Car object at the end of queue.
     * 
     * @param car Car object.
     */
    public synchronized void put(Car car)
    {
        queue.add(car);
    }

    public synchronized Integer size()
    {
        return queue.size();
    }
}
