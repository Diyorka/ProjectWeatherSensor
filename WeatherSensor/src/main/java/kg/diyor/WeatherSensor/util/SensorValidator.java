package kg.diyor.WeatherSensor.util;

import kg.diyor.WeatherSensor.models.Sensor;
import kg.diyor.WeatherSensor.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if(sensorService.findByName(sensor.getName()).isPresent()){
            errors.rejectValue("name", "Sensor with this name already exists");
        }
    }
}
