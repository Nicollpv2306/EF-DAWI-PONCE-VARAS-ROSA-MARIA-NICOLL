package pe.edu.i202210933.cl3_spring_restful_ponce_rosa.response;

import pe.edu.i202210933.cl3_spring_restful_ponce_rosa.dto.CarDetailDto;

public record FindCarByIdResponse(String code,
                                  String error,
                                  CarDetailDto car) {
}
