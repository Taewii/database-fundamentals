package app.petclinic.io.api;

public interface FileIO {

    String read(String file);

    void write(String fileContent, String file);
}
