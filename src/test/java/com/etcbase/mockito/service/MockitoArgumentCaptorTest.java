package com.etcbase.mockito.service;

import static org.hamcrest.CoreMatchers.notNullValue;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.etcbase.mockito.EtcBase;
import com.etcbase.mockito.dao.EtcBaseDao;

public class MockitoArgumentCaptorTest {

	@Mock
	private EtcBaseDao etcBaseDao;

	@InjectMocks
	private EtcBaseService etcBaseService;
	
	@Captor
	private ArgumentCaptor<EtcBase> etcBaseArgumentCaptor;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * In this test, because every EtcBase instance has to have an Id, before 
	 * persisting the object, here we want to capture the argument that is passed
	 * to generate id when it's called while saving an object.
	 */
	@Test
	public void successCheckIdentificationBeforeSaving() {

		// Prepare the needed objects
		final EtcBase etcBase = new EtcBase();
		
		Mockito.when(etcBaseDao.save(etcBase)).thenReturn(etcBase);
		
		final EtcBase savedEtcBase = etcBaseService.save(etcBase);
		
		Mockito.verify(etcBaseDao).checkId(etcBaseArgumentCaptor.capture());
		
		Assert.assertNotNull(savedEtcBase);
		Assert.assertNotNull(savedEtcBase.getId());
		
		// Asserting with the captured arguments.
		Assert.assertThat(etcBaseArgumentCaptor.getValue().getId(), CoreMatchers.is(notNullValue()));
	}

}
