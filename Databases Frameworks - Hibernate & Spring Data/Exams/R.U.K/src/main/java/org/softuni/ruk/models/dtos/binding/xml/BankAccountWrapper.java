package org.softuni.ruk.models.dtos.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "bank-accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountWrapper implements Serializable {

    @XmlElement(name = "bank-account")
    private BankAccountXmlImportDTO[] bankAccounts;

    public BankAccountWrapper() {
    }

    public BankAccountXmlImportDTO[] getBankAccounts() {
        return this.bankAccounts;
    }

    public void setBankAccounts(BankAccountXmlImportDTO[] bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
