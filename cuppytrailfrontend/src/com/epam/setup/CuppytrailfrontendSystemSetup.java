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

import static com.epam.constants.CuppytrailfrontendConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.epam.constants.CuppytrailfrontendConstants;
import com.epam.service.CuppytrailfrontendService;


@SystemSetup(extension = CuppytrailfrontendConstants.EXTENSIONNAME)
public class CuppytrailfrontendSystemSetup
{
	private final CuppytrailfrontendService cuppytrailfrontendService;

	public CuppytrailfrontendSystemSetup(final CuppytrailfrontendService cuppytrailfrontendService)
	{
		this.cuppytrailfrontendService = cuppytrailfrontendService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		cuppytrailfrontendService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return CuppytrailfrontendSystemSetup.class.getResourceAsStream("/cuppytrailfrontend/sap-hybris-platform.png");
	}
}
