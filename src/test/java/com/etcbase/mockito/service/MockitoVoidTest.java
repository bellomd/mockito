package com.etcbase.mockito.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.etcbase.mockito.dao.EtcBaseDao;

public class MockitoVoidTest {

	@Mock
	private EtcBaseDao etcBaseDao;

	@InjectMocks
	private EtcBaseService etcBaseService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * 	Mocking a void method using doAnswer. It's not used that much but this is an illustration 
	 * 	of the general usage.
	 */
	@Test
	public void successDeleteExistingEtcBaseBranch() {
		
		Mockito.doAnswer(new Answer<Void>() {
			
			@Override
			public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
				
				Object[] arguments = invocationOnMock.getArguments();

				if (arguments != null && arguments.length > 0 && arguments[0] != null) {

					// Do something here.
				}

				return null;
			}
		}).when(etcBaseDao).delete(Mockito.any(Long.class));
		
		// Calling the service
		etcBaseService.delete(1L);
	}
}
