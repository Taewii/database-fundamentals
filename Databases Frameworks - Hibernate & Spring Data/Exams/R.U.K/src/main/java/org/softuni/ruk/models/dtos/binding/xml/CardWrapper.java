package org.softuni.ruk.models.dtos.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardWrapper implements Serializable {

    @XmlElement(name = "card")
    private CardXmlImportDTO[] cards;

    public CardWrapper() {
    }

    public CardXmlImportDTO[] getCards() {
        return this.cards;
    }

    public void setCards(CardXmlImportDTO[] cards) {
        this.cards = cards;
    }
}
