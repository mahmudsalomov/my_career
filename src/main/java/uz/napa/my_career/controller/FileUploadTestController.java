package uz.napa.my_career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.napa.my_career.entity.FileStorage;
import uz.napa.my_career.repository.FileStorageRepository;
import uz.napa.my_career.service.FileStorageService;

import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/file")
public class FileUploadTestController {
    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileStorageRepository fileStorageRepository;






    @PostMapping("/upload")
    public HttpEntity<?> upload(@RequestBody MultipartFile[] files){
        List<FileStorage> fileStorages = fileStorageService.save(files);
        return ResponseEntity.ok(fileStorages);
    }

    @GetMapping("/get_file/{id}")
    public HttpEntity<?> getImage(@PathVariable Long id) throws MalformedURLException {
        Optional<FileStorage> fileStorage = fileStorageRepository.findById(id);
        if (fileStorage.isPresent()){
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"inline: filename\""+ URLEncoder.encode(fileStorage.get().getName()))
                    .contentType(MediaType.parseMediaType(fileStorage.get().getContentType()))
                    .contentLength(fileStorage.get().getFileSize())
                    .body(new FileUrlResource(fileStorage.get().getUploadPath()));
        }
        return ResponseEntity.ok("Xato!");
    }
}
