import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window {
	
	private static String imageLocation = "demo_input/img1.png";
	
	private static String image = "img";
	
	private static int shade = 190;
	
	public static String output = "demo_output/output_"+image+".png";
	
	private static Color imgCol = Color.black;
	private static Color bgCol = Color.white;
	
	public static BufferedImage readImage(String fileLocation) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(fileLocation));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public static void Window() {
		
		BufferedImage image1 = readImage(imageLocation);
		
		Overlay overlay = new Overlay(imageLocation);
		overlay.changeColor(imgCol);
		int width = image1.getWidth() + 30;
		int height = image1.getHeight() + 30;
		overlay.changeShade(shade);
		JFrame frame2 = new JFrame("GRAPH_Render");
		frame2.getContentPane().add(overlay);
		frame2.setLocationRelativeTo(null);
		frame2.setVisible(true);
		frame2.setSize(width, height);
	    frame2.setBackground(bgCol);
		//saves it as a png with transparent background
		try {
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = image.createGraphics();
			overlay.setSize(width, height);
			layoutComponent(overlay);
			overlay.print(g);
			ImageIO.write(image,"PNG", new File(output));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void init() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				Window();
			}
		});
	}
	
	public static void changeShade(int s){
		shade = s;
	}
	
	public static void changeImgColor(String color){
		switch (color){
			case "black":
				imgCol = Color.black;
				break;
			case "white":
				imgCol = Color.white;
				break;
			case "red":
				imgCol = Color.red;
				break;
			case "blue":
				imgCol = Color.blue;
				break;
			case "green":
				imgCol = Color.green;
				break;
			case "yellow":
				imgCol = Color.yellow;
				break;
			case "gray":
				imgCol = Color.gray;
				break;
			case "cyan":
				imgCol = Color.cyan;
				break;
		}
	}
	
	public static void changeBGColor(String color){
		switch (color){
			case "black":
				bgCol = Color.black;
				break;
			case "white":
				bgCol = Color.white;
				break;
			case "red":
				bgCol = Color.red;
				break;
			case "blue":
				bgCol = Color.blue;
				break;
			case "green":
				bgCol = Color.green;
				break;
			case "yellow":
				bgCol = Color.yellow;
				break;
			case "gray":
				bgCol = Color.gray;
				break;
			case "cyan":
				bgCol = Color.cyan;
				break;
		}
	}
	
	public static void changeFileLocation(String FileLocation){
		imageLocation = FileLocation;
	}
	
	public static void changeImage(String img){
		img = img.substring(0, img.lastIndexOf('.'));
		output = "demo_output/output_"+img+".png";
	}
	
	private static void layoutComponent(Component component) {
	    synchronized (component.getTreeLock()) {
	        component.doLayout();

	        if (component instanceof Container) {
	            for (Component child : ((Container) component).getComponents()) {
	                layoutComponent(child);
	            }
	        }
	    }
	}

}
