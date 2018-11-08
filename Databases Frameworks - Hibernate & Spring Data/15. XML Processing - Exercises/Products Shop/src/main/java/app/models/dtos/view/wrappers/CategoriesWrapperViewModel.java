package app.models.dtos.view.wrappers;

import app.models.dtos.view.categories.CategoriesByProductsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoriesWrapperViewModel implements Serializable {

    @XmlElement(name = "category")
    private List<CategoriesByProductsDto> categories;

    public CategoriesWrapperViewModel() {
    }

    public CategoriesWrapperViewModel(List<CategoriesByProductsDto> categories) {
        this.categories = categories;
    }
}
