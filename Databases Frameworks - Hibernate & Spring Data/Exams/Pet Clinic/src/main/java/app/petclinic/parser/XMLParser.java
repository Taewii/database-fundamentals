package app.petclinic.parser;

import app.petclinic.parser.interfaces.Parser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component(value = "XMLParser")
public class XMLParser implements Parser {

    private final DateTimeAdapter dateTimeAdapter;

    public XMLParser() {
        this.dateTimeAdapter = new DateTimeAdapter();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T read(Class<T> objectClass, String fileContent) {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(fileContent));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> String write(T object) {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            final Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.displayName());
            marshaller.setAdapter(this.dateTimeAdapter);

            final StringWriter writer = new StringWriter();
            marshaller.marshal(object, writer);

            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Component
    public static class DateTimeAdapter extends XmlAdapter<String, Date> {
        private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        @Override
        public Date unmarshal(String date) throws Exception {
            return this.format.parse(date);
        }

        @Override
        public String marshal(Date date) {
            return this.format.format(date);
        }
    }
}
