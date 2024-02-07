package dev.elma.dtos;

import dev.elma.enums.RoleName;
import lombok.*;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class RoleDto {
    private RoleName roleName;
}
