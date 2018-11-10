package org.softuni.mostwanted.services.town;

import org.softuni.mostwanted.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TownServiceImpl implements TownService {

	private final TownRepository townRepository;

	@Autowired
	public TownServiceImpl(TownRepository townRepository) { 
		this.townRepository = townRepository;
	}

}