package com.service.service.service;



import com.service.service.model.DoctorAuthResponse;
import com.service.service.model.PatientAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientServiceClient {

    @Autowired
    private RestTemplate restTemplate;


    public PatientAuthResponse getPatientByEmail(String email) {
        String url = "http:/patient-service/api/doctors/authenticate?email=" + email;


        ResponseEntity<PatientAuthResponse> response = restTemplate.getForEntity(url, PatientAuthResponse.class);

        return response.getBody();
    }
}
