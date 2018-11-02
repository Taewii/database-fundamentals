package app.models.dto.binding;

import java.io.Serializable;

public class SupplierDto implements Serializable {

    private String name;
    private boolean isImporter;

    public SupplierDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return this.isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
