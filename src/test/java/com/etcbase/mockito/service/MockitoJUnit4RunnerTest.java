package com.etcbase.mockito.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.etcbase.mockito.dao.EtcBaseDao;

@RunWith(MockitoJUnitRunner.class)
public class MockitoJUnit4RunnerTest {

	@Mock
	private EtcBaseDao etcBaseDao;

	@InjectMocks
	private EtcBaseService etcBaseService;

	@Test
	public void successCreateNewEtcBaseBranch() {
		Assert.assertNotNull(etcBaseDao);
		Assert.assertNotNull(etcBaseService);
	}
}
