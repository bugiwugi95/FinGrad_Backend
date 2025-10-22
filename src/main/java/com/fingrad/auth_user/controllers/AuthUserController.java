package com.fingrad.auth_user.controllers;

import com.fingrad.auth_user.dto.request.RegistrationRequest;
import com.fingrad.auth_user.dto.response.MessageResponse;
import com.fingrad.auth_user.services.AuthUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/auth/")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"}) // ← ДОБАВЬТЕ ЭТУ СТРОКУ
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Регистрация и авторизация пользователей")
public class AuthUserController {

  private final AuthUserService authUserService;

  @Operation(
      summary = "Регистрация нового пользователя",
      description = "Создает нового пользователя с ролью USER и возвращает JWT токен"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Пользователь успешно зарегистрирован",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = MessageResponse.class))),
      @ApiResponse(responseCode = "400", description = "Некорректные данные запроса",
          content = @Content),
      @ApiResponse(responseCode = "409", description = "Пользователь с таким username уже существует",
          content = @Content)
  })
  @PostMapping("/register")
  public ResponseEntity<MessageResponse> registerUser(
      @RequestBody @Valid RegistrationRequest request) {

    return ResponseEntity.status(HttpStatus.CREATED).body(authUserService.registrationUser(request));
  }

}

