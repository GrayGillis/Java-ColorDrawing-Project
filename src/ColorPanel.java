import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ColorPanel extends JPanel{
	private Color currentColor;
	private CanvasPanel canvas;
	private JPanel leftPanel;
	private JButton undo;
	private JButton clear;
	private ArrayList<Rectangle> rectList, prevList;
	private ArrayList<Color> colorList;
	private JComboBox combo;
	private int gridWidth;
	private int gridHeight;
	private boolean prevWasClear;
	private ArrayList<Color> prevColorList;
	   
	public ColorPanel(){
		prevWasClear = false;//setting prevWasClear to false
	    currentColor = Color.BLACK;//default color to draw is black
	    rectList = new ArrayList<Rectangle>();//creates the rectList
	    prevList = new ArrayList<Rectangle>();//creates the prevList
	    colorList = new ArrayList<Color>();//creates the colorList
	    prevColorList = new ArrayList<Color>();//creates the prevColorList
	      
	    undo = new JButton ("Undo");//creates an undo button
	    undo.addActionListener(new ButtonListener());//adds "Undo" to a ButtonListener
	    clear = new JButton ("Clear");//creates a clear button
	    clear.addActionListener(new ButtonListener());//adds "Clear" to a ButtonListener 
	      
	    String[] colors = {"black", "red", "blue", "green", "orange", "cyan", "magenta", "yellow", "pink", "gray"};//creates the colors for the JComboBox
	    combo = new JComboBox(colors);//creates the JComboBox
	    combo.addActionListener(new ColorListener());//adds a ColorListener to the JComboBox
	    ColorListener colorListen = new ColorListener();//creates the ColorListener
	    PointListener pointListen = new PointListener();//creates the PointListener

	    leftPanel = new JPanel();//creates leftPanel panel
	    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));//uses box layout
	    leftPanel.add(undo);//adds an undo button
	    leftPanel.add(clear);//adds a clear button
	    leftPanel.add(combo);//adds a JComboBox
	     
	    canvas = new CanvasPanel();//creates canvas panel
	    add(canvas);//adds canvas panel
		canvas.addMouseListener(pointListen);//adds pointListen to the canvas panel
	      
	    JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, canvas);//splits the panels leftPanel and canvas
	    setLayout(new BorderLayout());//using BorderLayout
	    add(sp);//adds the JSplitPane
	}
	
	//CanvasPanel is the panel where shapes will be drawn
	private class CanvasPanel extends JPanel{
		public void paintComponent(Graphics page){//this method draws all shapes
			super.paintComponent(page);
	        setBackground(Color.WHITE);//sets the background to white
	       
	        gridWidth = (int)(this.getSize().getWidth()/(4.0));//gets the gridWidth
	        gridHeight = (int)(this.getSize().getHeight()/(4.0));//gets the gridHeight
	        
	        for (int i=1; i< 4; i++){//begin for loop for drawing rectangles  	
	        	page.drawLine(0,gridHeight*i,(int)(getSize().getWidth()),gridHeight*i);//drawing grid lines
	        	page.drawLine(gridWidth*i,0,gridWidth*i,(int)(getSize().getHeight()));//drawing grid lines 	
	        }//end of for loop
	        
	        for (int i = 0; i < rectList.size(); i++){//begin for loop for filling rectangles
	        	if (!rectList.isEmpty()){//if the rectList is not empty
	        		page.setColor(colorList.get(i));//sets the color from the colorList ArrayList
	        		page.fillRect(rectList.get(i).x, rectList.get(i).y, gridWidth, gridHeight);//fills the rectangle with the x and y from rectList ArrayList, and gridWidth and gridHeight
	        	}
	        }this.updateUI();//updates
	        
	       } 
	   }//end of CanvasPanel class

	//ButtonListener defined actions to take in case
	//"Undo", or "Clear" is chosen.
	private class ButtonListener implements ActionListener{
		public void actionPerformed (ActionEvent event){
			//needs to be filled
			if (event.getSource() == undo){//if "Undo"was pressed
				//create a for loop to copy every element from rectList to prevList
	    		if(!prevWasClear){
	    			rectList.remove(rectList.size() - 1);//removing the last rectangle displayed 
	    			colorList.remove(colorList.size() - 1);//removing the last color displayed
	    			repaint();//repaints
	    		}
	    		else{//returning the previous rectangles from doing an undo after clear
	    			 //reload prevList into rectList
	    			 //rectList = prevList 
	    			 
	    			 for (int i = 0; i < prevList.size(); i++){//for loop for finding the size of prevList
	    				 rectList.add(prevList.get(i));//adds the prevList at index to the rectList	 
	    			 }
	    			  
	    			 for (int x = 0; x < prevColorList.size(); x++){//for loop for finding the size of prevColorList
	        			  colorList.add(prevColorList.get(x));//adds the prevColorList at index to the colorList
	        		 }
	    			 repaint();//repaints  
	    		}
	    	}
	    	if (event.getSource() == clear){//if "Clear" was pressed
	    		prevColorList.clear();//clears prevColorList
	    		prevList.clear();//clears prevList
	    		  
	    		for (int x = 0; x < colorList.size(); x++){
	    			prevColorList.add(colorList.get(x));//adds the colorList at index to the prevColorList
	    		}  
	    		
	    		colorList.clear();//clears colorList 
	    		
	    		for (int x = 0; x < rectList.size(); x++){
	    			  prevList.add(rectList.get(x));//adds the rectList at index to the prevList
	    		}
	    		  
	    		rectList.clear();//clears rectList
	    		System.out.println(prevList.size());//prints the size
	    		repaint();//repaints
	    		prevWasClear = true;//the previous action was clear
	    	  }
	      }
	}// end of ButtonLister Class	
	
	// listener class to set the color chosen by a user using
	// the color combo box.
	private class ColorListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//needs to be filled
			if(combo.getSelectedIndex() == 0){
	        	currentColor = Color.BLACK;//sets the currentColor to Black at index 0
	        	combo.updateUI();
	        }
	        if(combo.getSelectedIndex() == 1){
	        	currentColor = Color.RED;//sets the currentColor to Red at index 1
	        	combo.updateUI();
	        }
	        if(combo.getSelectedIndex() == 2){
	        	currentColor = Color.BLUE;////sets the currentColor to Blue at index 2
	        	combo.updateUI();
	        }
	        if(combo.getSelectedIndex() == 3){
	        	currentColor = Color.GREEN;//sets the currentColor to Green at index 3
	        	combo.updateUI();
	        }
	        if(combo.getSelectedIndex() == 4){
	        	currentColor = Color.ORANGE;//sets the currentColor to Orange at index 4
	        	combo.updateUI();
	        }
	        if(combo.getSelectedIndex() == 5){
	        	currentColor = Color.CYAN;//sets the color to Cyan @ index 5
	        	combo.updateUI();
	        }
	        if(combo.getSelectedIndex() == 6){
	        	currentColor = Color.MAGENTA;
	        	combo.updateUI();
	        }
	        if(combo.getSelectedIndex() == 7){
	        	currentColor = Color.YELLOW;
	        	combo.updateUI();
	        }
	        if(combo.getSelectedIndex() == 8){
	        	currentColor = Color.PINK;
	        	combo.updateUI();
	        }
	        if(combo.getSelectedIndex() == 9){
	        	currentColor = Color.GRAY;
	        	combo.updateUI();
	        }
		}
	}// end of ColorListener Class

	// listener class that listens to the mouse
	public class PointListener implements MouseListener{
		//in case that a user presses using a mouse,
		//record the point where it was pressed.
	    public void mousePressed (MouseEvent event){
	    	//needs to be filled
	    	Point pt = event.getPoint();//gets a point
	    	 
	    	int count = 0;//count at0
	    	while(pt.getY()>gridHeight){//start while loop to see where to create a rectangle in the panel
	    		 pt.y-=gridHeight;//finds the y-coordinate
	    		 count++;//increments the count for the number
	    	}//end of while loop
	    	
	    	int yCoordinate = count*gridHeight;//gets the y-coordinate
	    	System.out.println(yCoordinate);//prints it
	    	count = 0;//count at 0
	    	while(pt.getX()>gridWidth){//start while loop to see where to create a rectangle in the panel
	    		 pt.x-=gridWidth;//finds the x-coordinate
	    		 count++;//increments the count for the number
	    	 }//end of while loop
	    	
	    	int xCoordinate = count*gridWidth;//gets the x-coordinate
	    	System.out.println(xCoordinate);//prints it
	    	Rectangle rect = new Rectangle(xCoordinate,yCoordinate, gridWidth, gridHeight);//creates the rectangle
	    	//setBackground(currentColor); 
	    	rectList.add(rect);//adds the rectangle to the rectList
	    	System.out.println(prevList.size());//prints the size
	    	colorList.add(currentColor);//adds currentColor to the colorList
	    	repaint();//repaints
	     }
	     
	     public void mouseReleased (MouseEvent event) {}
	     public void mouseClicked (MouseEvent event) {}
	     public void mouseEntered (MouseEvent event) {}
	     public void mouseExited (MouseEvent event) {}
	}//end of PointListener Class
}// end of ColorPanel Class