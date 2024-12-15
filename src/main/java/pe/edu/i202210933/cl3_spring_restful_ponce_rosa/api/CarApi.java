package pe.edu.i202210933.cl3_spring_restful_ponce_rosa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.i202210933.cl3_spring_restful_ponce_rosa.dto.CarDetailDto;
import pe.edu.i202210933.cl3_spring_restful_ponce_rosa.dto.CarDto;
import pe.edu.i202210933.cl3_spring_restful_ponce_rosa.response.*;
import pe.edu.i202210933.cl3_spring_restful_ponce_rosa.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("manage-car")
public class CarApi {

    @Autowired
    CarService carService;

    @GetMapping("/all")
    public FindCarsResponse findCars(@RequestParam(value = "id", defaultValue = "0") String id) {
        try {
            if (Integer.parseInt(id) > 0) {
                Optional<CarDto> optional = carService.getAllCarsById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    CarDto carDto = optional.get();
                    return new FindCarsResponse("01", "", List.of(carDto));
                } else {
                    return new FindCarsResponse("02", "Car no encontrado", null);
                }
            } else {
                List<CarDto> cars = carService.getAllCars();
                if (!cars.isEmpty()) {
                    return new FindCarsResponse("01", "", cars);
                } else {
                    return new FindCarsResponse("03", "Listado de car no encontrado", cars);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarsResponse("99", "Service no encontrado", null);
        }
    }

    @GetMapping("/detail")
    public FindCarByIdResponse findCarById(@RequestParam(value = "id", defaultValue = "0") String id) {
        try {
            if (Integer.parseInt(id) > 0) {
                Optional<CarDetailDto> optional = carService.getCarById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    CarDetailDto carDetailDto = optional.get();
                    return new FindCarByIdResponse("01", "", carDetailDto);
                } else {
                    return new FindCarByIdResponse("02", "Car no encontrado", null);
                }
            } else {
                return new FindCarByIdResponse("03", "Parametro incorrecto", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarByIdResponse("99", "Service no encontrado", null);
        }
    }

    @PostMapping("/update")
    public UpdateCarResponse updateCar(@RequestBody CarDto carDto) {
        try {
            if (carService.updateCar(carDto)) {
                return new UpdateCarResponse("01", "");
            } else {
                return new UpdateCarResponse("02", "Car no fue actualizado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCarResponse("99", "Service no encontrado");
        }
    }

    @PostMapping("/create")
    public CreateCarResponse createCar(@RequestBody CarDetailDto carDetailDto) {
        try {
            if (carService.addCar(carDetailDto)) {
                return new CreateCarResponse("01", "");
            } else {
                return new CreateCarResponse("02", "Car no fue creado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CreateCarResponse("99", "Service no encontrado");
        }
    }

    @DeleteMapping("/delete")
    public DeleteCarResponse deleteCar(@RequestParam(value = "id", defaultValue = "0") String cardId) {
        try {
            if (Integer.parseInt(cardId) > 0) {
                if (carService.deleteCarById(Integer.parseInt(cardId))) {
                    return new DeleteCarResponse("01", "");
                } else {
                    return new DeleteCarResponse("02", "Car no fue eliminado");
                }
            } else {
                return new DeleteCarResponse("03", "Parametro incorrecto");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCarResponse("99", "Service no encontrado");
        }
    }
}
