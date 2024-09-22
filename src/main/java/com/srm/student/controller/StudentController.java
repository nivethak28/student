package com.srm.student.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srm.student.request.StudentRequestData;

@RestController
@RequestMapping("/bfhl")
public class StudentController {

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStudent(
            @RequestBody StudentRequestData requestData) {
        // HashMap to store and return response
        Map<String, Object> response = new HashMap<>();

        // Validate and process the Base64 input file
        boolean fileValid = validateBase64File(requestData.getFileB64());
        // Example MIME type
        String mimeType = fileValid ? "image/png" : null; 
        // Example file size
        long fileSizeKB = fileValid ? 400 : 0; 

        // Create user_id in the specified format
        String userId = createUserId("Nivetha K", "2003-01-28"); 

        // Separate List to hold numbers and alphabets 
        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        
        for (String item : requestData.getData()) {
            if (isNumeric(item)) {
                numbers.add(item);
            } else if (isAlphabetic(item)) {
                alphabets.add(item);
            }
        }

        // Find the highest lowercase alphabet
        String highestLowercase = findHighestLowercase(alphabets);

        // Prepare response values
        response.put("is_success", true);
        response.put("user_id", userId);
        response.put("email", "nivetha@28.com"); 
        response.put("roll_number", "SRM135"); 
        response.put("numbers", numbers);
        response.put("alphabets", alphabets);
        response.put("highest_lowercase_alphabet", highestLowercase != null ? Arrays.asList(highestLowercase) : Collections.emptyList());
        response.put("file_valid", fileValid);
        response.put("file_mime_type", mimeType);
        response.put("file_size_kb", fileSizeKB);

        return ResponseEntity.ok(response);
    }
   
    @GetMapping
    public ResponseEntity<Map<String, Object>> getStudent() {
        Map<String, Object> response = new HashMap<>();
        response.put("operation_code",1);
        return ResponseEntity.ok(response);
    }

    private boolean validateBase64File(String fileB64) {
        // Implement base64 validation logic
        return fileB64 != null && !fileB64.isEmpty();
    }

    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    private boolean isAlphabetic(String str) {
        return str != null && str.length() == 1 && Character.isAlphabetic(str.charAt(0));
    }

    private String findHighestLowercase(List<String> alphabets) {
        return alphabets.stream()
                .filter(s -> Character.isLowerCase(s.charAt(0)))
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

    private String createUserId(String fullName, String dob) {
         // Assuming dob is in yyyy-MM-dd format
        String formattedDob = dob.replaceAll("-", "");
        return String.format("%s_%s", fullName.replaceAll("\\s", "_").toLowerCase(), formattedDob);
    }

    
}