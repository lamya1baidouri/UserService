package com.service.service.controller;

import com.service.service.model.*;
import com.service.service.interfaces.UserService;
import com.service.service.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
@Tag(name = "User Management", description = "Operations for user authentication and registration")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    /**
     * Authentifier un médecin
     * @param loginRequest les informations de connexion (email et mot de passe)
     * @return un token JWT si l'authentification réussit
     */
    @Operation(summary = "Authenticate a doctor", description = "Authenticates a doctor using their email and password, and returns a JWT token if successful.")
    @PostMapping("/authenticate/doctor")
    public ResponseEntity<String> authenticateDoctor(
            @Parameter(description = "Login request containing email and password") @Valid @RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = userService.authenticateDoctor(loginRequest.getEmail());

        // Vérifier le mot de passe
        if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            String token = jwtService.generateToken(userDetails);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    /**
     * Authentifier un patient
     * @param loginRequest les informations de connexion (email et mot de passe)
     * @return un token JWT si l'authentification réussit
     */
    @Operation(summary = "Authenticate a patient", description = "Authenticates a patient using their email and password, and returns a JWT token if successful.")
    @PostMapping("/authenticate/patient")
    public ResponseEntity<String> authenticatePatient(
            @Parameter(description = "Login request containing email and password") @Valid @RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = userService.authenticatePatient(loginRequest.getEmail());

        // Vérifier le mot de passe
        if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            String token = jwtService.generateToken(userDetails);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    /**
     * Créer un nouveau médecin (envoie les données au service externe)
     * @param doctorRequestDTO Les informations du médecin à créer
     * @return Le médecin créé
     */
    @Operation(summary = "Register a new doctor", description = "Registers a new doctor by sending their details to the external doctor service.")
    @PostMapping("/register/doctor")
    public ResponseEntity<String> registerDoctor(
            @Parameter(description = "Doctor details for registration") @Valid @RequestBody DoctorRequestDTO doctorRequestDTO) {
        // Appeler le service utilisateur pour envoyer les informations au service médecin
        String response = userService.registerDoctor(doctorRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Mettre à jour un médecin existant (envoie les données au service externe)
     * @param userId L'ID du médecin à mettre à jour
     * @param doctorUpdateDTO Les nouvelles informations du médecin
     * @return Le médecin mis à jour
     */
    @Operation(summary = "Update an existing doctor", description = "Updates an existing doctor's information by sending updated details to the external doctor service.")
    @PutMapping("/update/doctor/{userId}")
    public ResponseEntity<String> updateDoctor(
            @Parameter(description = "ID of the doctor to update") @PathVariable Long userId,
            @Parameter(description = "Updated doctor information") @Valid @RequestBody DoctorUpdateDTO doctorUpdateDTO) {
        // Appeler le service utilisateur pour envoyer les informations de mise à jour au service médecin
        String response = userService.updateDoctor(userId, doctorUpdateDTO);
        return ResponseEntity.ok(response);
    }
}
