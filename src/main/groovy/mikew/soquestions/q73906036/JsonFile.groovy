package mikew.soquestions.q73906036

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonFile extends TrackingFile {

    public JsonFile(ArrayList<String> items) {
        super(items);
    }

    static String json = "";
    static Gson gson = new GsonBuilder().enableComplexMapKeySerialization().serializeNulls().setPrettyPrinting()
            .setVersion(1.0).create();

    @Override
    void createTheFile() {
        json = gson.toJson(super.items);
        System.out.println(json);

    }

    @Override
    void writingTheFile() throws IOException {
        String str = json;
        BufferedWriter writer = new BufferedWriter(new FileWriter("./items.json"));
        writer.write(str);
        writer.close();

    }

}
