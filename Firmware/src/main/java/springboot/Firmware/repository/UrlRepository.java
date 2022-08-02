package springboot.Firmware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.Firmware.model.File;

public interface UrlRepository extends JpaRepository<File, Integer> {

}
