import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class SlideViewerComponent extends JComponent implements Observer {
	private Slide slide; // The current slide
	private Font labelFont = null; // The font for labels
	private JFrame frame = null;

	private static final long serialVersionUID = 227L;
	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;

	public SlideViewerComponent(SlideMover slideMover, JFrame frame) {
		setBackground(BGCOLOR);
		labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
		this.frame = frame;
	}

	public Dimension getPreferredSize() {
		return new Dimension(Slide.WIDTH, Slide.HEIGHT);
	}

	// This method is called when the observed object is changed
	public void update(Observable o, Object arg) {
		if (o instanceof SlideMover) {
			SlideMover slideMover = (SlideMover) o;
			update2(slideMover.getPresentation().getSlideByNumber(slideMover.getSlideNumber()));
			frame.setTitle(slideMover.getPresentation().getTitle());
		}
	}

	// This was the original function named update, I have renamed it to update2
	private void update2(Slide data) {
		if (data == null) {
			repaint();
			return;
		}
		this.slide = data;
		repaint();
	}

	// Draw the slide
	public void paintComponent(Graphics g) {
		g.setColor(BGCOLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);
		if (slide == null) {
			return;
		}
		g.setFont(labelFont);
		g.setColor(COLOR);
		g.drawString("Slide " + slide.getNumber() + " of " +
				slide.getPresentation().getSize(), XPOS, YPOS);
		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
		slide.draw(g, area, this, slide.getPresentation().getTheme());
	}
}
