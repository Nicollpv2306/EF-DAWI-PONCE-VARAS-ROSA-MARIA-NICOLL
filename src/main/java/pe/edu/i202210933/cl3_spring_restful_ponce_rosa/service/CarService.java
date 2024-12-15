package pe.edu.i202210933.cl3_spring_restful_ponce_rosa.service;

import pe.edu.i202210933.cl3_spring_restful_ponce_rosa.dto.CarDetailDto;
import pe.edu.i202210933.cl3_spring_restful_ponce_rosa.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<CarDto> getAllCars() throws Exception;

    Optional<CarDto> getAllCarsById(int id) throws Exception;

    Optional<CarDetailDto> getCarById(int id) throws Exception;

    boolean deleteCarById(int id) throws Exception;

    boolean addCar(CarDetailDto carDetailDto) throws Exception;

    boolean updateCar(CarDto carDto) throws Exception;

}
