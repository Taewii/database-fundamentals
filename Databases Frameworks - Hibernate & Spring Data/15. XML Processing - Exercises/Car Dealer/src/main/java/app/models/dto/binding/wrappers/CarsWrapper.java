package app.models.dto.binding.wrappers;

import app.models.dto.binding.CarDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "cars")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CarsWrapper implements Serializable {

    @XmlElement(name = "car")
    private CarDto[] cars;

    public CarsWrapper() {
    }

    public CarDto[] getCars() {
        return this.cars;
    }
}
