package dev.elma.commands.dtos;

import dev.elma.commands.entities.ProfileEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDtoRequest {
    private String username;
    private String email;
    private String password;
    private String profileName;
}
