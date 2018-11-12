package org.softuni.mostwanted.io.impl;

import org.softuni.mostwanted.io.interfaces.FileIO;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIOImpl implements FileIO {

    @Override
    public String read(String file) {
        StringBuilder sb = new StringBuilder();
        InputStream stream = this.getClass().getResourceAsStream(file);

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

    @Override
    public void write(String fileContent, String file) {
        try {
            OutputStream outputStream = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(fileContent);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
