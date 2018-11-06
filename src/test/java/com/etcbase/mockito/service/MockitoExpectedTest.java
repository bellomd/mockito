package com.etcbase.mockito.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.etcbase.mockito.EtcBase;
import com.etcbase.mockito.dao.EtcBaseDao;

public class MockitoExpectedTest {

	@Mock
	private EtcBaseDao etcBaseDao;

	@InjectMocks
	private EtcBaseService etcBaseService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Throwing an exception from a mocked method.
	 */
	@Test(expected = RuntimeException.class)
	public void successThrowRuntimeExceptionWhenSaveIsCalled() {

		// Prepare the objects you need.
		final EtcBase etcBase = new EtcBase();

		// when
		Mockito.when(etcBaseDao.save(Mockito.any())).thenThrow(RuntimeException.class); // then

		// Mockito.doReturn(etcBaseService).when(etcBaseService).save(etcBase);

		// 	We then call our service
		//	The above expected is watching our back, so we don't have to check for 
		//	whether the exception is thrown or not.
		etcBaseService.save(etcBase);
	}

}
