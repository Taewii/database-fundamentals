package app.models.dto.view.car;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CarViewWrapper implements Serializable {

    @XmlElement(name = "car")
    private List<CarViewModel> cars;

    public CarViewWrapper() {
    }

    public CarViewWrapper(List<CarViewModel> cars) {
        this.cars = cars;
    }
}
