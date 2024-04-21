import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

/**
 * <p>The applicatiewindow for a slideviewcomponent</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class SlideViewerFrame extends JFrame {
	private static final long serialVersionUID = 3227L;

	private static final String JABTITLE = "Jabberpoint 1.6 - OU";
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;

	public SlideViewerFrame(String title, Projector projector) {
		super(title);
        //Projector projector = new Projector();
        Painter painter = new Painter(projector, this);
		//SlideViewerComponent slideViewerComponent = new SlideViewerComponent(projector, this);
		//projector.setShowView(slideViewerComponent);
		setupWindow(painter.getSlideViewerComponent(), projector);
	}

//Setup the GUI
	public void setupWindow(SlideViewerComponent
			slideViewerComponent, Projector projector) {
		setTitle(JABTITLE);
		addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		getContentPane().add(slideViewerComponent);
		addKeyListener(new KeyController(projector)); //Add a controller
        MenuLogic menuLogic = new MenuLogic(this, projector);
        setMenuBar(new MenuView(this, menuLogic));
		setSize(new Dimension(WIDTH, HEIGHT)); //Same sizes a slide has
		setVisible(true);
	}
}