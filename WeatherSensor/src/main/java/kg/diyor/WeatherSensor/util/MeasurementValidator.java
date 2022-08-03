package kg.diyor.WeatherSensor.util;

import kg.diyor.WeatherSensor.models.Measurement;
import kg.diyor.WeatherSensor.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if(measurement.getSensor() == null){
            return;
        }

        if(sensorService.findByName(measurement.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor", "There isn't registered sensor with this name");
        }
    }
}
