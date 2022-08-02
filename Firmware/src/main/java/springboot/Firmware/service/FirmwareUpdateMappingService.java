package springboot.Firmware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import springboot.Firmware.exception.FirmwareNotFoundException;
import springboot.Firmware.model.FirmwareUpdateMapping;
import springboot.Firmware.repository.FirmwareUpdateMappingRepository;

import java.util.List;

@Service
public class FirmwareUpdateMappingService {
    @Autowired
    private FirmwareUpdateMappingRepository firmwareRepository;




    public List<FirmwareUpdateMapping> firmwareConfigBycustIdAndmNumber(int customerId, String modelNumber) {
        return firmwareRepository.findByCustomerIdAndModelNumber(customerId, modelNumber);

    }

   /* public String updateFirmwareConfigData(FirmwareUpdateMapping firmwareUpdateMapping) throws FirmwareNotFoundException {
        firmwareUpdateMapping firmwareUpdateMappingobj2 =   firmwareRepository.findById(firmwareUpdateMapping.getEntryId()).orElse(null);

        FirmwareUpdateMapping firmwareUpdateMapping1 = firmwareRepository.findById(id).orElseThrow(() -> new FirmwareNotFoundException(id));
        firmwareUpdateMapping1.setFirmwareVersion(firmwareRepository.getFirmwareVersion());
        firmwareUpdateMapping1.setUpdateToNextId(firmwareRepository.getUpdateToNextId());
        firmwareUpdateMapping1.setFileMd5Hash(firmwareUpdateMapping.getFileMd5Hash());
        firmwareUpdateMapping1.setDownloadUrl(firmwareUpdateMapping.getDownloadUrl());
        firmwareUpdateMapping1.setUpdateStatus(firmwareUpdateMapping.getUpdateStatus());
        firmwareUpdateMapping1.setReleaseDate(firmwareUpdateMapping.getReleaseDate());
        firmwareUpdateMapping1.setUpdateKey(firmwareUpdateMapping.getUpdateKey());
        firmwareUpdateMapping1.setMinAndroidAppVersion(firmwareUpdateMapping.getMinAndroidAppVersion());
        firmwareUpdateMapping1.setMinIosAppVersion(firmwareUpdateMapping.getMinIosAppVersion());

        FirmwareUpdateMapping firmwareUpdateMapping2 = firmwareRepository.save(firmwareUpdateMapping1);
        return "updated successfully";
    }*/

    public String saveFirmwareConfigData(@RequestBody FirmwareUpdateMapping firmware) {
        FirmwareUpdateMapping firmware1 = firmwareRepository.save(firmware);
        return "saved successfully";
    }


  /*  public List<FirmwareUpdateMapping> firmwaresConfig() {
        return firmwareRepository.findAll();
    }

    public FirmwareUpdateMapping fimwareConfigByEntryId(@RequestParam int id) throws FirmwareNotFoundException {
        return firmwareRepository.findById(id).orElseThrow(()-> new FirmwareNotFoundException(id));
    }
    public List<FirmwareUpdateMapping> firmwareConfigBymNumber(String modelNumber){
        return firmwareRepository.findBymodelNumber(modelNumber);
    }

    public List<FirmwareUpdateMapping> fimwareConfigBycustId(int customerId) {
        return firmwareRepository.findBycustomerId(customerId);
    }*/
}