package springboot.Firmware.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.Firmware.exception.FirmwareNotFoundException;
import springboot.Firmware.model.File;
import springboot.Firmware.model.FirmwareUpdateMapping;
import springboot.Firmware.repository.FirmwareUpdateMappingRepository;
import springboot.Firmware.repository.UrlRepository;
import springboot.Firmware.service.AwsS3Service;
import springboot.Firmware.service.FirmwareUpdateMappingService;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collections;
import java.util.List;


@CrossOrigin
@RestController
public class firmwareUpdateMappingController {

    @Autowired
    private FirmwareUpdateMappingService service;

    @Autowired
    private FirmwareUpdateMappingRepository firmwareUpdateMappingRepository;
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private AwsS3Service awsS3Service;

    @PostMapping("firmware/config")
    public ResponseEntity<String> files(@RequestParam("customerId") int customerId,
                                                          @RequestParam("modelNumber") String modelNumber,
                                                          @RequestParam(value = "firmwareVersion") String firmwareVersion,
                                                          @RequestParam(value = "updateToNextId") Integer updateToNextId,
                                                          @RequestParam(value = "updateStatus") String updateStatus,
                                                          @RequestParam(value = "fileMD5Hash") String fileMD5Hash,
                                                          @RequestParam(value = "file") MultipartFile file,
                                                          @RequestParam(value = "updateKey") String updateKey,
                                                          @RequestParam(value = "releaseDate") String releaseDate,
                                                          @RequestParam(value = "minAndroidAppVersion") String minAndroidAppVersion,
                                                          @RequestParam(value = "minIosAppVersion") String minIosAppVersion


                                                            ){
        File fileObj = new File();
        String url = awsS3Service.files(file);
        HttpStatus status = HttpStatus.CREATED;
        fileObj.setFileType(fileObj.getFileType());

        FirmwareUpdateMapping firmwareUpdateMappingobj = new FirmwareUpdateMapping();
        String url1 = awsS3Service.files(file);
        HttpStatus status1 = HttpStatus.CREATED;
        firmwareUpdateMappingobj.setCustomerId(customerId);
        firmwareUpdateMappingobj.setModelNumber(modelNumber);
        firmwareUpdateMappingobj.setFirmwareVersion(firmwareVersion);
        firmwareUpdateMappingobj.setUpdateToNextId(updateToNextId);
        firmwareUpdateMappingobj.setUpdateStatus(updateStatus);
        firmwareUpdateMappingobj.setFileMd5Hash(fileMD5Hash);
        firmwareUpdateMappingobj.setUpdateKey(updateKey);
        firmwareUpdateMappingobj.setReleaseDate(releaseDate);
        firmwareUpdateMappingobj.setMinIosAppVersion(minIosAppVersion);
        firmwareUpdateMappingobj.setMinAndroidAppVersion(minAndroidAppVersion);
        firmwareUpdateMappingobj.setDownloadUrl(url1);

        firmwareUpdateMappingRepository.save(firmwareUpdateMappingobj);

        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.put("server", Collections.singletonList("My Server"));

        ResponseEntity<String> response = new ResponseEntity<>(url, header, status);

        return response;
    }


    @GetMapping("firmware/config")
    public List<FirmwareUpdateMapping> findByFirmwarecustIdAndmNumber(@RequestParam("customerId") int customerId, @RequestParam("modelNumber") String modelNumber){
        return service.firmwareConfigBycustIdAndmNumber(customerId, modelNumber);
    }

   /* @PostMapping("firmware/config")
    public String saveFirmwareConfig(@RequestBody FirmwareUpdateMapping firmware){
        return service.saveFirmwareConfigData(firmware);
    }*/


  /*  @PutMapping("firmware/config/{entryId}")
    public String FirmwareConfigData(@PathVariable("entryId") int entryId, @RequestBody FirmwareUpdateMapping firmwareUpdateMapping) throws FirmwareNotFoundException {
        return service.updateFirmwareConfigData(entryId, firmwareUpdateMapping);
    }*/

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @PutMapping("firmware/config")
    public ResponseEntity<FirmwareUpdateMapping> FirmwareConfigData( @RequestParam(value = "entryId") int entryId,
                                                                        @RequestParam("modelNumber") String modelNumber,
                                                                        @RequestParam("customerId") int customerId,
                                                                        @RequestParam(value = "firmwareVersion") String firmwareVersion,
                                                                        @RequestParam(value = "updateToNextId") int updateToNextId,
                                                                        @RequestParam(value = "fileMD5Hash") String fileMD5Hash,
                                                                        @RequestParam(value = "downloadUrl") String downloadUrl,
                                                                        @RequestParam(value = "updateStatus") String updateStatus,
                                                                        @RequestParam(value = "releaseDate") String releaseDate,
                                                                        @RequestParam(value = "updateKey") String updateKey,
                                                                        @RequestParam(value = "minAndroidAppVersion") String minAndroidAppVersion,
                                                                        @RequestParam(value = "minIosAppVersion") String minIosAppVersion
    ) {

        FirmwareUpdateMapping firmwareUpdateMappingobj1 = firmwareUpdateMappingRepository.findByEntryId(entryId);
        firmwareUpdateMappingobj1.setFirmwareVersion(firmwareVersion);
        firmwareUpdateMappingobj1.setModelNumber(modelNumber);
        firmwareUpdateMappingobj1.setCustomerId(customerId);
        firmwareUpdateMappingobj1.setUpdateToNextId(updateToNextId);
        firmwareUpdateMappingobj1.setFileMd5Hash(fileMD5Hash);
        firmwareUpdateMappingobj1.setDownloadUrl(downloadUrl);
        firmwareUpdateMappingobj1.setUpdateStatus(updateStatus);
        firmwareUpdateMappingobj1.setReleaseDate(releaseDate);
        firmwareUpdateMappingobj1.setUpdateKey(updateKey);
        firmwareUpdateMappingobj1.setMinAndroidAppVersion(minAndroidAppVersion);
        firmwareUpdateMappingobj1.setMinIosAppVersion(minIosAppVersion);

        FirmwareUpdateMapping updatedDetails = firmwareUpdateMappingRepository.save(firmwareUpdateMappingobj1);
        return ResponseEntity.ok(updatedDetails);

    }


     /*   @GetMapping("/firmware/config")
    public List<FirmwareUpdateMapping> findAllFirmwareConfig(){
        return service.firmwaresConfig();
    }


      @GetMapping("/firmware/config/eId")
    public FirmwareUpdateMapping findFirmwaresByEntryId(@RequestParam("entryId") int entryId) throws FirmwareNotFoundException {
        return service.fimwareConfigByEntryId(entryId);
    }
    @GetMapping("/firmware/config/cId")
    public List<FirmwareUpdateMapping> findFirmwaresBycustId(@RequestParam("customerId") int customerId){
        return service.fimwareConfigBycustId(customerId);
    }
    @GetMapping("firmware/config/mNumber")
    public List<FirmwareUpdateMapping> findFirmwaresBymNumber(@RequestParam("modelNumber") String modelNumber){
        return service.firmwareConfigBymNumber(modelNumber);
    }
    */


}
