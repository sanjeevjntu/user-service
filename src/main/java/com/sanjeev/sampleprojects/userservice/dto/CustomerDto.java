package com.sanjeev.sampleprojects.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @Schema(description = "Unique identifier of the Customer.", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
    private UUID id;

    @Schema(description = "Name of the Customer.", example = "Sanjeev", required = true)
    @NotBlank
    private String name;

    @Schema(description = "Vin of the Customer.", example = "JTKKU10479J033714", required = true)
    @NotBlank
    @Size(min = 17, max = 17)
    private String vin;
}
