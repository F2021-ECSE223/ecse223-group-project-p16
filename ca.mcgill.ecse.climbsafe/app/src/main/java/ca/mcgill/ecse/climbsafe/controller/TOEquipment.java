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
  private String equipmentName;
  private int weight;
  private int pricePerWeek;


  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOEquipment(String aEquipmentName, int aWeight, int aPricePerWeek)
  {
    equipmentName = aEquipmentName;
    weight = aWeight;
    pricePerWeek = aPricePerWeek;
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
  
  public boolean setWeight(int aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setPricePerWeek(int aPricePerWeek)
  {
    boolean wasSet = false;
    pricePerWeek = aPricePerWeek;
    wasSet = true;
    return wasSet;
  }

  public String getEquipmentName()
  {
    return equipmentName;
  }

  public int getWeight()
  {
    return weight;
  }

  public int getPricePerWeek()
  {
    return pricePerWeek;

  }

  public void delete()
  {}


  @Override
  // line 20 "../../../../../ClimbSafeTransferObjects.ump"
   public String toString(){
    return equipmentName;
  }

}