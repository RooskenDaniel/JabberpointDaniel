import java.util.ArrayList;

// Presentation class only holds slides
public class Presentation {
    private ArrayList<Slide> showList = new ArrayList<>(); // An ArrayList with slides
    private String showTitle; // The title of the presentation

    public Presentation() {
    }

    public int getSize() {
        return showList.size();
    }

    // Add a slide to the presentation
    public void append(Slide slide) {
        showList.add(slide);
    }

    // Return a slide with a specific number
    public Slide getSlide(int number) {
        if (number < 0 || number >= getSize()) {
            return null;
        }
        return showList.get(number);
    }

    // Get the title of the presentation
    public String getTitle() {
        return showTitle;
    }

    // Set the title of the presentation
    public void setTitle(String title) {
        this.showTitle = title;
    }
}
