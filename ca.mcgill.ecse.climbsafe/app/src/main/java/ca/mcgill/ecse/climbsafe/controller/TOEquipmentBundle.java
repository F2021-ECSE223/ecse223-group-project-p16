/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 28 "../../../../../ClimbSafeTransferObjects.ump"

public class TOEquipmentBundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOEquipmentBundle Attributes
  private String equipmentBundleName;
  private int discount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOEquipmentBundle(String aEquipmentBundleName, int aDiscount)
  {
    equipmentBundleName = aEquipmentBundleName;
    discount = aDiscount;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEquipmentBundleName(String aEquipmentBundleName)
  {
    boolean wasSet = false;
    equipmentBundleName = aEquipmentBundleName;
    wasSet = true;
    return wasSet;
  }

  public boolean setDiscount(int aDiscount)
  {
    boolean wasSet = false;
    discount = aDiscount;
    wasSet = true;
    return wasSet;
  }

  public String getEquipmentBundleName()
  {
    return equipmentBundleName;
  }

  public int getDiscount()
  {
    return discount;
  }

  public void delete()
  {}


  @Override
  // line 29 "../../../../../ClimbSafeTransferObjects.ump"
   public String toString(){
    return equipmentBundleName + " " + discount;
  }

}