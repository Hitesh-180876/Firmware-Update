package springboot.Firmware.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import springboot.Firmware.repository.UrlRepository;

import java.io.IOException;
import java.util.UUID;
@Service
public class AwsS3Service implements FileService{

    @Autowired
    private UrlRepository repository;

    @Autowired
    private AmazonS3Client awsS3Client;

    @Override
    public String files(MultipartFile file) {
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID().toString()+"."+filenameExtension;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try{
            awsS3Client.putObject("abcd9999", key, file.getInputStream(), metadata);
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occured while uploading file");
        }

        String getKey = awsS3Client.getResourceUrl("abcd9999", key); //getting the resource url
        System.out.println(getKey);
        return getKey;


    }

}
