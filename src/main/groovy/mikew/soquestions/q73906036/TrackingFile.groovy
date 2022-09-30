package mikew.soquestions.q73906036

import java.io.IOException;
import java.util.ArrayList;

public abstract class TrackingFile {

    protected ArrayList<String> items;

    public TrackingFile(ArrayList<String> items) {
        this.items = items;
    }

    abstract void createTheFile() throws IOException;

    abstract void writingTheFile() throws IOException;

}