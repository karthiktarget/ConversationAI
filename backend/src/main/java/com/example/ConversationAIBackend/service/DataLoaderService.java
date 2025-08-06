package com.example.ConversationAIBackend.service;

import com.example.ConversationAIBackend.model.User;
import com.example.ConversationAIBackend.repository.UserRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.time.LocalDateTime;

@Component
public class DataLoaderService implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/datasets/users.csv"))) {
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                User user = new User();
                user.setId(Long.parseLong(nextLine[0]));
                user.setFirstName(nextLine[1]);
                user.setLastName(nextLine[2]);
                user.setEmail(nextLine[3]);
                user.setAge(Integer.parseInt(nextLine[4]));
                user.setGender(nextLine[5]);
                user.setState(nextLine[6]);
                user.setStreetAddress(nextLine[7]);
                user.setPostalCode(nextLine[8]);
                user.setCity(nextLine[9]);
                user.setCountry(nextLine[10]);
                user.setLatitude(Double.parseDouble(nextLine[11]));
                user.setLongitude(Double.parseDouble(nextLine[12]));
                user.setTrafficSource(nextLine[13]);
                user.setCreatedAt(LocalDateTime.parse(nextLine[14]));
                userRepository.save(user);
            }
        }
    }
}
