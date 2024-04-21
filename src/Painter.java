import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class Painter implements Observer {
    SlideViewerComponent slideViewerComponent;

    public Painter(Projector projector, JFrame slideViewerFrame) {
        this.slideViewerComponent = new SlideViewerComponent(slideViewerFrame);
        projector.addObserver(this);
    }

    public SlideViewerComponent getSlideViewerComponent() {
        return slideViewerComponent;
    }

    @Override
    public void update(Observable o, Object arg) {
        Slide data = (Slide) arg;
        if (data == null) {
            //repaint();
            return;
        }
        Projector projector = (Projector) o;
        slideViewerComponent.setProjector(projector);
        slideViewerComponent.setSlide(data);
        slideViewerComponent.repaint();
        //frame.setTitle(projector.getPresentation().getTitle());
    }
}
