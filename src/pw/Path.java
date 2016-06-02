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
        }

        return new Point(41, position);
    }

    private static Point from1State(Car car)
    {
        return new Point(41, 68);
    }

    private static Point from2State(Car car)
    {
        Integer index = EntryQueue.instance()
                                  .getQueueIndex(car);

        Integer position = car.getQPosition() - 70;
        
        if (index == 0) {
            car.goToFirstPosition();
        } else if (index == 1) {
            if (position == 34) {
                car.setSecondPosition();
            }
        }
        
        return new Point(41, position);
    }

    private static Point from3State(Car car)
    {
        return new Point(41, 34);
    }

    private static Point from4State(Car car)
    {
        Integer index = EntryQueue.instance()
                                  .getQueueIndex(car);
        
        Integer position = car.getQPosition() - 106;
        
        if (index == 0) {
            car.goToFirstPosition();
        } else if (index == 1) {
            car.goToSecondPosition();
        } else if (index == 2) {
            if (position == 0) {
                car.setThirdPosition();
            }
        }
        
        return new Point(41, position);
    }

    private static Point from5State(Car car)
    {
        return new Point(41, 0);
    }

    private static Point from6State(Car car)
    {
        Integer x = 41;
        Integer y = 68;

        Integer position = car.getQPosition();

        if (position > 25) {
            x += position - 25;

            if (position < 46) {
                y += position;
            } else {
                y += 46;
            }
        } else {
            y += position;
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

    public static Double generateRotation(Car car)
    {
        Integer alpha = null;

        switch (car.getQ()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                alpha = 90;
                break;
            case 6:
                Integer position = car.getQPosition();

                if (position < 18) {
                    alpha = 90;
                } else {
                    if (position < 46) {
                        alpha = (new Double((-45.0 / 14.0) * position + (1035.0 / 7.0))).intValue();
                    } else {
                        alpha = 0;
                    }
                }
                break;
        }

        if (alpha != null) {
            return Math.toRadians(alpha);
        } else {
            System.exit(0);
            return null;
        }
    }

    public static Boolean increaseQPosition(Car car)
    {
        switch (car.getQ()) {
            case 0:
                return true;
            case 1:
                return false;
            case 2:
                return true;
            case 3:
                return false;
            case 4:
                return true;
            case 5:
                return false;
            case 6:
                return true;
        }

        System.exit(0);
        return null;
    }

    public static Integer generateQ(Car car)
    {
        return 0;
    }
}
