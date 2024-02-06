package dev.elma.commands.dtos;


import dev.elma.commands.entities.RoleEntity;
import dev.elma.commands.enums.RoleName;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProfileDtoRequest {
    private String profileName;
    private List<RoleName> rolesNames;
}
