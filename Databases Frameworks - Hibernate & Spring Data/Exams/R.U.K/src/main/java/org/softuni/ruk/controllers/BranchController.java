package org.softuni.ruk.controllers;

import org.softuni.ruk.models.dtos.binding.json.BranchJsonImportDTO;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.branch.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
        BranchJsonImportDTO[] branches = this.jsonParser.read(BranchJsonImportDTO[].class, content);

        StringBuilder sb = new StringBuilder();
        for (BranchJsonImportDTO branch : branches) {
            sb.append(this.branchService.create(branch)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
