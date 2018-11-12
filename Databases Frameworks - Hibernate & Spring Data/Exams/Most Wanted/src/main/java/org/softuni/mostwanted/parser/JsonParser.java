package org.softuni.mostwanted.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.springframework.stereotype.Component;

@Component
public class JsonParser implements Parser {

    private final Gson gson;

    public JsonParser() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
//                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) {
        return this.gson.fromJson(fileContent, objectClass);
    }

    @Override
    public <T> String write(T object) {
        return this.gson.toJson(object);
    }
}
