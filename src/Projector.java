import java.util.Observable;

// Projector class handles presentation display and navigation
public class Projector extends Observable {
    private Presentation presentation;
    //private SlideViewerComponent slideViewComponent;
    private int currentSlideNumber = 0;

    public Projector(Presentation presentation/*, SlideViewerComponent slideViewComponent*/) {
        this.presentation = presentation;
        //this.slideViewComponent = slideViewComponent;
    }

    public Projector()
    {
        this.presentation = new Presentation();
        //slideViewComponent = null;
    }

    // Navigate to the previous slide
    public void prevSlide() {
        if (currentSlideNumber > 0) {
            currentSlideNumber--;
            showCurrentSlide();
        }
    }

    // Navigate to the next slide
    public void nextSlide() {
        if (currentSlideNumber < (presentation.getSize() - 1)) {
            currentSlideNumber++;
            showCurrentSlide();
        }
    }

    // Show the current slide
    public void showCurrentSlide() {
        Slide currentSlide = presentation.getSlide(currentSlideNumber);
        /*if (slideViewComponent != null && currentSlide != null) {
            slideViewComponent.update(this, currentSlide);
        }*/
        setChanged();
        notifyObservers(currentSlide);
    }

    // Set the current slide number
    public void setSlideNumber(int number) {
        currentSlideNumber = number;
        showCurrentSlide();
    }

    // Exit the presentation
    public void exit(int n) {
        System.exit(n);
    }


    // Get the current slide number
    public int getSlideNumber() {
        return currentSlideNumber;
    }

    public void clear()
    {
        presentation = new Presentation();//Nieuwe lege presentatie toevoegen
        currentSlideNumber = 0;
        showCurrentSlide();
    }

    public Presentation getPresentation() {
        return presentation;
    }
}
