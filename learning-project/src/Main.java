import com.sunsum.thread.Account;
import com.sunsum.thread.MyThread;

public class Main {

    public static void threadDemo() throws InterruptedException{
        Thread thread = new Thread(new MyThread(),"my_thread-1");
        Thread thread2 = new Thread(new ThreadGroup("custom"),new MyThread(),"my_thread-2");
        Runnable runnable = ()-> {
            System.out.println(Thread.currentThread().getThreadGroup());
        };
        new Thread(new ThreadGroup("custom"),runnable).start();
        thread.start();
        thread.join();
        thread2.start();
    }

    public static void credit() throws InterruptedException{
        Account account = new Account(135000.77);
        Thread th1 = new Thread(()->{
            account.credit(5000.00);
        });

        Thread th2 = new Thread(()->account.credit(10000.00));

        th1.start();
        th2.start();
        th1.join();
        th2.join();
        System.out.println(account.getAmount());
    }

    public static void transaction() throws  InterruptedException{
        Account account = new Account(135000.00);
        Thread th1 = new Thread(() -> account.debit(5000.00));
        Thread th3 = new Thread(() -> account.debit(5000.00));
        Thread th2 = new Thread(() -> account.debit(135000.00));
        th1.start();
        th3.start();
        th2.start();
        th1.join(2000);
        th2.join(2000,1);
        th3.join(2000,2);

        System.out.println(account.getAmount());
    }

    public static void main(String[] args) throws InterruptedException{
        //threadDemo();
        //credit();
        transaction();
    }

}