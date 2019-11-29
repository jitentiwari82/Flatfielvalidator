package com.zs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@RestController
public class FileUploadController {
    private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    private static String UPLOADED_FILE_LOCATION = "C://tempfile//";


    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity("No fiel selected", HttpStatus.OK);
        }
        logger.debug("file upload start..");
        try {
            saveFile(file);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("File uploaded successfully - " + file.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }

    private void saveFile(MultipartFile file) throws IOException {
        String dateTime = LocalDateTime.now().toString();
        String folderPath = UPLOADED_FILE_LOCATION + dateTime;
        File dir = new File(folderPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FILE_LOCATION + file.getOriginalFilename());
        Files.write(path, bytes);
    }


}
