package org.softuni.ruk.services.branch;

import org.softuni.ruk.models.dtos.BranchJsonImportDTO;
import org.softuni.ruk.models.entities.Branch;

public interface BranchService {

    String create(BranchJsonImportDTO branchDto);

    Branch getBranchByName(String name);
}