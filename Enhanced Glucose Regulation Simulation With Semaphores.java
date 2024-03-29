import java.util.concurrent.Semaphore;


class BloodSugar { 
    private int level = 100;
    public int getLevel() {
        return level;
    }
    public void increaseLevel(int amount) {
        level += amount;
    }
    public void decreaseLevel(int amount) {
        level -= amount;
    }
}
class Liver implements Runnable {
    private final BloodSugar bloodSugar;
    private final Semaphore semaphore;
    private int iterations = 0;
    private final int maxIterations = 3;

    public Liver(BloodSugar bloodSugar, Semaphore semaphore) {
        this.bloodSugar = bloodSugar;
      this.semaphore = semaphore;
    }
   @Override
    public void run() {
        while (iterations < maxIterations && !Thread.interrupted()) {
            try {
                semaphore.acquire();
                bloodSugar.increaseLevel(5);
                System.out.println("After sort of decrease , the liver increases \n Blood sugar level: " + bloodSugar.getLevel());
                semaphore.release();
                try {
                    Thread.sleep(3000);
                    iterations++;
                } catch (InterruptedException e) {
                    // Le thread a été interrompu pendant son sommeil
                  Thread.currentThread().interrupt();  // Rétablit le statut d'interruption du thread
                }
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }
    }
}
class PhysicalActivities implements Runnable {
    private final BloodSugar bloodSugar;
    private final Semaphore semaphore;
    private int iterations = 0;
    private final int maxIterations = 3;
    public PhysicalActivities(BloodSugar bloodSugar, Semaphore semaphore) {
        this.bloodSugar = bloodSugar;
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        while (iterations < maxIterations && !Thread.interrupted()) {
            try {
                semaphore.acquire();
                bloodSugar.decreaseLevel(5);
                System.out.println("After having Physical Activities , decrease  \n Blood sugar level: " + bloodSugar.getLevel());
                semaphore.release();
                try {
                    Thread.sleep(3000);
                    iterations++;
                } catch (InterruptedException e) {        
                   Thread.currentThread().interrupt();  
                }
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
        }
    }
}
class NotEating implements Runnable {
    private final BloodSugar bloodSugar;
    private final Semaphore semaphore;
    private int iterations = 0;
    private final int maxIterations = 3;
    public NotEating(BloodSugar bloodSugar, Semaphore semaphore) {
        this.bloodSugar = bloodSugar;
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        while (iterations < maxIterations && !Thread.interrupted()) {
            try {
                semaphore.acquire();
                bloodSugar.decreaseLevel(10);
                System.out.println("this decrease is dangerous !  \n Blood sugar level: " + bloodSugar.getLevel());
                bloodSugar.increaseLevel(10);
                System.out.println(" the liver increases \n Blood sugar level: " + bloodSugar.getLevel());
                semaphore.release();
                try {
                    Thread.sleep(3000);
                    iterations++;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Pancreas implements Runnable {
    private final BloodSugar bloodSugar;
    private final Semaphore semaphore;
    private final Semaphore insulinSemaphore;
    private int iterations = 0;
    private final int maxIterations = 3;
    public Pancreas(BloodSugar bloodSugar, Semaphore semaphore, Semaphore insulinSemaphore) {
        this.bloodSugar = bloodSugar;
        this.semaphore = semaphore;
        this.insulinSemaphore = insulinSemaphore;
    }
    @Override
    public void run() {
        while (iterations < maxIterations && !Thread.interrupted()) {
            try {
                semaphore.acquire();
                bloodSugar.increaseLevel(7);
                System.out.println("Strong increase and insufisant Glycogen ! requires Insulin intervention  \n Blood sugar level: " + bloodSugar.getLevel());
                semaphore.release();
                try {
                    insulinSemaphore.acquire();
                    bloodSugar.decreaseLevel(7);
                    System.out.println("Insulin administered, decreased! \n Blood sugar level: " + bloodSugar.getLevel());
                    insulinSemaphore.release();
                    Thread.sleep(2000);
                    iterations++;
                } catch (InterruptedException e) {
        
                    Thread.currentThread().interrupt();  
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class DiabeticSituationSimulator implements Runnable {
    private final BloodSugar bloodSugar;
    private final Semaphore semaphore;
    private int iterations = 0;
    private final int maxIterations = 3;
    public DiabeticSituationSimulator(BloodSugar bloodSugar, Semaphore semaphore) {
        this.bloodSugar = bloodSugar;
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        while (iterations < maxIterations && !Thread.interrupted()) {
            try {
                semaphore.acquire();
                bloodSugar.increaseLevel(20);
                System.out.println("Over increase , fainting !! \n blood sugar level: " + bloodSugar.getLevel());
                semaphore.release();
                try {
                    Thread.sleep(4000);
                    iterations++;
                } catch (InterruptedException e) {
                
                    Thread.currentThread().interrupt();  
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class OtherSituationSimulator implements Runnable {
    private final BloodSugar bloodSugar;
    private final Semaphore semaphore;
    private int iterations = 0;
    private final int maxIterations = 3;
    public OtherSituationSimulator(BloodSugar bloodSugar, Semaphore semaphore) {
        this.bloodSugar = bloodSugar;
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        while (iterations < maxIterations && !Thread.interrupted()) {
            try {
                semaphore.acquire();
                bloodSugar.increaseLevel(2);
                System.out.println("Chemical factors and hormones which increase \n Blood sugar level: " + bloodSugar.getLevel());
                semaphore.release();
                try {
                    Thread.sleep(5000);
                    iterations++;
                } catch (InterruptedException e) {               
                    Thread.currentThread().interrupt();  
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class EnhancedGlucoseRegulationSimulation {
    public static void main(String[] args) {
        BloodSugar bloodSugar = new BloodSugar();
        Semaphore semaphore = new Semaphore(1);
        Semaphore insulinSemaphore = new Semaphore(1);
        Liver liver = new Liver(bloodSugar, semaphore);
        Pancreas pancreas = new Pancreas(bloodSugar, semaphore, insulinSemaphore);
        DiabeticSituationSimulator diabeticSituationSimulator = new DiabeticSituationSimulator(bloodSugar, semaphore);
        OtherSituationSimulator otherSituationSimulator = new OtherSituationSimulator(bloodSugar, semaphore);
        PhysicalActivities  PhysicalActivities  = new PhysicalActivities(bloodSugar, semaphore);
        NotEating  NotEatingg   = new NotEating (bloodSugar, semaphore);
        Thread liverThread = new Thread(liver);
        Thread pancreasThread = new Thread(pancreas);
        Thread diabeticThread = new Thread(diabeticSituationSimulator);
        Thread otherThread = new Thread(otherSituationSimulator);
        Thread physActi = new Thread( PhysicalActivities);
        Thread NotEating = new Thread(NotEatingg);
        liverThread.start();
        pancreasThread.start();
        diabeticThread.start();
        otherThread.start();
        physActi.start();
        NotEating.start();
    }
}
