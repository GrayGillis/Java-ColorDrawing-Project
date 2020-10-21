import javax.swing.*;
public class JavaColorDrawing extends JApplet{
	public void init(){// create a ColorPanel object and add it to the applet
		ColorPanel wholePanel = new ColorPanel();
	    getContentPane().add(wholePanel);

	    //set applet size to 500 X 400
	    setSize (500, 400);
	  }
}