package pl.sda.finalapp.threads;

public class OurRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " OurRunnable");
    }
}
