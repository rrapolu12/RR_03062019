package com.ram.crud.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;


public class StorageService {

    public void init() {

    }

    public void store(MultipartFile file) {

    }

    public Stream<Path> loadAll() {
        return null;
    }

    public Path load(String filename) {
        return null;
    }

    public Resource loadAsResource(String filename) {
        return null;
    }

}
