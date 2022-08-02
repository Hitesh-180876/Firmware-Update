package springboot.Firmware.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    String files(MultipartFile file);
}
