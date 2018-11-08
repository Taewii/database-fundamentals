package app.utils;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIO {

    public String read(String fileName) {
        StringBuilder sb = new StringBuilder();
        InputStream stream = this.getClass().getResourceAsStream(fileName);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;

            while (null != (line = reader.readLine())) {
                sb.append(line);
            }

            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public void write(String content, String fileName) {
        try {
            OutputStream outputStream = new FileOutputStream(fileName);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
