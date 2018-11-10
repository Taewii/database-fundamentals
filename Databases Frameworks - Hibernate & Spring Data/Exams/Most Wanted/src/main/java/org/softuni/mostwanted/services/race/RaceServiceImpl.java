package org.softuni.mostwanted.services.race;

import org.softuni.mostwanted.repositories.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RaceServiceImpl implements RaceService {

	private final RaceRepository raceRepository;

	@Autowired
	public RaceServiceImpl(RaceRepository raceRepository) {
		this.raceRepository = raceRepository;
	}

}