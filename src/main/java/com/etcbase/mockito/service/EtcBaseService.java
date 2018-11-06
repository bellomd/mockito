package com.etcbase.mockito.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.etcbase.mockito.EtcBase;
import com.etcbase.mockito.dao.EtcBaseDao;

public class EtcBaseService {
	
	private final EtcBaseDao etcBaseDao;
	
	public EtcBaseService(final EtcBaseDao etcBaseDao) {
		this.etcBaseDao = etcBaseDao;
	}
	
	
	public List<EtcBase> getBranches() {
		return etcBaseDao.getEtcBaseBranches()
				.values().stream().collect(Collectors.toList());
	}

	public EtcBase save(final EtcBase etcBase) {
		etcBaseDao.checkId(etcBase);
		return etcBaseDao.save(etcBase);
	}

	public Optional<EtcBase> get(final Long id) {
		return etcBaseDao.get(id);
	}

	public EtcBase update(final EtcBase etcBase) {
		return etcBaseDao.update(etcBase);
	}

	public void delete(final Long id) {
		etcBaseDao.delete(id);
	}
	
	public void delete(final EtcBase etcBase) {
		etcBaseDao.delete(etcBase);
	}
}
