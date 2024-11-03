package com.service.service.service;;

import com.service.service.interfaces.UserService;

import com.service.service.model.DoctorAuthResponse;
import com.service.service.model.DoctorRequestDTO;
import com.service.service.model.DoctorUpdateDTO;
import com.service.service.model.PatientAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public DoctorServiceClient doctorServiceClient;  // Appel au service médecin
    @Autowired
    private PatientServiceClient patientServiceClient;  // Appel au service patient
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserDetails authenticateDoctor(String email) {
        // Appel au service médecin pour récupérer les informations d'authentification
        DoctorAuthResponse doctorAuthResponse = doctorServiceClient.getDoctorByEmail(email);

        if (doctorAuthResponse == null) {
            throw new UsernameNotFoundException("Médecin non trouvé");
        }


        Collection<GrantedAuthority> authorities = doctorAuthResponse.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                doctorAuthResponse.getEmail(),
                doctorAuthResponse.getPassword(),
                authorities
        );
    }

    @Override
    public UserDetails authenticatePatient(String email) {

        PatientAuthResponse patientAuthResponse = patientServiceClient.getPatientByEmail(email);

        if (patientAuthResponse == null) {
            throw new UsernameNotFoundException("Patient non trouvé");
        }


        Collection<GrantedAuthority> authorities = patientAuthResponse.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                patientAuthResponse.getEmail(),
                patientAuthResponse.getPassword(),
                authorities
        );
    }

    @Override
    public String registerDoctor(DoctorRequestDTO doctorRequestDTO) {
        // Appeler le service médecin pour créer le compte du médecin
        return doctorServiceClient.createDoctor(doctorRequestDTO);
    }

    @Override
    public String updateDoctor(Long userId, DoctorUpdateDTO doctorUpdateDTO) {
        // Appeler le service médecin pour mettre à jour le compte du médecin
        return doctorServiceClient.updateDoctor(userId, doctorUpdateDTO);
    }
}
