package pe.edu.i202210933.cl3_spring_restful_ponce_rosa.response;

import pe.edu.i202210933.cl3_spring_restful_ponce_rosa.dto.CarDto;

public record FindCarsResponse(String code,
                               String error,
                               Iterable<CarDto> cars) {
}
