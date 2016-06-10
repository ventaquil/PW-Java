package pw;

import pw.objects.Car;
import pw.objects.Worker;

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
        } else if (position < 170) {
            y = 114;
            x += position - 32 + 1;
        } else if (position < 203) {
            y = 114 - (position - 170 + 1);
            x = 164;
        } else if (position < 262) {
            y = 81;
            x = 164 + (position - 203 + 1);
        } else {
            y = 81;
            x = 224;

            car.onFirstDistributor();
        }

        return new Point(x, y);
    }

    private static Point from7State(Car car)
    {
        return new Point(224, 81);
    }

    private static Point from8State(Car car)
    {
        Integer x = 25;
        Integer y = 82;

        Integer position = car.getQPosition();

        if (position < 32) {
            y += position + 1;
        } else if (position < 170) {
            y = 114;
            x += position - 32 + 1;
        } else if (position < 177) {
            y = 114 + (position - 170 + 1);
            x = 164;
        } else if (position < 236) {
            y = 121;
            x = 164 + (position - 177 + 1);
        } else {
            y = 121;
            x = 224;

            car.onSecondDistributor();
        }

        return new Point(x, y);
    }

    private static Point from9State(Car car)
    {
        return new Point(224, 121);
    }

    private static Point from10State(Car car)
    {
        Integer x = 25;
        Integer y = 82;

        Integer position = car.getQPosition();

        if (position < 32) {
            y += position + 1;
        } else if (position < 170) {
            y = 114;
            x += position - 32 + 1;
        } else if (position < 217) {
            y = 114 + (position - 170 + 1);
            x = 164;
        } else if (position < 277) {
            y = 161;
            x = 164 + (position - 217 + 1);
        } else {
            y = 161;
            x = 224;

            car.onThirdDistributor();
        }

        return new Point(x, y);
    }

    private static Point from11State(Car car)
    {
        return new Point(224, 161);
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
            case 7:
                return from7State(car);
            case 8:
                return from8State(car);
            case 9:
                return from9State(car);
            case 10:
                return from10State(car);
            case 11:
                return from11State(car);
        }

        System.exit(0);
        return null;
    }

    public static Point from0State(Worker worker)
    {
        return new Point(10, 10);
    }

    public static Point from1State(Worker worker)
    {
        switch (worker.getNumber()) {
            case 1:
                return new Point(164, 203);
            case 2:
                return new Point(176, 203);
            case 3:
                return new Point(188, 203);
            case 4:
                return new Point(200, 203);
        }

        System.exit(-1);
        return null;
    }

    public static Point from2State(Worker worker)
    {
        Integer x = 0;
        Integer y = 0;
        Integer modifier = 0;

        Integer position = worker.getQPosition();

        switch (worker.getNumber()) {
            case 1:
                x = 164;
                y = 203;
                modifier = 0;
                break;
            case 2:
                x = 176;
                y = 203;
                modifier = 12;
                break;
            case 3:
                x = 188;
                y = 203;
                modifier = 24;
                break;
            case 4:
                x = 200;
                y = 203;
                modifier = 36;
                break;
        }

        if (position < 16) {
            y -= position;
        } else if (position < 96 - modifier) {
            x += position - 16;
            y = 187;
        } else if (position < 216 - modifier) {
            y -= position - 80 + modifier;
            x += 80 - modifier;
        } else {
            y -= 136;
            x += 80 - modifier;
        }

        return new Point(x, y);
    }

    public static Point from3State(Worker worker)
    {
        Integer x = 0;
        Integer y = 0;
        Integer modifier = 0;

        Integer position = worker.getQPosition();

        switch (worker.getNumber()) {
            case 1:
                x = 164;
                y = 203;
                modifier = 0;
                break;
            case 2:
                x = 176;
                y = 203;
                modifier = 12;
                break;
            case 3:
                x = 188;
                y = 203;
                modifier = 24;
                break;
            case 4:
                x = 200;
                y = 203;
                modifier = 36;
                break;
        }

        if (position < 16) {
            y -= position;
        } else if (position < 96 - modifier) {
            x += position - 16;
            y = 187;
        } else if (position < 176 - modifier) {
            y -= position - 80 + modifier;
            x += 80 - modifier;
        } else {
            y -= 96;
            x += 80 - modifier;
        }

        return new Point(x, y);
    }

    public static Point from4State(Worker worker)
    {
        Integer x = 0;
        Integer y = 0;
        Integer modifier = 0;

        Integer position = worker.getQPosition();

        switch (worker.getNumber()) {
            case 1:
                x = 164;
                y = 203;
                modifier = 0;
                break;
            case 2:
                x = 176;
                y = 203;
                modifier = 12;
                break;
            case 3:
                x = 188;
                y = 203;
                modifier = 24;
                break;
            case 4:
                x = 200;
                y = 203;
                modifier = 36;
                break;
        }

        if (position < 16) {
            y -= position;
        } else if (position < 96 - modifier) {
            x += position - 16;
            y = 187;
        } else if (position < 136 - modifier) {
            y -= position - 80 + modifier;
            x += 80 - modifier;
        } else {
            y -= 56;
            x += 80 - modifier;
        }

        return new Point(x, y);
    }

    public static Point from5State(Worker worker)
    {
        return new Point(0, 0);
    }

    public static Point generatePosition(Worker worker)
    {
        switch (worker.getQ()) {
            case 0:
                return from0State(worker);
            case 1:
                return from1State(worker);
            case 2:
                return from2State(worker);
            case 3:
                return from3State(worker);
            case 4:
                return from4State(worker);
            case 5:
                return from5State(worker);
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
            case 8:
            case 10:
                return true;
            case 1:
            case 3:
            case 5:
            case 7:
            case 9:
            case 11:
                return false;
        }

        System.exit(0);
        return null;
    }

    public static Boolean increaseQPosition(Worker worker)
    {
        switch (worker.getQ()) {
            case 0:
            case 2:
            case 3:
            case 4:
                return true;
            case 1:
                return false;
        }

        System.exit(0);
        return null;
    }
}
