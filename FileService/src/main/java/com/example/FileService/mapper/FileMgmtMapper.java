package com.example.FileService.mapper;

import com.example.FileService.dto.FileInfo;
import com.example.FileService.entity.FileMgmt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FileMgmtMapper {
    @Mapping(target = "id",source = "name")
    FileMgmt toFileMgmt(FileInfo fileInfo);
}
