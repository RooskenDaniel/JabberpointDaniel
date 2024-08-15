package Slidestuff;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Presentationstuff.*;
import Stylestuff.BitmapItem;
import Stylestuff.TextItem;
import org.w3c.dom.NodeList;


/** Menu.XMLAccessor, reads and writes XML files
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class XMLSaver
{
	public void saveFile(Projector projector, String filename) throws IOException {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			// Create the root element <presentation>
			Element root = document.createElement("presentation");
			document.appendChild(root);

			// Add <showtitle> element
			Element showTitle = document.createElement("showtitle");
			showTitle.appendChild(document.createTextNode(projector.getPresentation().getTitle()));
			root.appendChild(showTitle);

			// Add each slide as <slide> element
			int numSlides = projector.getPresentation().getSize();
			for (int i = 0; i < numSlides; i++) {
				Slide slide = projector.getPresentation().getSlide(i);
				Element slideElement = createSlideElement(document, slide);
				root.appendChild(slideElement);
			}

			// Create a Transformer to write the document to a file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(filename));

			// Transform the DOM document to an XML file
			transformer.transform(domSource, streamResult);

		} catch (ParserConfigurationException | TransformerException e) {
			throw new IOException("Failed to save file", e);
		}
	}

	private Element createSlideElement(Document document, Slide slide) {
		Element slideElement = document.createElement("slide");

		// Add <title> element
		Element titleElement = document.createElement("title");
		titleElement.appendChild(document.createTextNode(slide.getTitle()));
		slideElement.appendChild(titleElement);

		// Add each slide item as <item> element
		for (SlideItem slideItem : slide.getSlideItems()) {
			Element itemElement = createSlideItemElement(document, slideItem);
			slideElement.appendChild(itemElement);
		}

		return slideElement;
	}

	private Element createSlideItemElement(Document document, SlideItem slideItem) {
		String kind = getItemKind(slideItem);
		Element itemElement = document.createElement("item");
		itemElement.setAttribute("kind", kind);
		itemElement.setAttribute("level", String.valueOf(slideItem.getLevel()));

		String content = getSlideItemContent(slideItem);
		itemElement.appendChild(document.createTextNode(content));

		return itemElement;
	}

	private String getItemKind(SlideItem slideItem) {
		if (slideItem instanceof TextItem) {
			return "text";
		} else if (slideItem instanceof BitmapItem) {
			return "image";
		} else {
			throw new IllegalArgumentException("Unknown SlideItem type: " + slideItem.getClass().getName());
		}
	}

	private String getSlideItemContent(SlideItem slideItem) {
		if (slideItem instanceof TextItem) {
			return ((TextItem) slideItem).getText();
		} else if (slideItem instanceof BitmapItem) {
			return ((BitmapItem) slideItem).getName();
		} else {
			throw new IllegalArgumentException("Unknown SlideItem type: " + slideItem.getClass().getName());
		}
	}
}
