package sg.edu.rp.c346.id22016788.ndpsongs;

import java.io.Serializable;

public class NDPSongs implements Serializable {

    private int id;
    private String title;
    private String singer;
    private String year;
    private String rating;

    public NDPSongs(int id, String title, String singer, String year, String rating  ) {
        this.id = id;
        this.title = title;
        this.singer = singer;
        this.year = year;
        this.rating = rating;
    }

    public int getId() {  return id;  }

    public String getNDPSongsTitle() {
        return title;
    }
    public String getNDPSongsSinger() {
        return singer;
    }
    public String getNDPSongsYear() {
        return year;
    }
    public String getNDPSongsRating() {
        return rating;
    }

    public void setNDPSongsContent(int id, String title, String singer, String year, String rating) {
        this.id = id;
        this.title = title;
        this.singer = singer;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ID:" + id + ", " + title;
    }

}
