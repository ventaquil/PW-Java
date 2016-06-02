package pw;

import pw.objects.Car;

public abstract class Path {
    private static Point from0State(Car car)
    {
        Integer index = EntryQueue.instance()
                                  .getQueueIndex(car);

        Integer position = car.getQPosition() - 34;

        if (index == 0) {
            if (position == 68) {
                car.setFirstPosition();
            }
        } else if (index == 1) {
            
        } else {
            
        }

        return new Point(41, position);
    }

    private static Point from1State(Car car)
    {
        return new Point(41, 68);
    }

    public static Point generatePosition(Car car)
    {
        switch (car.getQ()) {
            case 0:
                return from0State(car);
            case 1:
                return from1State(car);
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }

        System.exit(0);
        return null;
    }

    public static Double generateRotation(Car car)
    {
        return Math.toRadians(90);//(new java.util.Random()).nextInt(90));
    }

    public static Boolean increaseQPosition(Car car)
    {
        switch (car.getQ()) {
            case 0:
                return true;
            case 1:
                //return from1State(car);
                return false;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }

        System.exit(0);
        return null;
    }

    public static Integer generateQ(Car car)
    {
        return 0;
    }
}
