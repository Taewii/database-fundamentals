package org.softuni.ruk.models.dtos.binding.xml;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardXmlImportDTO implements Serializable {

    @NotNull
    @XmlAttribute(name = "status")
    private String status;

    @XmlAttribute(name = "account-number")
    private String accountNumber;

    @NotNull
    @XmlElement(name = "card-number")
    private String cardNumber;

    public CardXmlImportDTO() {
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
