package com.example.BanHang.repository.httpclient;

import com.example.BanHang.dto.request.ApiResponse;
import com.example.BanHang.dto.response.FileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-client", url = "${app.file.url}")
public interface FileClient {
    @PostMapping(value = "media/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<FileResponse> uploadAvatar(@RequestPart("file") MultipartFile file);
}
