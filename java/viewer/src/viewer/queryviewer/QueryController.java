package viewer.queryviewer;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.AbstractAction;
import viewer.util.Util;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * <p>Title: Viewer</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

/**
 * QueryController class contains all reactions on user activity
 * when he works with query viewer.
 */
class QueryController extends AbstractAction implements WindowListener, ComponentListener {
  private final QueryViewerModel model;
  private final QueryViewerUI queryViewer;

  QueryController(QueryViewerModel model, QueryViewerUI queryViewer) {
    super("Run Query");
    this.model = model;
    this.queryViewer = queryViewer;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public void actionPerformed(ActionEvent e) {
    model.openQuery(queryViewer.getQuery());
    queryViewer.adjustColumns();
  }

  public void windowClosing(WindowEvent e) {
    queryViewer.close();
  }

  public void windowOpened(WindowEvent e) {}
  public void windowClosed(WindowEvent e) {}
  public void windowIconified(WindowEvent e) {}
  public void windowDeiconified(WindowEvent e) {}
  public void windowActivated(WindowEvent e) {}
  public void windowDeactivated(WindowEvent e) {}
  public void componentResized(ComponentEvent e) {
    queryViewer.adjustWholeTable();
  }
  public void componentMoved(ComponentEvent e) {}
  public void componentShown(ComponentEvent e) {}
  public void componentHidden(ComponentEvent e) {}
}
