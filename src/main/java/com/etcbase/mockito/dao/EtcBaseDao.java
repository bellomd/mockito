package com.etcbase.mockito.dao;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.etcbase.mockito.EtcBase;

@Service
public class EtcBaseDao {

	private static ConcurrentHashMap<Long, EtcBase> etcBaseBranches;

	public EtcBaseDao() {
		if (etcBaseBranches == null) {
			EtcBaseDao.etcBaseBranches = new ConcurrentHashMap<>();
		}
	}

	public ConcurrentHashMap<Long, EtcBase> getEtcBaseBranches() {
		return etcBaseBranches;
	}

	public EtcBase save(final EtcBase etcBase) {
		etcBaseBranches.put(etcBase.getId(), etcBase);
		return etcBase;
	}

	public Optional<EtcBase> get(final Long id) {
		return Optional.of(etcBaseBranches.get(id));
	}

	public EtcBase update(final EtcBase etcBase) {

		if (!get(etcBase.getId()).isPresent()) {
			throw new RuntimeException("Entity not found with the given id: " + etcBase.getId());
		}

		return etcBaseBranches.put(etcBase.getId(), etcBase);
	}

	public void delete(final Long id) {

		if (!get(id).isPresent()) {
			throw new RuntimeException("Entity not found with the given id: " + id);
		}

		etcBaseBranches.remove(id);
	}
	
	public void delete(final EtcBase etcBase) {

		if (etcBase == null) {
			throw new RuntimeException("Entity cannot be null");
		}

		etcBaseBranches.remove(etcBase.getId());
	}
	
	public void checkId(final EtcBase etcBase) {
		
		if (etcBase.getId() == null) {
			etcBase.setId(new Random().nextLong());
		}
	}
}
