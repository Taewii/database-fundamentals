package org.softuni.ruk.models.dtos.view.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "bank-account")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountExportDTO implements Serializable {

    @XmlAttribute(name = "account-number")
    private String accountNumber;

    @XmlElementWrapper(name = "cards")
    @XmlElement(name = "card")
    private List<CardExportDTO> cards;

    public BankAccountExportDTO() {
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<CardExportDTO> getCards() {
        return this.cards;
    }

    public void setCards(List<CardExportDTO> cards) {
        this.cards = cards;
    }
}
