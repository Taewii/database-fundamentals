package app.petclinic.services.passport;

import app.petclinic.domain.models.Passport;

public interface PassportService {

    Passport findOneBySerialNumber(String serialNumber);
}