package app.petclinic.services.passport;

import app.petclinic.domain.models.Passport;
import app.petclinic.repositories.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;

    @Autowired
    public PassportServiceImpl(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    @Override
    public Passport findOneBySerialNumber(String serialNumber) {
        return this.passportRepository.findOneBySerialNumber(serialNumber);
    }
}