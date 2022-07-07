import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SongCacheImpl implements SongCache{

    private Semaphore semaphore = new Semaphore(1, true);
    private Lock lock = new ReentrantLock();
    private static Map<String, Integer> map = new ConcurrentHashMap<>();
    @Override
    public void recordSongPlays(String songId, int numPlays) {
        if(songId==null || songId=="" || numPlays<0){
            throw new InvalidParamException("parameter is invalid");
        }
//        synchronized (this){
//            map.put(songId, numPlays);
//        }
        try {
            lock.lock();
            map.put(songId, numPlays);
            System.out.println(songId+" with played number "+numPlays+" has been stored");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getPlaysForSong(String songId) {
        if(songId==null || songId==""){
            throw new InvalidParamException("parameter is invalid");
        }
        int res = -1;
        if(!map.containsKey(songId)){
            return res;
        }
        try {
            semaphore.acquire();
            res = map.get(songId);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
        return res;
    }

    @Override
    public synchronized List<String> getTopSongsPlayed(int n) {
        List<String> res = new ArrayList<>();
        if(n <= 0){
            return res;
        }
        PriorityQueue<String> q = new PriorityQueue<>((s1, s2)->map.get(s2)-map.get(s1));
        for(String id : map.keySet()){
            q.offer(id);
        }
        while(n-->0 && !q.isEmpty()){
            res.add(q.poll());
        }
        return res;
    }

    public static void main(String[] args) throws InterruptedException {
        SongCacheImpl s = new SongCacheImpl();
        Thread t1 = new Thread(()->{
            s.recordSongPlays("new song1", 10);
            s.recordSongPlays("new song2", 15);
            s.recordSongPlays("new song3", 5);

        });
        Thread t2 = new Thread(()->{
            System.out.println(s.getPlaysForSong("new song2"));
            System.out.println(s.getTopSongsPlayed(2));
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
