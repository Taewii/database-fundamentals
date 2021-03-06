package app;

import app.utils.ModelMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarDealerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarDealerApplication.class, args);
    }

    @Bean
    public ModelMapper configureMapper() {
        ModelMapper modelMapper = new ModelMapper();
        ModelMapperUtil modelMapperUtil = new ModelMapperUtil(modelMapper);
        return modelMapper;
    }
}
