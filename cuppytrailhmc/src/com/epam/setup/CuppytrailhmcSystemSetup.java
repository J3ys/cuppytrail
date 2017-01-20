/*
 * [y] hybris Platform
 * 
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of SAP 
 * Hybris ("Confidential Information"). You shall not disclose such 
 * Confidential Information and shall use it only in accordance with the 
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.epam.setup;

import static com.epam.constants.CuppytrailhmcConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.epam.constants.CuppytrailhmcConstants;
import com.epam.service.CuppytrailhmcService;


@SystemSetup(extension = CuppytrailhmcConstants.EXTENSIONNAME)
public class CuppytrailhmcSystemSetup
{
	private final CuppytrailhmcService cuppytrailhmcService;

	public CuppytrailhmcSystemSetup(final CuppytrailhmcService cuppytrailhmcService)
	{
		this.cuppytrailhmcService = cuppytrailhmcService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		cuppytrailhmcService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return CuppytrailhmcSystemSetup.class.getResourceAsStream("/cuppytrailhmc/sap-hybris-platform.png");
	}
}
