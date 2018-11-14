package org.softuni.ruk.services.branch;

import org.softuni.ruk.Constants;
import org.softuni.ruk.models.dtos.BranchJsonImportDTO;
import org.softuni.ruk.models.entities.Branch;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final ValidationUtil validation;
    private final ModelParser modelParser;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository,
                             ValidationUtil validation,
                             ModelParser modelParser) {
        this.branchRepository = branchRepository;
        this.validation = validation;
        this.modelParser = modelParser;
    }

    @Override
    public String create(BranchJsonImportDTO branchDto) {
        if (!validation.isValid(branchDto)) {
            return Constants.INVALID_DATA_ERROR;
        }

        Branch branch = this.modelParser.convert(branchDto, Branch.class);
        this.branchRepository.saveAndFlush(branch);

        return String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Branch", branch.getName());
    }

    @Override
    public Branch getBranchByName(String name) {
        return this.branchRepository.getBranchByName(name);
    }
}