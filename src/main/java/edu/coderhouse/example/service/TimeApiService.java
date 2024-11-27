package edu.coderhouse.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;


@Service
public class TimeApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    public LocalDateTime getCurrentDateTime() {
        String url = "https://www.timeapi.io/api/Time/current/zone?timeZone=America/Argentina/Buenos_Aires";
        TimeApiResponse response = restTemplate.getForObject(url, TimeApiResponse.class);
        return response != null ? response.getDateTime() : null;
    }

    private static class TimeApiResponse {
        private LocalDateTime dateTime;

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }
    }


}
