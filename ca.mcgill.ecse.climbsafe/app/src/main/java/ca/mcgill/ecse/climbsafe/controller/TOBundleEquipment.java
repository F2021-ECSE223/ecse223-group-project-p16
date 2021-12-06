/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 32 "../../../../../ClimbSafeTransferObjects.ump"
public class TOBundleEquipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOBundleEquipment Attributes
  private String equipmentName;
  private int quantity;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOBundleEquipment(String aEquipmentName, int aQuantity)
  {
    equipmentName = aEquipmentName;
    quantity = aQuantity;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEquipmentName(String aEquipmentName)
  {
    boolean wasSet = false;
    equipmentName = aEquipmentName;
    wasSet = true;
    return wasSet;
  }

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public String getEquipmentName()
  {
    return equipmentName;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "equipmentName" + ":" + getEquipmentName()+ "," +
            "quantity" + ":" + getQuantity()+ "]";
  }
}