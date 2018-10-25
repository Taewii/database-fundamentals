package gamestore.models.utils;

import org.modelmapper.ModelMapper;

public class ModelMapperUtil {

    private final ModelMapper modelMapper;

    public ModelMapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.initialize();
    }

    private void initialize() {

    }
}
