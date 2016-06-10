package pw;

import pw.objects.Car;

public abstract class Path {
    private static Point from0State(Car car)
    {
        Integer index = EntryQueue.instance()
                                  .getQueueIndex(car);

        Integer position = car.getQPosition() - 20;

        if (index == 0) {
            if (position == 82) {
                car.setFirstPosition();
            }
        }

        return new Point(25, position);
    }

    private static Point from1State(Car car)
    {
        return new Point(25, 82);
    }

    private static Point from2State(Car car)
    {
        Integer index = EntryQueue.instance()
                                  .getQueueIndex(car);

        Integer position = car.getQPosition() - 20;
        
        if (index == 0) {
            car.goToFirstPosition();
        } else if (index == 1) {
            if (position == 62) {
                car.setSecondPosition();
            }
        }
        
        return new Point(25, position);
    }

    private static Point from3State(Car car)
    {
        return new Point(25, 62);
    }

    private static Point from4State(Car car)
    {
        Integer index = EntryQueue.instance()
                                  .getQueueIndex(car);
        
        Integer position = car.getQPosition() - 20;
        
        if (index == 0) {
            car.goToFirstPosition();
        } else if (index == 1) {
            car.goToSecondPosition();
        } else if (index == 2) {
            if (position == 42) {
                car.setThirdPosition();
            }
        }
        
        return new Point(25, position);
    }

    private static Point from5State(Car car)
    {
        return new Point(25, 42);
    }

    private static Point from6State(Car car)
    {
        Integer x = 25;
        Integer y = 82;

        Integer position = car.getQPosition();

        if (position < 32) {
            y += position + 1;
        } else {
            y = 114;
            x += position - 32 + 1;
        }

        return new Point(x, y);
    }

    public static Point generatePosition(Car car)
    {
        switch (car.getQ()) {
            case 0:
                return from0State(car);
            case 1:
                return from1State(car);
            case 2:
                return from2State(car);
            case 3:
                return from3State(car);
            case 4:
                return from4State(car);
            case 5:
                return from5State(car);
            case 6:
                return from6State(car);
        }

        System.exit(0);
        return null;
    }

    public static Boolean increaseQPosition(Car car)
    {
        switch (car.getQ()) {
            case 0:
            case 2:
            case 4:
            case 6:
                return true;
            case 1:
            case 3:
            case 5:
                return false;
        }

        System.exit(0);
        return null;
    }

    public static Integer generateQ(Car car)
    {
        return 0;
    }
}
