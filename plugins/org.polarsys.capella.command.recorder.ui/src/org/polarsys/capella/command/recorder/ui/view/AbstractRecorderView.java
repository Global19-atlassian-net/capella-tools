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
package org.polarsys.capella.command.recorder.ui.view;

import java.io.File;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.polarsys.capella.command.recorder.core.exception.RecorderException;
import org.polarsys.capella.command.recorder.core.manager.AbstractRecorderManager;
import org.polarsys.capella.command.recorder.core.output.OutputHelper;
import org.polarsys.capella.command.recorder.ui.messages.CapellaRecorderUIMessages;
import org.polarsys.capella.common.helpers.ICommonConstants2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 *
 */
abstract public class AbstractRecorderView extends ViewPart {
  
  protected TreeViewer _treeViewer = null;
  
  protected String _fDirectory = OutputHelper.getRootDirectoryForStorage().getPath();
  

  /**
   * Load a record file
   * @param file
   */
  abstract protected boolean loadRecord(File file);

  /**
   * Return the Recorder Manager in use
   * @return
   */
  abstract protected AbstractRecorderManager getRecorderManager();
  
  /**
   * Return the content provider for the viewer
   */
  abstract protected ITreeContentProvider getContentProvider();
  
  /**
   * Return the label provider for the viewer
   */
  abstract protected ITableLabelProvider getLabelProvider();
  
  /**
   * Create the TreeViewer
   */
  abstract protected TreeViewer createTreeViewer(Composite parent);
  
  /**
   * Load a record file
   * @param path
   * @return
   */
  public boolean loadRecord(String path) {
    return loadRecord(new Path(path).toFile());
  }
  
  /**
   * Import a record file from file selected in a FileDialog.
   */
  public String selectRecord() {
    
    FileDialog dialog = new FileDialog(getViewSite().getShell());
    
    dialog.setFilterExtensions(
        new String[] {
            ICommonConstants2.ASTERISK_CHARACTER + OutputHelper.LOG_EXT
        }
    );
    
    String directory = 
      _fDirectory != null ?
          _fDirectory : 
          OutputHelper.getRootDirectoryForStorage().getPath()
    ;
    File file = new File(directory);
    if (!file.exists()) {
    	file.mkdir();
    }
    
    dialog.setFilterPath(directory);
    
    String path = dialog.open();
    
    if (null != path) {
      loadRecord(path);
      _fDirectory = (new Path(path)).removeLastSegments(1).toString();
    }

    return path;
  }
  

  
  /**
   * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void createPartControl(Composite parent) {
    
    _treeViewer = createTreeViewer(parent);
     
    createActions();
    
    _treeViewer.setContentProvider(getContentProvider());
    _treeViewer.setLabelProvider(getLabelProvider());
    
    return;
  }


  /**
   * Creates the actions for the viewsite action bars
   */
  protected void createActions() {
    
    IActionBars bars = getViewSite().getActionBars();
    IToolBarManager toolBarManager = bars.getToolBarManager();
    
    final Action importLogAction = createImportRecordAction();
    
    final Action deleteLogs = createDeleteLogsAction();
    
    toolBarManager.add(importLogAction);
    toolBarManager.add(deleteLogs);
    
    return;
  }
  
  protected Action createImportRecordAction() {
    
    Action action = new ImportRecordAction(this, ICommonConstants.EMPTY_STRING);
    action.setToolTipText(CapellaRecorderUIMessages.recorderView_loadFileMainButton);
    action.setImageDescriptor(
        RecorderSharedImages.getImageDescriptor(RecorderSharedImages.DESC_IMPORT)
    );
    action.setDisabledImageDescriptor(
        RecorderSharedImages.getImageDescriptor(RecorderSharedImages.DESC_IMPORT_DISABLED)
    );
    
    return action;
  }

  protected Action createDeleteLogsAction() {
    Action action = new Action(CapellaRecorderUIMessages.recorderView_deleteButton) {
      @Override
      public void run() {
        String title = CapellaRecorderUIMessages.recorderView_confirmDelete_title;
        String message = CapellaRecorderUIMessages.recorderView_confirmDelete_msg;
        if (!MessageDialog.openConfirm(_treeViewer.getTree().getShell(), title, message)) {
          return;
        }
        
        AbstractRecorderManager mgr = getRecorderManager();
        boolean oldState = mgr.isStarted();
        
        if (true == oldState) {
          try {
            mgr.shutDown();
          } catch (RecorderException exception) {/** Do nothing */}
        }
        
        File root = OutputHelper.getRootDirectoryForStorage();
        OutputHelper.deleteFiles(root.listFiles());
        _treeViewer.setInput(null);
        
        if (true == oldState) {
          try {
            mgr.startup();
          } catch (RecorderException exception) { /** Do nothing */}
        }
        
        
        return;
      }
    };

    action.setToolTipText(CapellaRecorderUIMessages.recorderView_deleteButton_toolTip);
    action.setImageDescriptor(RecorderSharedImages.getImageDescriptor(RecorderSharedImages.DESC_DELETE));
    action.setDisabledImageDescriptor(RecorderSharedImages.getImageDescriptor(RecorderSharedImages.DESC_DELETE));
    action.setEnabled(true);

    return action;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void setFocus() {
    // DO nothing
    return;
  }

}
