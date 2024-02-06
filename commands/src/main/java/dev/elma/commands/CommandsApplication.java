package dev.elma.commands;


import dev.elma.commands.dtos.ProfileDto;
import dev.elma.commands.dtos.UserDto;
import dev.elma.commands.entities.ProfileEntity;
import dev.elma.commands.entities.RoleEntity;
import dev.elma.commands.entities.UserEntity;
import dev.elma.commands.enums.RoleName;
import dev.elma.commands.mappers.ProfileMapper;
import dev.elma.commands.mappers.UserMapper;
import dev.elma.commands.repositories.ProfileRepository;
import dev.elma.commands.repositories.RoleRepository;
import dev.elma.commands.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class CommandsApplication implements CommandLineRunner {
    ProfileRepository profileRepository;
    RoleRepository roleRepository;
    UserRepository userRepository;



    public static void main(String[] args) {
        SpringApplication.run(CommandsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserMapper userMapper = new UserMapper();
        ProfileMapper profileMapper = new ProfileMapper();

        RoleEntity admin = RoleEntity.builder().roleName(RoleName.ADMIN).build();
        RoleEntity user = RoleEntity.builder().roleName(RoleName.USER).build();
        roleRepository.save(admin);
        roleRepository.save(user);







        List.of("Manager", "Commercial").forEach(profileName -> {

            ProfileDto profileDto1 = ProfileDto.builder()
                    .profileName(profileName)
                    .roles(profileName.equals("Manager")?List.of(admin, user):List.of(user))
                    .build();
            ProfileEntity profile = profileMapper.toProfileEntity(profileDto1);
            ProfileEntity save = profileRepository.save(profile);
                    System.out.println(save);
        }


        );
        List.of("Ahmed", "Ali", "Mohamed").forEach(username -> {
            ProfileEntity profile1 = profileRepository.findById(1L).orElse(null);
            ProfileEntity profile2 = profileRepository.findById(2L).orElse(null);


            UserDto userDto = UserDto.builder()
                    .username(username)
                    .email(username + "@elma.dev")
                    .profile(Math.random()<0.5?profile1:profile2)
                    .password("password")
                    .build();
            UserEntity user1 = userMapper.toUserEntity(userDto);
            UserEntity save = userRepository.save(user1);
        });


    }
}
