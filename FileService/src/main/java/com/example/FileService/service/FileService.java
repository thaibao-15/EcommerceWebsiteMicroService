package com.example.FileService.service;

import com.example.FileService.dto.FileInfo;
import com.example.FileService.dto.response.FileData;
import com.example.FileService.dto.response.FileResponse;
import com.example.FileService.entity.FileMgmt;
import com.example.FileService.exception.AppException;
import com.example.FileService.exception.ErrorCode;
import com.example.FileService.mapper.FileMgmtMapper;
import com.example.FileService.repository.FileMgmtRepository;
import com.example.FileService.repository.FileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class FileService {
    FileRepository fileRepository;
    FileMgmtRepository fileMgmtRepository;
    FileMgmtMapper fileMgmtMapper;
    public FileResponse uploadFile(MultipartFile file) throws IOException {
        FileInfo fileInfo = fileRepository.store(file);

        FileMgmt fileMgmt = fileMgmtMapper.toFileMgmt(fileInfo);
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        fileMgmt.setOwnerId(userId);

        fileMgmtRepository.save(fileMgmt);

        return FileResponse.builder()
                .originalFileName(file.getOriginalFilename())
                .url(fileInfo.getUrl())
                .build();
    }

    public FileData dowload(String fileName) throws IOException {
        FileMgmt fileMgmt = fileMgmtRepository.findById(fileName).orElseThrow(() -> new AppException(ErrorCode.FILE_NOT_FOUND));

        var resourse = fileRepository.read(fileMgmt);

        return new FileData(fileMgmt.getContentType(),resourse);
    }
    
}
