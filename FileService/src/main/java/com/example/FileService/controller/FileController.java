package com.example.FileService.controller;

import com.example.FileService.dto.ApiResponse;
import com.example.FileService.dto.response.FileResponse;
import com.example.FileService.service.FileService;
import com.sun.net.httpserver.HttpContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class FileController {
    FileService fileService;
    @PostMapping("/media/upload")
    public ApiResponse<FileResponse> uploadMedia(@RequestParam("file")MultipartFile file) throws IOException {
        return ApiResponse.<FileResponse>builder()
                .result(fileService.uploadFile(file))
                .build();
    }
    @GetMapping("/media/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName) throws IOException {
        var fileData = fileService.dowload(fileName);
        return ResponseEntity.<Resource>ok()
                .header(HttpHeaders.CONTENT_TYPE,fileData.contentType())
                .body(fileData.resource());
    }
}
