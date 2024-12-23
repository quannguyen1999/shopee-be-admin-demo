package com.shopee.ecommer.controllers.rest;

import com.amazonaws.services.macie2.model.AwsService;
import com.shopee.ecommer.constants.PathApi;
import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.services.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Tag(
        name = "API Rest S3",
        description = "CRUD, Export API S3 details"
)
@RestController
@RequestMapping(value = PathApi.S3)
@AllArgsConstructor
public class S3RestController {

    @Autowired
    private S3Service service;

    // Endpoint to list files in a bucket
    @GetMapping("/{bucketName}")
    public ResponseEntity<?> listFiles(
            @PathVariable("bucketName") String bucketName
    ) {
        val body = service.listFiles(bucketName);
        return ResponseEntity.ok(body);
    }

    // Endpoint to upload a file to a bucket
    @PostMapping("/{bucketName}/upload")
    @SneakyThrows(IOException.class)
    public ResponseEntity<?> uploadFile(
            @PathVariable("bucketName") String bucketName,
            @RequestParam("file") MultipartFile file
    ) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String contentType = file.getContentType();
        long fileSize = file.getSize();
        InputStream inputStream = file.getInputStream();

        service.uploadFile(bucketName, fileName, fileSize, contentType, inputStream);

        return ResponseEntity.ok().body("File uploaded successfully");
    }


//    @Operation(
//            summary = "Upload s3"
//    )
//    @RequestMapping(value = PathApi.UPLOAD, method = RequestMethod.POST)
//    public ResponseEntity<?> uploadFile(@RequestBody AccountRequestDto accountRequestDto) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(accountService.createAccount(accountRequestDto));
//    }
}
