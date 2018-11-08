package app.models.dto.view.car;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CarViewModelWithPartsWrapper implements Serializable {

    @XmlElement(name = "car")
    private List<CarViewModelWithParts> cars;

    public CarViewModelWithPartsWrapper() {
    }

    public CarViewModelWithPartsWrapper(List<CarViewModelWithParts> cars) {
        this.cars = cars;
    }
}
