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
package org.polarsys.capella.command.recorder.core.output;

import java.io.Writer;

import org.polarsys.capella.command.recorder.core.recorder.IRecorder;

/**
 * Default implementation to standard output
 *
 */
final public class DummyOutputManager implements IOutputManager {

  final private Writer _writer; 
  
  /**
   * Constructor
   */
  public DummyOutputManager() {
    _writer = OutputHelper.logForStream(System.out);
  }
  
  /**
   * {@inheritDoc}
   */
  public Writer getWriter(IRecorder recorder) {
    return _writer;
  }

  /**
   * {@inheritDoc}
   */
  public void registerRecorder(IRecorder recorder, boolean isNewRecordFileShouldBeCreated) {
   // Do nothing
   return;
  }

  /**
   * {@inheritDoc}
   */
  public void removeRecorder(IRecorder recorder) {
    // Do nothing
    return;
  }

  /**
   * {@inheritDoc}
   */
  public void init() {
    // Do nothing
    return;
  }
  
  /**
   * {@inheritDoc}
   */
  public void dispose() {
    // Do nothing
    return;
  }

}
