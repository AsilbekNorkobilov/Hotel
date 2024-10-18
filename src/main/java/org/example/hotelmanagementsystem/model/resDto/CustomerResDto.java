package org.example.hotelmanagementsystem.model.resDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link org.example.hotelmanagementsystem.entity.User}
 */
@Value
public class CustomerResDto implements Serializable {
    UUID id;
    @NotNull
    @Email
    @NotEmpty
    @NotBlank
    String email;
    @NotNull
    @NotEmpty
    @NotBlank
    String firstName;
    @NotNull
    @NotEmpty
    @NotBlank
    String lastName;
}