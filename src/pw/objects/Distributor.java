package pw.objects;

import pw.DistributorCollection;
import java.util.concurrent.Semaphore;

public class Distributor {
    private Integer number;
    private Semaphore s;

    public static Distributor addNew()
    {
        if (DistributorCollection.instance()
                                 .getDistributorsCount() < 3) {
            return new Distributor();
        }

        return null;
    }

    private Distributor() {
        DistributorCollection distributorCollection = DistributorCollection.instance();

        number = distributorCollection.getDistributorsCount() + 1;

        s = new Semaphore(1);

        distributorCollection.add(this);
    }

    public Integer getNumber()
    {
        return number;
    }

    public Semaphore getSemaphore()
    {
        return s;
    }
}
