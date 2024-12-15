package pe.edu.i202210933.cl3_spring_restful_ponce_rosa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.i202210933.cl3_spring_restful_ponce_rosa.dto.CarDetailDto;
import pe.edu.i202210933.cl3_spring_restful_ponce_rosa.dto.CarDto;
import pe.edu.i202210933.cl3_spring_restful_ponce_rosa.entity.Car;
import pe.edu.i202210933.cl3_spring_restful_ponce_rosa.repository.CarRepository;
import pe.edu.i202210933.cl3_spring_restful_ponce_rosa.service.CarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() throws Exception {
        List<CarDto> cars = new ArrayList<>();
        Iterable<Car> iterable = carRepository.findAll();

        if (!iterable.iterator().hasNext()) {
            throw new Exception("Cars no encontrados");
        }

        iterable.forEach(car -> {
            CarDto dto = new CarDto(
                    car.getCarId(),
                    car.getMake(),
                    car.getModel(),
                    car.getYear(),
                    car.getLicensePlate(),
                    car.getOwnerName(),
                    car.getColor()
            );
            cars.add(dto);
        });

        return cars;
    }

    @Override
    public Optional<CarDto> getAllCarsById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);

        if (optional.isEmpty()) {
            throw new Exception("Car con ID " + id + " no fue encontrado");
        }

        return optional.map(car -> new CarDto(
                car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getLicensePlate(),
                car.getOwnerName(),
                car.getColor()
        ));
    }

    @Override
    public Optional<CarDetailDto> getCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);

        if (optional.isEmpty()) {
            throw new Exception("Car with ID " + id + " not found");
        }

        return optional.map(car -> new CarDetailDto(
                car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getVin(),
                car.getLicensePlate(),
                car.getOwnerName(),
                car.getOwnerContact(),
                car.getPurchaseDate(),
                car.getMileage(),
                car.getEngineType(),
                car.getColor(),
                car.getInsuranceCompany(),
                car.getInsurancePolicyNumber(),
                car.getRegistrationExpirationDate(),
                car.getServiceDueDate()
        ));
    }

    @Override
    public boolean updateCar(CarDto carUpdateDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carUpdateDto.carId());

        if (optional.isEmpty()) {
            throw new Exception("Car con ID " + carUpdateDto.carId() + " no fue encontrado para actualizar");
        }

        optional.ifPresent(car -> {
            car.setMake(carUpdateDto.make());
            car.setModel(carUpdateDto.model());
            car.setYear(carUpdateDto.year());
            car.setLicensePlate(carUpdateDto.licensePlate());
            car.setOwnerName(carUpdateDto.ownerName());
            car.setColor(carUpdateDto.color());
            carRepository.save(car);
        });

        return true;
    }

    @Override
    public boolean deleteCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);

        if (optional.isEmpty()) {
            throw new Exception("Car con ID " + id + " no fue encontrado para eliminar");
        }

        optional.ifPresent(carRepository::delete);
        return true;
    }

    @Override
    public boolean addCar(CarDetailDto carDetailDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDetailDto.carId());

        if (optional.isPresent()) {
            throw new Exception("Car con ID " + carDetailDto.carId() + " ya existe");
        }

        Car car = new Car();
        car.setMake(carDetailDto.make());
        car.setModel(carDetailDto.model());
        car.setYear(carDetailDto.year());
        car.setVin(carDetailDto.vin());
        car.setLicensePlate(carDetailDto.licensePlate());
        car.setOwnerName(carDetailDto.ownerName());
        car.setOwnerContact(carDetailDto.ownerContact());
        car.setPurchaseDate(carDetailDto.purchaseDate());
        car.setMileage(carDetailDto.mileage());
        car.setEngineType(carDetailDto.engineType());
        car.setColor(carDetailDto.color());
        car.setInsuranceCompany(carDetailDto.insuranceCompany());
        car.setInsurancePolicyNumber(carDetailDto.insurancePolicyNumber());
        car.setRegistrationExpirationDate(carDetailDto.registrationExpirationDate());
        car.setServiceDueDate(carDetailDto.serviceDueDate());
        carRepository.save(car);

        return true;
    }
}