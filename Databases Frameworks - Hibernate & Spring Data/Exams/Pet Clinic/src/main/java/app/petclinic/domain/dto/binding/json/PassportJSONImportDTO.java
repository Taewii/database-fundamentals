package app.petclinic.domain.dto.binding.json;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

public class PassportJSONImportDTO implements Serializable {

    @Pattern(regexp = "^.{7}\\d{3}$")
    private String serialNumber;

    @Length(min = 3, max = 30)
    private String ownerName;

    @Pattern(regexp = "^(?>0|\\+359)\\d{9}$")
    private String ownerPhoneNumber;

    private Date registrationDate;

    public PassportJSONImportDTO() {
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhoneNumber() {
        return this.ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
