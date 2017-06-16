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

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Default name resolver
 *
 */
public class DefaultNameResolver implements INameResolver {

  /**
   * {@inheritDoc}
   */
  public String getReadableName(EObject eobject) {

    String result = null;
    
    if ( null != eobject) {
      result = eobject.eClass().getName();
    }
    
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public String getID(EObject eobject) {
    return ICommonConstants.EMPTY_STRING;
  }

}
