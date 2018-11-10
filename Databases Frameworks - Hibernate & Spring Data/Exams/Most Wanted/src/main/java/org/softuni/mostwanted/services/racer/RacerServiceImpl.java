package org.softuni.mostwanted.services.racer;

import org.softuni.mostwanted.repositories.RacerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RacerServiceImpl implements RacerService {

	private final RacerRepository racerRepository;

	@Autowired
	public RacerServiceImpl(RacerRepository racerRepository) {
		this.racerRepository = racerRepository;
	}

}