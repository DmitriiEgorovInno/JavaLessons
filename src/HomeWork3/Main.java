package HomeWork3;

public class Main {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(5);
        for (int i =0; i<10; i++){
            int taskNum = i;
            threadPool.execute(()-> {
                        System.out.println("Task " + taskNum + " Thread " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

            );
        }
        threadPool.shutdown();
        threadPool.awaitTermination();
        System.out.println("Задачи завершены");
    }
}
