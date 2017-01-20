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
package com.epam.cuppytrailcockpit.setup;

import static com.epam.cuppytrailcockpit.constants.CuppytrailcockpitConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.epam.cuppytrailcockpit.constants.CuppytrailcockpitConstants;
import com.epam.cuppytrailcockpit.service.CuppytrailcockpitService;


@SystemSetup(extension = CuppytrailcockpitConstants.EXTENSIONNAME)
public class CuppytrailcockpitSystemSetup
{
	private final CuppytrailcockpitService cuppytrailcockpitService;

	public CuppytrailcockpitSystemSetup(final CuppytrailcockpitService cuppytrailcockpitService)
	{
		this.cuppytrailcockpitService = cuppytrailcockpitService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		cuppytrailcockpitService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return CuppytrailcockpitSystemSetup.class.getResourceAsStream("/cuppytrailcockpit/sap-hybris-platform.png");
	}
}
