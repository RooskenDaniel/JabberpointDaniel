import javax.swing.JOptionPane;
import java.io.IOException;

/** <p>The logic behind the menu</p>
 */
public class MenuLogic {

    protected static final String ABOUT = "About";
    protected static final String FILE = "File";
    protected static final String EXIT = "Exit";
    protected static final String GOTO = "Go to";
    protected static final String HELP = "Help";
    protected static final String NEW = "New";
    protected static final String NEXT = "Next";
    protected static final String OPEN = "Open";
    protected static final String PAGENR = "Page number?";
    protected static final String PREV = "Prev";
    protected static final String SAVE = "Save";
    protected static final String VIEW = "View";

    protected static final String TESTFILE = "testPresentation.xml";
    protected static final String SAVEFILE = "savedPresentation.xml";

    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";

    private SlideViewerFrame parent;
    private Projector projector;

    public MenuLogic(SlideViewerFrame parent, Projector projector) {
        this.parent = parent;
        this.projector = projector;
    }

    public void openFile() {
        projector.clear();
        XMLAccessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.loadFile(projector, TESTFILE);
            projector.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, IOEX + exc,
                    LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        parent.repaint();
    }

    public void createNew() {
        projector.clear();
        parent.repaint();
    }

    public void saveFile() {
        XMLAccessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.saveFile(projector, SAVEFILE);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, IOEX + exc,
                    SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exit() {
        projector.exit(0);
    }

    public void nextSlide() {
        projector.nextSlide();
    }

    public void prevSlide() {
        projector.prevSlide();
    }

    public void goToSlide() {
        String pageNumberStr = JOptionPane.showInputDialog((Object)PAGENR);
        int pageNumber = Integer.parseInt(pageNumberStr);
        if (pageNumber < 1 || pageNumber > projector.getPresentation().getSize()) {//Kijken of deze slide bestaat
            SlideDoesNotExistBox.show(parent);
        }
        else {
            projector.setSlideNumber(pageNumber - 1);
        }
    }

    public void showAbout() {
        AboutBox.show(parent);
    }
}
