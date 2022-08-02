package springboot.Firmware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.Firmware.model.FirmwareUpdateMapping;

import java.util.List;

@Repository
public interface FirmwareUpdateMappingRepository extends JpaRepository<FirmwareUpdateMapping, Integer> {
  List<FirmwareUpdateMapping> findByCustomerIdAndModelNumber(int customerId, String modelNumber);

  FirmwareUpdateMapping findByEntryId(int entryId);

  /*List<FirmwareUpdateMapping> findBycustomerId(int customerId);

  List<FirmwareUpdateMapping> findBymodelNumber(String modelNumber);*/
}
