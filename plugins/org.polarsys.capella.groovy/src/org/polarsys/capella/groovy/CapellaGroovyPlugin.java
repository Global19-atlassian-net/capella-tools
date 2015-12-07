/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Soyatec - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.groovy;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class CapellaGroovyPlugin implements BundleActivator {

  public static final String PLUGIN_ID = "org.polarsys.capella.groovy";

  private static BundleContext context;

  static BundleContext getContext() {
    return context;
  }

  /**
   * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
   */
  public void start(BundleContext bundleContext) throws Exception {
    CapellaGroovyPlugin.context = bundleContext;
  }

  /**
   * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
   */
  public void stop(BundleContext bundleContext) throws Exception {
    CapellaGroovyPlugin.context = null;
  }
}
