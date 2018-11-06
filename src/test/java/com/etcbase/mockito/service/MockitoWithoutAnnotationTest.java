package com.etcbase.mockito.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.etcbase.mockito.dao.EtcBaseDao;

public class MockitoWithoutAnnotationTest {

	private EtcBaseDao etcBaseDao;

	private EtcBaseService etcBaseService;

	/**
	 * The setUp method is called before running any method in this class
	 * to create mock instances and use them as dependencies for EtcBaseService class.
	 */
	@Before
	public void setUp() {
		etcBaseDao = Mockito.mock(EtcBaseDao.class);
		etcBaseService = new EtcBaseService(etcBaseDao);
	}
	
	/**
	 * Test the creation of new EtcBase branch.
	 */
	@Test
	public void successCreateAndInjectMocksUsingStaticMethod() {
		Assert.assertNotNull(etcBaseDao);
		Assert.assertNotNull(etcBaseService);
	}

}
