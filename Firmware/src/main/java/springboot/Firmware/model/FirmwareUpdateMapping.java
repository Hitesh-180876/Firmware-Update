package springboot.Firmware.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "customerId", "firmwareVersion","modelNumber" }) })
public class FirmwareUpdateMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private int entryId;

    @Column(nullable = false)
    private int customerId;

    @Column(nullable = false)
    private String modelNumber;

    @Column(nullable = false)
    private String firmwareVersion;

    @Column
    private int updateToNextId;

    @Column(nullable = false)
    private String updateStatus;

    @Column(nullable = false)
    private String fileMd5Hash;

    @Column(nullable = false)
    private  String downloadUrl;

    @Column
    private String updateKey;

    @Column(nullable = false)
    private String releaseDate;

    @Column(nullable = false)
    private String minAndroidAppVersion;

    @Column(nullable = false)
    private String minIosAppVersion;



}
