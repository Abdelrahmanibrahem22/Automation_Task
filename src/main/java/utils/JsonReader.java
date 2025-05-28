package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
public class JsonReader {
        public static String getTestData(String key) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            UserData userData = mapper.readValue(new File("src/main/resources/UserData.json"), UserData.class);

            if (key.equals("validUser.username")) {
                return userData.getValidUser().getUsername();
            } else if (key.equals("validUser.password")) {
                return userData.getValidUser().getPassword();
            }
            throw new IllegalArgumentException("Invalid key: " + key);
        }
    }

