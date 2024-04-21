import javax.swing.JOptionPane;
import java.io.IOException;

public class JabberPoint {
    protected static final String IOERR = "IO Error: ";
    protected static final String JABERR = "Jabberpoint Error ";
    protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";

    /** The main program */
    public static void main(String[] argv) {
        JabberPoint jabberPoint = new JabberPoint();
        jabberPoint.setup(argv);
    }

    private void setup(String[] argv) {
        Theme.createStyles();
        Projector projector = new Projector();
        SlideViewerFrame viewerFrame = new SlideViewerFrame(JABVERSION, projector);
        loadPresentation(projector, argv);
    }

    private void loadPresentation(Projector projector, String[] argv) {
        try {
            if (argv.length == 0) { //a demo presentation
                new XMLAccessor().loadFile(projector, "demoPresentation.xml");
            } else {
                new XMLAccessor().loadFile(projector, argv[0]);
            }
            projector.setSlideNumber(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    IOERR + ex, JABERR,
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
