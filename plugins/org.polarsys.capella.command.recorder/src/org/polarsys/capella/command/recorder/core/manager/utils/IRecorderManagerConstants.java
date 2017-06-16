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
package org.polarsys.capella.command.recorder.core.manager.utils;

import org.eclipse.core.commands.operations.OperationHistoryEvent;

/**
 * Useful constant for The recorder Manager
 *
 */
public interface IRecorderManagerConstants {

  /** NONE CONSTANT... */
  final public static int NONE = -1;
  
  /*
   *  Bounds (peer/odd) for operations of interest
   */
  final public static int DOUBLETS[] = {
       OperationHistoryEvent.ABOUT_TO_EXECUTE, // "Execute"                           
       OperationHistoryEvent.DONE,
       OperationHistoryEvent.ABOUT_TO_UNDO, // "UNDO"
       OperationHistoryEvent.UNDONE,
       OperationHistoryEvent.ABOUT_TO_REDO, // "REDO"
       OperationHistoryEvent.REDONE
  };
  
}
