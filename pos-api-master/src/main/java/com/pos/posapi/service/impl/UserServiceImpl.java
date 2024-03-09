package com.pos.posapi.service.impl;

import com.pos.posapi.dto.UserDTO;
import com.pos.posapi.dto.requestdto.RequestUserSaveDto;
import com.pos.posapi.dto.responsedto.ResponseUserDataDTO;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.enity.User;
import com.pos.posapi.enity.UserRole;
import com.pos.posapi.exception.EntryNotFoundException;
import com.pos.posapi.jwt.JwtConfig;
import com.pos.posapi.repo.UserRepo;
import com.pos.posapi.repo.UserRoleRepo;
import com.pos.posapi.service.UserService;
import com.pos.posapi.util.Generator;
import com.pos.posapi.util.mapper.UserMapper;
import com.pos.posapi.util.mapper.UserRoleMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final Generator generator;
    private final UserRoleRepo userRoleRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final UserRoleMapper userRoleMapper;

    public UserServiceImpl(UserRepo userRepo, Generator generator, UserRoleRepo userRoleRepo, UserMapper userMapper,
            PasswordEncoder passwordEncoder, JwtConfig jwtConfig, SecretKey secretKey, UserRoleMapper userRoleMapper) {
        this.userRepo = userRepo;
        this.generator = generator;
        this.userRoleRepo = userRoleRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public void initializeUser() throws IOException {
        String email = "kusal.admin@gmail.com";
        User user = userRepo.findByUsername(email);
        String lastId = userRepo.findLastId("POS-U-", 7);

        String id = "POS-U-1";

        if (null != lastId) {
            int i = (Integer.parseInt(lastId.split("POS-U-")[1])) + 1;
            id = "POS-U-" + i;
        }
        if (user == null) {
            String password = "1234";

            // =============================
            UserRole userRole = userRoleRepo.findUserRoleByRoleName("ADMIN");
            int otp = generator.generateOtp();
            String prefix = generator.generatePrefix();
            UserDTO userDTO = new UserDTO(
                    id,
                    true,
                    email,
                    "Kusal",
                    "Dissanayake",
                    true,
                    true,
                    true,
                    true,
                    passwordEncoder.encode(password),
                    new Date(),
                    email,
                    prefix,
                    otp,
                    userRoleMapper.toUserRoleDto(userRole));
            userRepo.save(userMapper.toUser(userDTO));
        }
    }

    @Override
    public CommonResponseDTO createUser(RequestUserSaveDto userDTO) {
        UserRole userRole = userRoleRepo.findUserRoleByRoleName("CUSTOMER");
        User userByUsername = userRepo.findByUsername(userDTO.getEmail());
        int otp = generator.generateOtp();
        String prefix = generator.generatePrefix();
        String lastId = userRepo.findLastId("POS-U-", 7);

        String id = "POS-U-1";

        if (null != lastId) {
            int i = (Integer.parseInt(lastId.split("POS-U-")[1])) + 1;
            id = "POS-U-" + i;
        }
        UserDTO newUserDTO = new UserDTO(
                id,
                true,
                userDTO.getEmail(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                true,
                true,
                true,
                true,
                passwordEncoder.encode(userDTO.getPassword()),
                new Date(),
                userDTO.getEmail(),
                prefix,
                otp,
                userRoleMapper.toUserRoleDto(userRole));
        userRepo.save(userMapper.toUser(newUserDTO));
        return new CommonResponseDTO(
                201, "USER_REGISTRATION_SUCCESSFULLY", userDTO.getEmail(), new ArrayList<>());
    }

    @Override
    public List<ResponseUserDataDTO> getAll() {
        List<User> all = userRepo.findAll();
        return userMapper.toResponseUserDataList(all);

    }

    @Override
    public CommonResponseDTO deleteUser(String id) {
        Optional<User> user = userRepo.findUserById(id);
        if (user.isEmpty()) {
            throw new EntryNotFoundException("User Not Found");
        }
        userRepo.delete(user.get());
        return new CommonResponseDTO(
                204,
                "USER DELETED SUCCESSFULLY",
                user.get().getUserId(),
                new ArrayList<>());
    }

    @Override
    public CommonResponseDTO verifyUser(int otp, String email) {
        User selectedUser = userRepo.findUserByEmail(email);
        if (selectedUser != null) {
            if (selectedUser.isEnabled()) {
                return new CommonResponseDTO(409, "USER_ALREADY_ACTIVATED", "", new ArrayList<>());
            } else {
                if (selectedUser.getOtp() == otp) {
                    selectedUser.setEnabled(true);
                    selectedUser.setOtp(0);
                    userRepo.save(selectedUser);
                    return new CommonResponseDTO(201, "USER_SUCCESSFULLY_ACTIVATED", selectedUser.getUserId(),
                            new ArrayList<>());
                } else {
                    return new CommonResponseDTO(400, "WRONG_OTP", "", new ArrayList<>());
                }
            }
        } else {
            return new CommonResponseDTO(404, "EMAIL_NOT_FOUND", "", new ArrayList<>());
        }
    }

    @Override
    public ResponseUserDataDTO getAllUserData(String token) {
        String realToken = token.replace(jwtConfig.getTokenPrefix(), "");
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(realToken);
        String username = claimsJws.getBody().getSubject();
        User selectedUser = userRepo.findByUsername(username);
        if (selectedUser == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return new ResponseUserDataDTO(
                selectedUser.getUserId(),
                selectedUser.getEmail(),
                selectedUser.getFirstName(),
                selectedUser.getLastName(),
                selectedUser.getUserRole().getRoleId(),
                selectedUser.getUserRole().getRoleName(),
                selectedUser.getPrefix());
    }

}
