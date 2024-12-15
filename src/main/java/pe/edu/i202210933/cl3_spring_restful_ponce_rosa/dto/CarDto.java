package pe.edu.i202210933.cl3_spring_restful_ponce_rosa.dto;

public record CarDto(Integer carId,
                     String make,
                     String model,
                     Integer year,
                     String licensePlate,
                     String ownerName,
                     String color) {
}
