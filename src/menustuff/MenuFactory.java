package menustuff;

import Presentationstuff.Projector;
import Slidestuff.SlideViewerFrame;

import java.awt.*;

public class MenuFactory {

    private static MenuItem createMenuItem(String name) {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }


    public static Menu CreateMenu(String type, SlideViewerFrame parent, Projector projector)
    {
        MenuLogic menuLogic = new MenuLogic(parent, projector);
        Menu menu = null;

        if (type.equals(MenuLogic.FILE)) {
            menu = new Menu(MenuLogic.FILE);
            menu.add(createMenuItem(MenuLogic.OPEN)).addActionListener(e -> menuLogic.openFile());
            menu.add(createMenuItem(MenuLogic.NEW)).addActionListener(e -> menuLogic.createNew());
            menu.add(createMenuItem(MenuLogic.SAVE)).addActionListener(e -> menuLogic.saveFile());
            menu.addSeparator();
            menu.add(createMenuItem(MenuLogic.EXIT)).addActionListener(e -> menuLogic.exit());
        } else if (type.equals(MenuLogic.VIEW)) {
            menu = new Menu(MenuLogic.VIEW);
            menu.add(createMenuItem(MenuLogic.NEXT)).addActionListener(e -> menuLogic.nextSlide());
            menu.add(createMenuItem(MenuLogic.PREV)).addActionListener(e -> menuLogic.prevSlide());
            menu.add(createMenuItem(MenuLogic.GOTO)).addActionListener(e -> menuLogic.goToSlide());
        } else if (type.equals(MenuLogic.HELP)) {
            menu = new Menu(MenuLogic.HELP);
            menu.add(createMenuItem(MenuLogic.ABOUT)).addActionListener(e -> menuLogic.showAbout());
        }
        return menu;
    }
}
