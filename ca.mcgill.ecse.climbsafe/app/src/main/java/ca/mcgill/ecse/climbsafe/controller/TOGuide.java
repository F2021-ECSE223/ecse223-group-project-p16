/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 35 "../../../../../ClimbSafeTransferObjects.ump"
public class TOGuide
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOGuide Attributes
  private String guideEmail;
  private String guideName;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOGuide(String aGuideEmail, String aGuideName)
  {
    guideEmail = aGuideEmail;
    guideName = aGuideName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGuideEmail(String aGuideEmail)
  {
    boolean wasSet = false;
    guideEmail = aGuideEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuideName(String aGuideName)
  {
    boolean wasSet = false;
    guideName = aGuideName;
    wasSet = true;
    return wasSet;
  }

  public String getGuideEmail()
  {
    return guideEmail;
  }

  public String getGuideName()
  {
    return guideName;
  }

  public void delete()
  {}


  @Override
  // line 40 "../../../../../ClimbSafeTransferObjects.ump"
   public String toString(){
    return guideName + " + " + guideEmail;
  }

}