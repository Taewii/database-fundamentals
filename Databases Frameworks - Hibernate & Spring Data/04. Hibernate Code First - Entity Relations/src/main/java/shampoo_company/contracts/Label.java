package shampoo_company.contracts;

import shampoo_company.entities.shampoos.BasicShampoo;

public interface Label {

    Long getId();

    void setId(Long id);

    String getTitle();

    void setTitle(String title);

    String getSubtitle();

    void setSubtitle(String subtitle);

    BasicShampoo getShampoo();

    void setShampoo(BasicShampoo shampoo);
}
