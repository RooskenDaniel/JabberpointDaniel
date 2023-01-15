import java.util.ArrayList;


/**
 * <p>Presentations keeps track of the slides in a presentation.</p>
 * <p>Only one instance of this class is available.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

/*
Deze class was voor te veel verantwoordelijk. Namelijk het zijn van een presentatie en zichzelf van dia veranderen. Deze class is nu puur een presentatie. SlideMover regelt het wisselen van de dia's
 */

public class Presentation {
	private String showTitle; //The title of the presentation
	private ArrayList<Slide> showList = null; //An ArrayList with slides
	/*Het currentslidenumber wordt nu bijgehouden in SlideMover
	/*private SlideViewerComponent slideViewComponent = null; //The view component of the slides*/

	public Presentation() {
		/*slideViewComponent = null;*/
		clear();
	}

	public int getSize() {
		return showList.size();
	}

	public ArrayList<Slide> getShowList()
	{
		return showList;
	}

	public String getTitle() {
		return showTitle;
	}

	public void setTitle(String nt) {
		showTitle = nt;
	}

	public Slide getSlideByNumber(int number)
	{
		return getShowList().get(number);
	}

	//Remove the presentation
	void clear() {
		showList = new ArrayList<Slide>();
	}

	//Add a slide to the presentation
	public void append(Slide slide) {
		showList.add(slide);
	}

	//Return a slide with a specific number
	public Slide getSlide(int number) {
		if (number < 0 || number >= getSize())
			return null;
		return (Slide)showList.get(number);
	}

	public void exit(int n) {
		System.exit(n);
	}
}
