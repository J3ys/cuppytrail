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
package com.epam.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * The annotated element must not be empty or <code>null</code>. Supported type is {@link String}. <code>null</code>
 * elements are considered <b>invalid</b>.
 */
@Target(
{ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = NotEmptyCuppyValidator.class)
@Documented
public @interface NotEmptyCuppy
{
	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}