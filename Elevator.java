
import java.util.concurrent.Semaphore;

class ElevatorController {
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore capacity = new Semaphore(5);

    private int passengersInside = 0;
    private boolean elevatorTriggered = false;

    public void useElevator(String passengerName) {
       try
       {
            mutex.acquire(); // which means P function
            // The first passenger triggers the elevator wait
            if (passengersInside == 0) {
                System.out.println("Elevator waiting for other passengers...");
                elevatorTriggered = true;
            }

            // Passenger enters the elevator
            capacity.acquire();
            passengersInside++;
            System.out.println(passengerName + " enters the elevator.");

            mutex.release();    // which means V  function  
            // Simulate time spent inside the elevator
            Thread.sleep(3000);
            System.out.println( " Elevator moving ! ");
            mutex.acquire();
            // Passenger exits the elevator
            passengersInside--;
            System.out.println(passengerName + " exits the elevator.");

            capacity.release();

            // Check if the elevator should trigger automatically
           if (elevatorTriggered && passengersInside == 0) {
                System.out.println("Elevator triggered automatically.");
                elevatorTriggered = false;
            }

            mutex.release(); 

        }
       catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Passenger extends Thread {
    private final String name;
    private final ElevatorController controller;

    public Passenger(String name, ElevatorController controller) {
        this.name = name;
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            // Simulate passengers arriving at different times
            Thread.sleep((long) (Math.random() * 5000));

            // Passenger requests the elevator
            controller.useElevator(name);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ElevatorSimulation {
    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController();
        // Simulate multiple passengers arriving at different times
        for (int i = 1; i <= 5; i++) {
// i just use i=5 , there is no relation with capacity .. 
            Passenger passenger = new Passenger("Passenger " + i, controller);
            passenger.start();
        }
    }
}
