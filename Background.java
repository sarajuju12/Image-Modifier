import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import javax.swing.JOptionPane;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Starter code for Image Manipulation Array Assignment.
 * 
 * The class Processor contains all of the code to actually perform
 * transformation. The rest of the classes serve to support that
 * capability. This World allows the user to choose transformations
 * and open files.
 * 
 * Add to it and make it your own!
 * 
 * @author Jordan Cohen
 * @version November 2013
 */
public class Background extends World
{
    // Constants:
    private final String STARTING_FILE = "nctdream.jpg";

    // Objects and Variables:
    private ImageHolder image;

    private TextButton openFile;
    private TextButton blueButton;
    private TextButton hRevButton;
    private TextButton vRevButton;
    private TextButton nRevButton;
    private TextButton greyButton;
    private TextButton negButton;
    private TextButton undoButton;
    private TextButton redoButton;
    
    private ArrayList<GreenfootImage> images = new ArrayList<GreenfootImage>();
    
    private String fileName;

    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 800x650 cells with a cell size of 1x1 pixels.
        super(700, 725, 1); 

        // Initialize buttons and the image
        image = new ImageHolder(STARTING_FILE);
        images.add(Processor.createGreenfootImageFromBI(image.getBufferedImage()));
        
        openFile = new TextButton(" [ Open File: " + STARTING_FILE + " ] ");
        blueButton = new TextButton(" [ Blue-ify ] ");
        hRevButton = new TextButton(" [ Flip Horizontal ] ");
        vRevButton = new TextButton(" [ Flip Vertical ] ");
        nRevButton = new TextButton(" [ Flip 90deg ]" );
        greyButton = new TextButton(" [ Greyscale ] ");
        negButton = new TextButton(" [ Negative ] ");
        undoButton = new TextButton(" [ Undo ] ");
        redoButton = new TextButton(" [ Redo ] ");
        
        // Add objects to the screen
        addObject (image, 350, 375);
        
        addObject (openFile, 77, 10);
        addObject (blueButton, 187, 10);
        addObject (hRevButton, 273, 10);
        addObject (vRevButton, 370, 10);
        addObject (nRevButton, 453, 10);
        addObject (greyButton, 532,10);
        addObject (negButton, 607, 10);
        
        addObject (undoButton, 27, 32);
        addObject (redoButton, 83, 32);
    }
    
    /**
     * Act() method just checks for mouse input
     */
    public void act ()
    {
        checkMouse();
    }

    /**
     * Check for user clicking on a button
     */
    private void checkMouse ()
    {
        // Avoid excess mouse checks - only check mouse if somethething is clicked.
        if (Greenfoot.mouseClicked(null))
        {
            if (Greenfoot.mouseClicked(openFile))
            {
                openFile();
            }
            else if (Greenfoot.mouseClicked(blueButton)){
                Processor.blueify(image.getBufferedImage());
                images.add(Processor.createGreenfootImageFromBI(image.getBufferedImage()));
            }
            else if (Greenfoot.mouseClicked(hRevButton))
            {
                Processor.flipHorizontal(image.getBufferedImage());
                images.add(Processor.createGreenfootImageFromBI(image.getBufferedImage()));
            }
            else if (Greenfoot.mouseClicked(vRevButton))
            {
                Processor.flipVertical(image.getBufferedImage());
                images.add(Processor.createGreenfootImageFromBI(image.getBufferedImage()));
            }
            else if (Greenfoot.mouseClicked(nRevButton))
            {
                Processor.flipNinetyDeg(image.getBufferedImage());
                images.add(Processor.createGreenfootImageFromBI(image.getBufferedImage()));
            }
            else if (Greenfoot.mouseClicked(greyButton))
            {
                Processor.changeToGreyscale(image.getBufferedImage());
                images.add(Processor.createGreenfootImageFromBI(image.getBufferedImage()));
            }
            else if (Greenfoot.mouseClicked(negButton))
            {
                Processor.changeToNegative(image.getBufferedImage());
                images.add(Processor.createGreenfootImageFromBI(image.getBufferedImage()));
            }
            else if (Greenfoot.mouseClicked(undoButton))
            {
                undo();
            } 
            else if (Greenfoot.mouseClicked (redoButton))
            {
                
            }
        }
    }

    /**
     * Allows the user to open a new image file.
     */
    private void openFile ()
    {
        // Use a JOptionPane to get file name from user
        String fileName = JOptionPane.showInputDialog("Please input a file name with extension");

        // If the file opening operation is successful, update the text in the open file button
        if (image.openFile (fileName))
        {
            String display = " [ Open File: " + fileName + " ] ";
            openFile.update (display);
        }

    }
    
    private void undo()
    {
        GreenfootImage undoPic = Processor.deepCopy(images.get(images.size()-2));
        image.setImage(undoPic);
        
        images.add(undoPic);
    }
}

