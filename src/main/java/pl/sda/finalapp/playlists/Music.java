package pl.sda.finalapp.playlists;

public class Music extends PlayableWithTitle {
    private final String author;


    public Music(String author, String title) {
        super(title);
        this.author = author;
    }

    @Override
    public String play() {
        return super.play() + " " + this.author;
    }
}
