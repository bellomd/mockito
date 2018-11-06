package com.etcbase.mockito.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.etcbase.mockito.dao.EtcBaseDao;

/**
 * This class is an example of using Mockito's annotations
 * to mock and inject dependencies.
 */
public class MockitoWithAnnotationTest {

	@Mock // create a new mock implementation
	private EtcBaseDao etcBaseDao;

	@InjectMocks // inject the created mock instances in this class.
	private EtcBaseService etcBaseService;

	/**
	 * The setUp method is called before running any method in this class
	 * to create mock instances and use them as dependencies for EtcBaseService class.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Test the creation of new EtcBase branch.
	 */
	@Test
	public void successCreateAndInjectMocksUsingAnnotation() {
		Assert.assertNotNull(etcBaseDao);
		Assert.assertNotNull(etcBaseService);
	}

}
