package dev.elma.commands.dtos;

import dev.elma.commands.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class RoleDto {
    private RoleName roleName;
}
