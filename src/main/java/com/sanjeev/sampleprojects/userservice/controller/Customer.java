package com.sanjeev.sampleprojects.userservice.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
public class Customer {

    @Schema(description = "Unique identifier of the Customer.", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    @Schema(description = "Name of the Customer.", example = "Sanjeev", required = true)
    @NotBlank
    private String name;

    @Schema(description = "Vin of the Customer.", example = "JTKKU10479J033714", required = true)
    @NotBlank(message="vin should not be blank")
    @Size(min = 17, max = 17)
    private String vin;
}
