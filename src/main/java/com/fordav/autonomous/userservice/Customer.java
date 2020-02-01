package com.fordav.autonomous.userservice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Schema(description = "Unique identifier of the Customer.", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Schema(description = "Name of the Customer.", example = "Sanjeev", required = true)
    @NotBlank(message="customer name should not be blank")
    private String name;

    @Schema(description = "Vin of the Customer.", example = "VIN1234567890123", required = true)
    @NotBlank(message="vin should not be blank")
    @Size(message = "vin should be 17 length", min = 17, max = 17)
    private String vin;
}
