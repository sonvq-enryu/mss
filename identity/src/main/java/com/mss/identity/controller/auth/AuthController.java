package com.mss.identity.controller.auth;

import com.mss.identity.dto.auth.AuthDto;
import com.mss.identity.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/user/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "User Sign-up API", description = "Sign-up new account for user role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuthDto.SignUpResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<AuthDto.SignUpResponse> signUp(@RequestBody @Valid AuthDto.SignUpRequest request) {
        return ResponseEntity.ok(authService.userSignUp(request));
    }

    @PostMapping(value = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "User Sign-in API", description = "Sign-in existing account for user role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuthDto.SignInResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<AuthDto.SignInResponse> signIn(@RequestBody @Valid AuthDto.SignInRequest request) {
        return ResponseEntity.ok(authService.signIn(request));
    }
}
