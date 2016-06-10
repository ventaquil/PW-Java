package pw.semaphores;

import java.util.concurrent.Semaphore;

public class BuildingSemaphore extends Semaphore {
    private static final long serialVersionUID = 1L;
    private static BuildingSemaphore instance;

    private BuildingSemaphore(int arg0) {
        super(arg0);
    }

    private BuildingSemaphore(int arg0, boolean arg1) {
        super(arg0, arg1);
    }

    public synchronized static BuildingSemaphore instance()
    {
        if (instance == null) {
            instance = new BuildingSemaphore(1);
        }

        return instance;
    }
}
