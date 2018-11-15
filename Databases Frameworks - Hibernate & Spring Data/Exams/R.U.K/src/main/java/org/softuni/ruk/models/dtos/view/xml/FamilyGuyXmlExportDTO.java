package org.softuni.ruk.models.dtos.view.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "family-guy")
@XmlAccessorType(XmlAccessType.FIELD)
public class FamilyGuyXmlExportDTO implements Serializable {

    @XmlAttribute(name = "full-name")
    private String fullName;

    @XmlAttribute(name = "age")
    private Integer age;

    @XmlElement(name = "bank-account")
    private BankAccountExportDTO bankAccount;

    public FamilyGuyXmlExportDTO() {
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BankAccountExportDTO getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccountExportDTO bankAccount) {
        this.bankAccount = bankAccount;
    }
}
