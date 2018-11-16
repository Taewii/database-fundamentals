package app.petclinic.io;

import app.petclinic.io.api.FileIO;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FIleIOImpl implements FileIO {

    @Override
    public String read(String file) {
        InputStream stream = this.getClass().getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while (null != (line = reader.readLine())) {
                sb.append(line);
            }

            stream.close();
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(String fileContent, String file) {
        try {
            OutputStream outputStream = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(fileContent);
            writer.flush();
            outputStream.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
