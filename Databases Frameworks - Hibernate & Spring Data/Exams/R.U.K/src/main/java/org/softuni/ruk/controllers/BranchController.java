package org.softuni.ruk.controllers;

import org.softuni.ruk.models.dtos.BranchJsonImportDTO;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.branch.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class BranchController {

    private final BranchService branchService;
    private final Parser jsonParser;

    @Autowired
    public BranchController(BranchService branchService, Parser jsonParser) {
        this.branchService = branchService;
        this.jsonParser = jsonParser;
    }

    public String importBranchesFromJson(String content) {
        try {
            BranchJsonImportDTO[] branches = this.jsonParser.read(BranchJsonImportDTO[].class, content);

            StringBuilder sb = new StringBuilder();
            for (BranchJsonImportDTO branch : branches) {
                sb.append(this.branchService.create(branch)).append(System.lineSeparator());
            }

            return sb.toString().trim();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
