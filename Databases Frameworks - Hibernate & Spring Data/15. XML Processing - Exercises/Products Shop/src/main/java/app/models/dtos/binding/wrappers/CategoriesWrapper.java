package app.models.dtos.binding.wrappers;

import app.models.dtos.binding.CategoryDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "categories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoriesWrapper {

    @XmlElement(name = "category")
    private CategoryDto[] categories;

    public CategoryDto[] getCategories() {
        return this.categories;
    }

    public void setCategories(CategoryDto[] categories) {
        this.categories = categories;
    }
}
