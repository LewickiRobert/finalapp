package pl.sda.finalapp.playlists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Playlist implements Playable {

    private List<Playable> elements = new ArrayList<>();
    private PlayMode playMode = PlayMode.SEQ;
    private int times;


    @Override
    public String play() {
        if (playMode == PlayMode.SEQ) {
            return playInternally(elements);
        }
        if (playMode == PlayMode.RANDOM) {
            List<Playable> temporary = new ArrayList<>(elements);
            Collections.shuffle(temporary);
            return playInternally(temporary);
        }
        if (playMode == PlayMode.LOOP) {
            return IntStream.iterate(1, a -> a + 1)
                    .limit(times)
                    .mapToObj(e -> playInternally(elements))
                    .collect(Collectors.joining("\n"));
        }
        return null;
    }

    private String playInternally(List<Playable> elements) {
        return elements.stream()
                .map(e -> e.play())
                .collect(Collectors.joining("\n"));
    }


    public void addElement(Playable playable) {
        elements.add(playable);
    }

    public void setRandom() {
        this.playMode = PlayMode.RANDOM;
    }

    public void setSeq() {
        this.playMode = PlayMode.SEQ;
    }

    public void setLoop(int times) {
        this.times = times;
        this.playMode = PlayMode.LOOP;
    }
}
