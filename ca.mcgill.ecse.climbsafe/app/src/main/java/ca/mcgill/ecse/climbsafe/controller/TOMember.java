/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 44 "../../../../../ClimbSafeTransferObjects.ump"
public class TOMember
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOMember Attributes
  private String memberEmail;
  private String memberName;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOMember(String aMemberEmail, String aMemberName)
  {
    memberEmail = aMemberEmail;
    memberName = aMemberName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMemberEmail(String aMemberEmail)
  {
    boolean wasSet = false;
    memberEmail = aMemberEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setMemberName(String aMemberName)
  {
    boolean wasSet = false;
    memberName = aMemberName;
    wasSet = true;
    return wasSet;
  }

  public String getMemberEmail()
  {
    return memberEmail;
  }

  public String getMemberName()
  {
    return memberName;
  }

  public void delete()
  {}


  @Override
  // line 49 "../../../../../ClimbSafeTransferObjects.ump"
   public String toString(){
    return memberName + " + " + memberEmail;
  }

}