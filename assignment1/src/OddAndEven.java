//answer to ques5
public class OddAndEven {

    static int count = 1;

    static int N;

    public void printOdd(){
        synchronized (this){
            while(count<N){
                while(count%2!=1){
                    try {
                        wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println(count+" "+Thread.currentThread().getName());
                count++;
                notify();
            }
        }
    }
    public void printEven(){
        synchronized (this){
            while(count<N){
                while(count%2!=0){
                    try {
                        wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println(count+" "+Thread.currentThread().getName());
                count++;
                notify();
            }
        }
    }

    public static void main(String[] args) {
        N = 10;
        OddAndEven oddAndEven = new OddAndEven();
        Thread t1 = new Thread(()->{
            oddAndEven.printOdd();
        });
        Thread t2 = new Thread(()->{
            oddAndEven.printEven();
        });
        t1.start();
        t2.start();
    }
}
