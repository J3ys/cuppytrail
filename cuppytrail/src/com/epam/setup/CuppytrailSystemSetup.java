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

import static com.epam.constants.CuppytrailConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.epam.constants.CuppytrailConstants;
import com.epam.service.CuppytrailService;


@SystemSetup(extension = CuppytrailConstants.EXTENSIONNAME)
public class CuppytrailSystemSetup
{
	private final CuppytrailService cuppytrailService;

	public CuppytrailSystemSetup(final CuppytrailService cuppytrailService)
	{
		this.cuppytrailService = cuppytrailService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		cuppytrailService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return CuppytrailSystemSetup.class.getResourceAsStream("/cuppytrail/sap-hybris-platform.png");
	}
}
