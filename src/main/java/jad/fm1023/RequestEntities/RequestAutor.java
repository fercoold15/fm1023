package jad.fm1023.RequestEntities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAutor {
    @NotEmpty
    @Size(min = 5,max = 50)
    private String nombreAutor;
}
