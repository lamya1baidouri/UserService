package com.service.service.interfaces;

import com.service.service.model.DoctorRequestDTO;
import com.service.service.model.DoctorUpdateDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails authenticateDoctor(String email);

    UserDetails authenticatePatient(String email);

    String registerDoctor(DoctorRequestDTO doctorRequestDTO);

    String updateDoctor(Long userId, DoctorUpdateDTO doctorUpdateDTO);
}

