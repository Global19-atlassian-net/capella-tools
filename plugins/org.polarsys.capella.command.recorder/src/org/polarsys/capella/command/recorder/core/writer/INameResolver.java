/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.command.recorder.core.writer;

import org.eclipse.emf.ecore.EObject;

/**
 * Name Resolver for serialization purpose.
 *
 */
public interface INameResolver {

  /**
   * Convenient method in order to get readable name for EObject
   * @param eobject
   * @return see implementation
   */
  public String getReadableName(EObject eobject);
  
  /**
   * Return the Id of a given {@link EObject}
   * @param eobject
   * @return see implementation
   */
  public String getID(EObject eobject);
  
  
  
}
