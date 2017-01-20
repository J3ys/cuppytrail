/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2017 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.epam.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.epam.model.StadiumModel;


@IntegrationTest
public class DefaultStadiumServiceIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private com.epam.service.StadiumService stadiumService;
	@Resource
	private ModelService modelService;

	private StadiumModel stadiumModel;
	private final static String STADIUM_NAME = "wembley";
	private final static Integer STADIUM_CAPACITY = Integer.valueOf(12345);

	@Before
	public void setUp()
	{
		// This instance of a StadiumModel will be used by the tests
		stadiumModel = new StadiumModel();
		stadiumModel.setCode(STADIUM_NAME);
		stadiumModel.setCapacity(STADIUM_CAPACITY);
	}

	@Test(expected = UnknownIdentifierException.class)
	public void testFailBehavior()
	{
		stadiumService.getStadiumForCode(STADIUM_NAME);
	}

	/**
	 * This test tests and demonstrates that the Service's getAllStadium method calls the DAOs' getAllStadium method and
	 * returns the data it receives from it.
	 */
	@Test
	public void testStadiumService()
	{
		List<StadiumModel> stadiumModels = stadiumService.getStadiums();
		final int size = stadiumModels.size();

		modelService.save(stadiumModel);

		stadiumModels = stadiumService.getStadiums();
		assertEquals(size + 1, stadiumModels.size());
		assertEquals("Unexpected stadium found", stadiumModel, stadiumModels.get(stadiumModels.size() - 1));

		final StadiumModel persistedStadiumModel = stadiumService.getStadiumForCode(STADIUM_NAME);
		assertNotNull("No stadium found", persistedStadiumModel);
		assertEquals("Different stadium found", stadiumModel, persistedStadiumModel);
	}

}
