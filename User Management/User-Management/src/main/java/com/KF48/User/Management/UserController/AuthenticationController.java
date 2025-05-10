package com.KF48.User.Management.UserController;

import com.KF48.User.Management.UserModel.User;
import com.KF48.User.Management.UserService.AuthenticationService;
import com.KF48.User.Management.dto.JwtAuthenticationResponse;
import com.KF48.User.Management.dto.RefreshTokenRequest;
import com.KF48.User.Management.dto.SignUpRequest;
import com.KF48.User.Management.dto.SigninRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "USER AUTHENTICATION")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
private final AuthenticationService authenticationService;
@Tag(name = "USER SIGNUP")
@PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
    return ResponseEntity.ok(authenticationService.signUp(signUpRequest));

}
 @Tag(name = "USER SIGNIN")
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SigninRequest  signinRequest) {
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }
     @Tag(name = "USER REFRESH TOKEN")
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }


}
