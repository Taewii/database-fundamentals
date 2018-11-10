package org.softuni.mostwanted.services.district;

import org.softuni.mostwanted.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

	private final DistrictRepository districtRepository;

	@Autowired
	public DistrictServiceImpl(DistrictRepository districtRepository) { 
		this.districtRepository = districtRepository;
	}

}