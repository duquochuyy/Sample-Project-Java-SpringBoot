package com.example.demo.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    // lưu trữ file mới
    public String storageFile(MultipartFile file);
    // lấy toàn bộ file
    public Stream<Path> loadAll();
    // lấy dữ liệu ảnh theo tên file
    public byte[] readFileContent(String fileName);
    // xóa toàn bộ file
    public void deleteAllFiles();
}