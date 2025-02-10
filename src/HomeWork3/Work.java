package HomeWork3;

public class Work extends Thread{
    private final ThreadPool threadPool;

    public Work(ThreadPool threadPool){
        this.threadPool=threadPool;
    }
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            Runnable task;
            synchronized (threadPool.getLock()) {
                while (threadPool.getTasks().isEmpty() && !threadPool.isShutdown()) {
                    try {
                        threadPool.getLock().wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().isInterrupted();
                        return;
                    }
                }
                if (threadPool.getTasks().isEmpty() && threadPool.isShutdown()){
                    return;
                }
                task= threadPool.getTasks().poll();
            }
            try {
                task.run();
            } catch (RuntimeException e){
                e.printStackTrace();
            }
        }
    }
}
