package com.example.FileService.controller;

import com.example.FileService.dto.ApiResponse;
import com.example.FileService.dto.response.FileResponse;
import com.example.FileService.service.FileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
