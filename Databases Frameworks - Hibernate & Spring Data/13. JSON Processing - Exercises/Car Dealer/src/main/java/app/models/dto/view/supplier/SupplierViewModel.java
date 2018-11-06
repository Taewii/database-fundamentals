package app.models.dto.view.supplier;

import java.io.Serializable;

public class SupplierViewModel implements Serializable {

    private Long id;
    private String name;
    private Integer partsCount;

    public SupplierViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartsCount() {
        return this.partsCount;
    }

    public void setPartsCount(Integer partsCount) {
        this.partsCount = partsCount;
    }
}
