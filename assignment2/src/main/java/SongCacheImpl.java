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
        PriorityQueue<String> q = new PriorityQueue<>((s1, s2)->map.get(s1)-map.get(s2));
        for(String id : map.keySet()){
            q.offer(id);
        }
        while(n-->0 && !q.isEmpty()){
            res.add(q.poll());
        }
        return res;
    }
}
