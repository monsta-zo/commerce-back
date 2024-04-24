package org.example.commerceback.user;

import lombok.RequiredArgsConstructor;
import org.example.commerceback._core.errors.CustomException;
import org.example.commerceback._core.errors.ExceptionCode;
import org.example.commerceback._core.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public UserResponse.LoginDTO register(UserRequest.RegisterDTO requestDTO, String type) {
        checkEmail(requestDTO.email());

        if(!requestDTO.password().equals(requestDTO.passwordConfirm()))
            throw new CustomException(ExceptionCode.USER_PASSWORD_WRONG);

        if(type.equals("Seller")){
            Seller seller = Seller.builder()
                    .email(requestDTO.email())
                    .name(requestDTO.name())
                    .password(passwordEncoder.encode(requestDTO.password()))
                    .role(Role.USER)
                    .build();

            var id = userRepository.save(seller).getId();
            var jwtToken = jwtService.generateToken(seller);
            return new UserResponse.LoginDTO(jwtToken, "seller", id);

        } else {
            Buyer buyer = Buyer.builder()
                    .email(requestDTO.email())
                    .name(requestDTO.name())
                    .password(passwordEncoder.encode(requestDTO.password()))
                    .role(Role.USER)
                    .build();

            var id = userRepository.save(buyer).getId();
            var jwtToken = jwtService.generateToken(buyer);
            return new UserResponse.LoginDTO(jwtToken, "buyer", id);
        }
    }

    public UserResponse.LoginDTO login(UserRequest.LoginDTO requestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.email(),
                        requestDTO.password()
                )
        );
        var user = userRepository.findByEmail(requestDTO.email()).orElseThrow(() -> new CustomException(ExceptionCode.USER_NO_EXIST));
        var jwtToken = jwtService.generateToken(user);
        String role = "";
        if(user instanceof Seller) role = "seller";
        else role = "buyer";
        return new UserResponse.LoginDTO(jwtToken, role, user.getId());

    }

    // 이메일 중복 체크
    private void checkEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) throw new CustomException(ExceptionCode.USER_EMAIL_EXIST);
    }
}
