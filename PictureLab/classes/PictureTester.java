/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
    
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test keepOnlyBlue */
  public static void testKeepOnlyBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.keepOnlyBlue();
    beach.explore();
  }
  
  /** Method to test negate */
  public static void testNegate()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.negate();
    beach.explore();
  }
  
  /** Method to test greyscale */
  public static void testGreyscale()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.greyscale();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorVerticalRightToLeft */
  public static void testMirrorVerticalRightToLeft()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVerticalRightToLeft();
    caterpillar.explore();
  }
  
  /** Method to test mirrorHorizontal */
  public static void testMirrorHorizontal()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorHorizontal();
    caterpillar.explore();
  }
  
  /** Method to test mirrorHorizontalBotToTop */
  public static void testMirrorHorizontalBotToTop()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorHorizontalBotToTop();
    caterpillar.explore();
  }
  
  /** Method to test cropAndCopy */
  public static void testCropAndCopy()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    Picture caterpillar1 = new Picture("caterpillar.jpg");
    caterpillar1.explore();
    caterpillar1.cropAndCopy(caterpillar, 10, 100, 10, 100, 40, 50);
    caterpillar1.explore();
  }
  
  /** Method to test scaleByHalf */
  public static void testScaleByHalf()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.scaleByHalf().explore();
    //beach.explore();
  }
  
  public static void testEnlarge()
  {
      Picture beach = new Picture("beach.jpg");
      beach.explore();
      beach.enlarge().explore();
    }
  
  /** Method to test rotate */
  public static void testRotate()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.rotate().explore();
  }
  
  /** Method to test mirrorTemple */
//   public static void testMirrorTemple()
//   {
//     Picture temple = new Picture("temple.jpg");
//     temple.explore();
//     temple.mirrorTemple();
//     temple.explore();
//   }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
   Picture source = new Picture("schmit.jpg");
    
   Picture pic1 = new Picture("schmit.jpg");
 
   Picture pic2 = new Picture("schmit.jpg");
   pic2.mirrorVertical();
   
   Picture pic3 = new Picture("schmit.jpg");
   pic3.negate();
   
   Picture pic4 = new Picture("schmit.jpg");
   pic4.setYellow(100, 100);
   
   Picture pic5 = new Picture("face.jpg");
   Picture smallSchmit = pic5.scaleByHalf().scaleByHalf();
   
  
   Picture canvas = new Picture(source.enlarge().getHeight(), source.enlarge().getWidth()); 
   canvas.copy(pic1, 0, 0);
   canvas.copy(pic2, 0, pic1.getWidth());
   canvas.copy(pic3, pic1.getHeight(), 0);
   canvas.copy(pic4, pic1.getHeight(), pic1.getWidth());
   int red = 222;
   int green = 0;
   int blue = 0;
   int pic = 0;
   for (int totalWidth = 0; totalWidth < canvas.getWidth(); totalWidth += smallSchmit.getWidth())
   {
       smallSchmit.rainbow(red, green, blue);
       canvas.copy(smallSchmit, pic1.getHeight() - (smallSchmit.getHeight() / 2), totalWidth);
       red -= 20;
       if (pic % 2 == 0)
       {
           green += (2 * pic);
        }
       else
       {
           green -= (2 * pic);
        }
       blue += 5;
   }
   canvas.explore();
   canvas.write("C:\\Users\\gschmit\\Desktop\\GitHub\\unit6MediaComp\\images\\MyCollage.jpg");

  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}