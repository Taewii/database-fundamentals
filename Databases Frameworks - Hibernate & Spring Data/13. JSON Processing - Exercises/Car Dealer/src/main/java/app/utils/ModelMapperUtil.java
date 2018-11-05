package app.utils;

import app.models.dto.view.SupplierViewModel;
import app.models.entity.Supplier;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ModelMapperUtil {

    private final ModelMapper modelMapper;

    public ModelMapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.initialize();
    }

    private void initialize() {
        setPartCount();
    }

    private void setPartCount() {
        this.modelMapper.addMappings(new PropertyMap<Supplier, SupplierViewModel>() {
            @Override
            protected void configure() {
                map().setPartsCount(source.getParts().size());
            }
        });
    }
}
