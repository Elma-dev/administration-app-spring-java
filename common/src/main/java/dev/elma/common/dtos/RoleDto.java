package dev.elma.common.dtos;

import dev.elma.common.enums.RoleName;
import lombok.*;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class RoleDto {
    private RoleName roleName;
}
