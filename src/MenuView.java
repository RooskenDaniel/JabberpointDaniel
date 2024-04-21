import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;

/** <p>The view for the menu</p>
 */
public class MenuView extends MenuBar {

    private static final long serialVersionUID = 227L;

    public MenuView(Frame parent, MenuLogic menuLogic) {
        MenuItem menuItem;
        Menu fileMenu = new Menu(MenuLogic.FILE);
        fileMenu.add(menuItem = mkMenuItem(MenuLogic.OPEN));
        menuItem.addActionListener(e -> menuLogic.openFile());
        fileMenu.add(menuItem = mkMenuItem(MenuLogic.NEW));
        menuItem.addActionListener(e -> menuLogic.createNew());
        fileMenu.add(menuItem = mkMenuItem(MenuLogic.SAVE));
        menuItem.addActionListener(e -> menuLogic.saveFile());
        fileMenu.addSeparator();
        fileMenu.add(menuItem = mkMenuItem(MenuLogic.EXIT));
        menuItem.addActionListener(e -> menuLogic.exit());
        add(fileMenu);

        Menu viewMenu = new Menu(MenuLogic.VIEW);
        viewMenu.add(menuItem = mkMenuItem(MenuLogic.NEXT));
        menuItem.addActionListener(e -> menuLogic.nextSlide());
        viewMenu.add(menuItem = mkMenuItem(MenuLogic.PREV));
        menuItem.addActionListener(e -> menuLogic.prevSlide());
        viewMenu.add(menuItem = mkMenuItem(MenuLogic.GOTO));
        menuItem.addActionListener(e -> menuLogic.goToSlide());
        add(viewMenu);

        Menu helpMenu = new Menu(MenuLogic.HELP);
        helpMenu.add(menuItem = mkMenuItem(MenuLogic.ABOUT));
        menuItem.addActionListener(e -> menuLogic.showAbout());
        setHelpMenu(helpMenu);
    }

    //Creating a menu-item
    private MenuItem mkMenuItem(String name) {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
