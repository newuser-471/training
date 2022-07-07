import java.util.List;

public interface SongCache {

    void recordSongPlays(String songId, int numPlays);

    int getPlaysForSong(String songId);

    List<String> getTopSongsPlayed(int n);
}
