package pl.sda.finalapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.finalapp.playlists.Movie;
import pl.sda.finalapp.playlists.Music;
import pl.sda.finalapp.playlists.Playlist;

public class PlaylistTest {

    @Test
    public void souldPlaySequentially() {
        Playlist playlist = new Playlist();
        playlist.addElement(new Music("Kazik", "12 groszy"));
        playlist.addElement(new Movie("Kler"));
        Playlist subplayList = new Playlist();
        subplayList.addElement(new Music("Perfect", "Autobiografia"));
        subplayList.addElement(new Music("Lady Punk", "Marchewka"));
        subplayList.addElement(new Movie("Casablanca"));
        playlist.addElement(subplayList);

        System.out.println(playlist.play());

    }

    @Test
    public void souldPlayRandomly() {
        Playlist playlist = new Playlist();
        playlist.addElement(new Music("Kazik", "12 groszy"));
        playlist.addElement(new Movie("Kler"));
        Playlist subplayList = new Playlist();
        subplayList.setRandom();
        subplayList.addElement(new Music("Perfect", "Autobiografia"));
        subplayList.addElement(new Music("Lady Punk", "Marchewka"));
        subplayList.addElement(new Movie("Casablanca"));
        playlist.addElement(subplayList);

        System.out.println(playlist.play());

    }

    @Test
    public void souldPlayLoop() {
        Playlist playlist = new Playlist();
        playlist.addElement(new Music("Kazik", "12 groszy"));
        playlist.addElement(new Movie("Kler"));
        Playlist subplayList = new Playlist();
        subplayList.setLoop(2);
        subplayList.addElement(new Music("Perfect", "Autobiografia"));
        subplayList.addElement(new Music("Lady Punk", "Marchewka"));
        subplayList.addElement(new Movie("Casablanca"));
        playlist.addElement(subplayList);

        System.out.println(playlist.play());

        String result = "12 groszy Kazik\n" +
                "Kler\n" +
                "Autobiografia Perfect\n" +
                "Marchewka Lady Punk\n" +
                "Casablanca\n" +
                "Autobiografia Perfect\n" +
                "Marchewka Lady Punk\n" +
                "Casablanca";

        Assertions.assertEquals(playlist.play(), result);

    }
}
