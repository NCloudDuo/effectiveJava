package junghyeok.chapter7.item46;

import java.util.Objects;

public class Artist {
    private final String artistName;

    public Artist(final String artistName) {
        this.artistName = artistName;
    }

    public String getArtistName() {
        return artistName;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistName='" + artistName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(getArtistName(), artist.getArtistName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArtistName());
    }
}
