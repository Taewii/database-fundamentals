package app.petclinic.parser.interfaces;

public interface Parser {

    <T> T read(Class<T> objectClass, String fileContent);

    <T> String write(T object);
}
