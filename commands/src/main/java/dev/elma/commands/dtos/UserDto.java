package dev.elma.commands.dtos;

import dev.elma.commands.entities.ProfileEntity;
import dev.elma.commands.entities.RoleEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDto {
    private String username;
    private String email;
    private String password;
    private ProfileEntity profile;
}
