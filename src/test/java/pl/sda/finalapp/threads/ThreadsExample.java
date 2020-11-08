package pl.sda.finalapp.threads;

import org.junit.jupiter.api.Test;

public class ThreadsExample {

    @Test

    public void basicThreads(){
        Runnable runnableClass = new OurRunnable();
        Runnable runnableAnonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " runnableAnonymous");
            }
        };

        Runnable runnableLambda = ()->System.out.println(Thread.currentThread().getName() + " runnableLamda");

        Thread thread1 = new Thread(runnableClass);
        Thread thread2 = new Thread(runnableAnonymous);
        Thread thread3 = new Thread(runnableLambda);
       // thread1.run();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
