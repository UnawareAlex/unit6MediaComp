import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /**Method to set red and green to 0 */
  public void keepOnlyBlue()
  {
     Pixel[][] pixels = this.getPixels2D();
     for (Pixel[] rowArray : pixels)
     {
         for (Pixel pixelObj : rowArray)
         {
             pixelObj.setGreen(0);
             pixelObj.setRed(0);
            }
        }
  }
  
  /**Method to set yellow tint */
  public void setYellow(int amountRed, int amountGreen)
  {
     Pixel[][] pixels = this.getPixels2D();

     for (Pixel[] rowArray : pixels)
     {
         for (Pixel pixelObj : rowArray)
         {
             int red = pixelObj.getRed();
             int green = pixelObj.getGreen();
             pixelObj.setGreen(red + amountRed);
             pixelObj.setRed(green + amountGreen);
            }
        }
  }
  
  /**Method to set color to specific tint*/
  public void rainbow(int red, int green, int blue)
  {
     Pixel[][] pixels = this.getPixels2D();
     for (Pixel[] rowArray : pixels)
     {
         for (Pixel pixelObj : rowArray)
         {
             int redPic = pixelObj.getRed();
             int greenPic = pixelObj.getGreen();
             int bluePic = pixelObj.getBlue();
             pixelObj.setRed(redPic + red);
             pixelObj.setGreen(greenPic + green);
             pixelObj.setBlue(bluePic + blue);
            }
        }
  }
  
  /**Method to negate picture */
  public void negate()
  {
     Pixel[][] pixels = this.getPixels2D();
     for (Pixel[] rowArray : pixels)
     {
         for (Pixel pixelObj : rowArray)
         {
             pixelObj.setGreen(255 - pixelObj.getGreen());
             pixelObj.setRed(255 - pixelObj.getRed());
             pixelObj.setBlue(255 - pixelObj.getBlue());
            }
        }
    }
  
  /**Method to turn picture to shades of grey */
  public void greyscale()
  {
     Pixel[][] pixels = this.getPixels2D();
     int avg = 0;
     for (Pixel[] rowArray : pixels)
     {
         for (Pixel pixelObj : rowArray)
         {
             avg = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3;
             pixelObj.setRed(avg);
             pixelObj.setGreen(avg);
             pixelObj.setBlue(avg);
            }
        }
  }
  
  /**Method to crop and copy a region */
  public void cropAndCopy( Picture sourcePicture, int startSourceRow, int endSourceRow, int startSourceCol, int endSourceCol,
         int startDestRow, int startDestCol )
  {
    Pixel pixel = null;
    int newRow = startDestRow;
    int newCol = startDestCol;
    Pixel[][] sourcePixels = sourcePicture.getPixels2D();
    Pixel[][] destinationPixels = this.getPixels2D();
    for (int row = startSourceRow; row < endSourceRow; row++)
    {
      for (int col = startSourceCol; col < endSourceCol; col++)
      {
         destinationPixels[newRow][newCol].setColor(sourcePixels[row][col].getColor());
         newCol++;
      }
      newRow++;
      newCol = startDestCol;
    }
  }
  
  /**Method to scale a region */
  public Picture scaleByHalf()
  {
    Pixel[][] sourcePixels = this.getPixels2D();
    int newRowLength = (int)(sourcePixels.length * .5) + (sourcePixels.length % 2);
    int newColLength = (int)(sourcePixels[0].length * .5) + (sourcePixels[0].length % 2);
    Picture newPicture = new Picture(newRowLength, newColLength);
    Pixel[][] destinationPixels = newPicture.getPixels2D();
    int newRow = 0;
    int newCol = 0;
    for (int row = 0; row < sourcePixels.length; row += 2)
    {
      for (int col = 0; col < sourcePixels[0].length; col += 2)
      {
         destinationPixels[newRow][newCol].setColor(sourcePixels[row][col].getColor());
         newCol++;
      }
      newRow++;
      newCol = 0;
    }
    return newPicture;
  }
  
  /**Method to scale a region */
  public Picture enlarge()
  {
    Pixel[][] sourcePixels = this.getPixels2D();
    int newRowLength = (int)(sourcePixels.length * 2) + (sourcePixels.length % 2);
    int newColLength = (int)(sourcePixels[0].length * 2) + (sourcePixels[0].length % 2);
    Picture newPicture = new Picture(newRowLength, newColLength);
    Pixel[][] destinationPixels = newPicture.getPixels2D();
    int newRow = 0;
    int newCol = 0;
    for (int row = 0; row < sourcePixels.length; row++)
    {
      for (int col = 0; col < sourcePixels[0].length; col++)
      {
         destinationPixels[newRow][newCol].setColor(sourcePixels[row][col].getColor());
         newCol++;
         destinationPixels[newRow][newCol].setColor(sourcePixels[row][col].getColor());
         newCol++;
      }
      newRow++;
      newCol = 0;
      for (int col = 0; col < sourcePixels[0].length; col++)
      {
         destinationPixels[newRow][newCol].setColor(sourcePixels[row][col].getColor());
         newCol++;
         destinationPixels[newRow][newCol].setColor(sourcePixels[row][col].getColor());
         newCol++;
      }
      newRow++;
      newCol = 0;
    }
    return newPicture;
  }
  
  /**Method to rotate an image by 90 degrees to the right  */
  public Picture rotate()
  {
    Pixel[][] pixels = this.getPixels2D();
    int newRowLength = pixels[0].length;
    int newColLength = pixels.length;
    Picture newPicture = new Picture(newRowLength, newColLength);
    Pixel[][] destinationPixels = newPicture.getPixels2D();
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
         destinationPixels[col][row].setColor(pixels[row][col].getColor());
      }
    }
    return newPicture;
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  public void mirrorVerticalRightToLeft()
  {
      Pixel[][] pixels = this.getPixels2D();
      Pixel leftPixel = null;
      Pixel rightPixel = null;
      int width = pixels[0].length;
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < width / 2; col++)
          {
              rightPixel = pixels[row][col];
              leftPixel = pixels[row][width - 1 - col];
              leftPixel.setColor(rightPixel.getColor());
            }
        } 
    }
  
  public void mirrorHorizontal()
  {
      Pixel[][] pixels = this.getPixels2D();
      Pixel topPixel = null;
      Pixel bottomPixel = null;
      int height = pixels.length;
      for (int row = 0; row < height/2; row++)
      {
          for (int col = 0; col < pixels[row].length; col++)
          {
              topPixel = pixels[row][col];
              bottomPixel = pixels[height - 1 - row][col];
              bottomPixel.setColor(topPixel.getColor());
            }
        } 
    }
    
  public void mirrorHorizontalBotToTop()
  {
      Pixel[][] pixels = this.getPixels2D();
      Pixel topPixel = null;
      Pixel bottomPixel = null;
      int height = pixels.length;
      for (int row = 0; row < height/2; row++)
      {
          for (int col = 0; col < pixels[row].length; col++)
          {
              bottomPixel = pixels[row][col];
              topPixel = pixels[height - 1 - row][col];
              topPixel.setColor(bottomPixel.getColor());
            }
        } 
    }
    
  /** Mirror just part of a picture of a temple */
  public void mirror(int rowStart, int colStart, int rowEnd, int colEnd)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = rowStart; row < rowEnd; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = colStart; col < colEnd; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][colEnd - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
    System.out.println(count);
  }
 
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.createCollage();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
