/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 15 "../../../../../ClimbSafeTransferObjects.ump"
public class TOEquipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOEquipment Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOEquipment(String aName)
  {
    name = aName;
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

  public String getName() {
    return equipmentName;
  }

  public void delete()
  {}


  @Override
  // line 18 "../../../../../ClimbSafeTransferObjects.ump"
   public String toString(){
    return name;
  }

}