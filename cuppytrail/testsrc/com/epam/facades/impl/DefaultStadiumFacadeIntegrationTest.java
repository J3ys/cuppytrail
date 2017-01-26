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
package com.epam.facades.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.epam.data.StadiumData;
import com.epam.facades.StadiumFacade;
import com.epam.model.StadiumModel;


public class DefaultStadiumFacadeIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	private StadiumFacade stadiumFacade;

	@Resource
	private ModelService modelService;

	private StadiumModel stadiumModel;
	private final String STADIUM_NAME = "wembley";
	private final Integer STADIUM_CAPACITY = Integer.valueOf(90000);
	public static final String IMAGE_URL = "testUrl";
	public static final String CONVERSION_MEDIA_FORMAT = "conversionMediaFormatTest";

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
		stadiumFacade.getStadium(STADIUM_NAME, "UninportantFormat");
	}

	@Test
	public void testStadiumFacade()
	{
		List<StadiumData> stadiumListData = stadiumFacade.getStadiums(CONVERSION_MEDIA_FORMAT);
		assertNotNull(stadiumListData);
		final int size = stadiumListData.size();
		modelService.save(stadiumModel);

		stadiumListData = stadiumFacade.getStadiums(CONVERSION_MEDIA_FORMAT);
		assertNotNull(stadiumListData);
		assertEquals(size + 1, stadiumListData.size());
		assertEquals(stadiumListData.get(size).getName(), STADIUM_NAME);
		assertEquals(stadiumListData.get(size).getCapacity(), STADIUM_CAPACITY.toString());

		final StadiumData persistedStadiumData = stadiumFacade.getStadium(STADIUM_NAME, "UninportantFormat");
		assertNotNull(persistedStadiumData);
		assertEquals(persistedStadiumData.getName(), STADIUM_NAME);
		assertEquals(persistedStadiumData.getCapacity(), STADIUM_CAPACITY.toString());
	}
}
