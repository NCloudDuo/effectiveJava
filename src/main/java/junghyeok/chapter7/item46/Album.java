package junghyeok.chapter7.item46;

public class Album {
    private final Artist artist;
    private final int sales;
    private final String albumName;

    public Album(final Artist artist, final int sales, final String albumName) {
        this.artist = artist;
        this.sales = sales;
        this.albumName = albumName;
    }

    public Artist getArtist() {
        return artist;
    }

    public int getSales() {
        return sales;
    }

    public String getAlbumName() {
        return albumName;
    }

    @Override
    public String toString() {
        return "Album{" +
                "artist=" + artist +
                ", sales=" + sales +
                ", albumName='" + albumName + '\'' +
                '}';
    }
}
