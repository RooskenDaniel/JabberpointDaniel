package Menustuff;

import Presentationstuff.Projector;
import Slidestuff.SlideViewerFrame;

import java.awt.MenuBar;
import java.awt.Frame;




/**
 * <p>The controller for the menu</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {
    private static final long serialVersionUID = 227L;

    public MenuController(Frame parent, Projector projector) {
        add(MenuFactory.CreateMenu("File", (SlideViewerFrame) parent, projector));
        add(MenuFactory.CreateMenu("View", (SlideViewerFrame) parent, projector));
        setHelpMenu(MenuFactory.CreateMenu("Help", (SlideViewerFrame) parent, projector));
    }
}
