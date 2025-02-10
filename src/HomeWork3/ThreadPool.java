package HomeWork3;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadPool {
    private Queue<Runnable> tasks = new LinkedList<>();
    private Thread[] workers;
    private final Object lock = new Object();
    private volatile boolean isShutdown = false;

    public ThreadPool(int size){
        workers = new Thread[size];
        for (int i=0; i<size; i++){
            workers[i]=new Work(this);
            workers[i].start();
        }
    }

    public void execute(Runnable task){
        synchronized (lock) {
            if (isShutdown) {
                throw new IllegalStateException("Threadpool is shut down");
            }
            tasks.add(task);
            lock.notify();
        }
    }
    public void shutdown(){
        synchronized (lock){
            isShutdown=true;
        }
    }

    public void awaitTermination(){
        for (Thread worker:workers){
            try {
                worker.join();
            } catch (InterruptedException e){
                Thread.currentThread().isInterrupted();
            }
        }
    }
    public Queue<Runnable> getTasks(){
        return tasks;
    }
    public Thread[] getWorkers(){
        return workers;
    }
    public Object getLock(){
        return lock;
    }

    public boolean isShutdown() {
        return isShutdown;
    }
}
