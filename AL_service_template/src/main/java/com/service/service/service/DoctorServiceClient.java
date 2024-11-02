package com.service.service.service;

import com.service.service.model.DoctorAuthResponse;
import com.service.service.model.DoctorRequestDTO;
import com.service.service.model.DoctorUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DoctorServiceClient {

    @Autowired
    private RestTemplate restTemplate;


    public DoctorAuthResponse getDoctorByEmail(String email) {
        String url = "http://doctor-service/api/doctors/authenticate?email=" + email;


        ResponseEntity<DoctorAuthResponse> response = restTemplate.getForEntity(url, DoctorAuthResponse.class);

        return response.getBody();
    }
    public String createDoctor(DoctorRequestDTO doctorRequestDTO) {
        String url = "http://doctor-service/api/doctors";
        ResponseEntity<String> response = restTemplate.postForEntity(url, doctorRequestDTO, String.class);
        return response.getBody();
    }

    public String updateDoctor(Long userId, DoctorUpdateDTO doctorUpdateDTO) {
        String url = "http://doctor-service/api/doctors/" + userId;
        restTemplate.put(url, doctorUpdateDTO);
        return "Médecin mis à jour avec succès";
    }
}
