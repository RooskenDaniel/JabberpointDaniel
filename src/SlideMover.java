import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class SlideMover extends Observable
{
    private Presentation presentation;
    private Override obs;
    private int currentSlideNumber = 0;

    public SlideMover(Presentation presentation) {
        this.presentation = presentation;
    }

    //Change the current slide number and report it the the window
    public void setSlideNumber(int number) {
        currentSlideNumber = number;
        /*if (slideViewComponent != null) {
            slideViewComponent.update(this, getCurrentSlide());
        }*/
        setChanged();
        notifyObservers();
    }

    public void presUpdate()
    {
        notifyObservers();
    }

    //Navigate to the previous slide unless we are at the first slide
    public void prevSlide() {
        if (currentSlideNumber > 0) {
            setSlideNumber(currentSlideNumber - 1);
        }
    }

    //Navigate to the next slide unless we are at the last slide
    public void nextSlide() {
        if (currentSlideNumber < (presentation.getShowList().size()-1)) {
            setSlideNumber(currentSlideNumber + 1);
        }
    }

    //Return the current slide
    public int getSlideNumber() {
        return currentSlideNumber;
    }

    void clear() {
        presentation.clear();
        setSlideNumber(-1);
    }

    public Presentation getPresentation()
    {
        return presentation;
    }

}
