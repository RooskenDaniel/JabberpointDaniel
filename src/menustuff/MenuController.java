package menustuff;

import Presentationstuff.Projector;
import Slidestuff.SlideViewerFrame;

import java.awt.MenuBar;
import java.awt.Frame;

import static menustuff.MenuAttributes.*;


/**
 * <p>The controller for the menu</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {
    public static final String TESTFILE = "testPresentation.xml";
    public static final String SAVEFILE = "savedPresentation.xml";

    private static final long serialVersionUID = 227L;

    public MenuController(Frame parent, Projector projector) {
        add(MenuFactory.CreateMenu(FILE, (SlideViewerFrame) parent, projector));
        add(MenuFactory.CreateMenu(VIEW, (SlideViewerFrame) parent, projector));
        setHelpMenu(MenuFactory.CreateMenu(HELP, (SlideViewerFrame) parent, projector));
    }
}
