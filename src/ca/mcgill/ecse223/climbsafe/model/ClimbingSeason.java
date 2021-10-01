package ca.mcgill.ecse223.climbsafe.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 54 "slide10.ump"
// line 140 "slide10.ump"
// line 211 "slide10.ump"
public class ClimbingSeason
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbingSeason Attributes
  private Date seasonStartDate;
  private Date seasonEndDate;
  private int numberOfWeeks;
  private int guideWeeklyPrice;

  //ClimbingSeason Associations
  private List<ClimbingWeek> climbingWeeks;
  private List<Equipment> availableEquipment;
  private ClimbSafe climbSafe;
  private Administrator administrator;
  private List<Bundle> availableBundles;
  private List<ClimbAssignment> assignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbingSeason(Date aSeasonStartDate, Date aSeasonEndDate, int aNumberOfWeeks, int aGuideWeeklyPrice, ClimbSafe aClimbSafe, Administrator aAdministrator)
  {
    seasonStartDate = aSeasonStartDate;
    seasonEndDate = aSeasonEndDate;
    numberOfWeeks = aNumberOfWeeks;
    guideWeeklyPrice = aGuideWeeklyPrice;
    climbingWeeks = new ArrayList<ClimbingWeek>();
    availableEquipment = new ArrayList<Equipment>();
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create seasonInfo due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAdministrator = setAdministrator(aAdministrator);
    if (!didAddAdministrator)
    {
      throw new RuntimeException("Unable to create seasonInfo due to administrator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    availableBundles = new ArrayList<Bundle>();
    assignments = new ArrayList<ClimbAssignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSeasonStartDate(Date aSeasonStartDate)
  {
    boolean wasSet = false;
    seasonStartDate = aSeasonStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setSeasonEndDate(Date aSeasonEndDate)
  {
    boolean wasSet = false;
    seasonEndDate = aSeasonEndDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfWeeks(int aNumberOfWeeks)
  {
    boolean wasSet = false;
    numberOfWeeks = aNumberOfWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuideWeeklyPrice(int aGuideWeeklyPrice)
  {
    boolean wasSet = false;
    guideWeeklyPrice = aGuideWeeklyPrice;
    wasSet = true;
    return wasSet;
  }

  public Date getSeasonStartDate()
  {
    return seasonStartDate;
  }

  public Date getSeasonEndDate()
  {
    return seasonEndDate;
  }

  /**
   * this is a derived attribute from seasonStartDate and seas
   */
  public int getNumberOfWeeks()
  {
    return numberOfWeeks;
  }

  public int getGuideWeeklyPrice()
  {
    return guideWeeklyPrice;
  }
  /* Code from template association_GetMany */
  public ClimbingWeek getClimbingWeek(int index)
  {
    ClimbingWeek aClimbingWeek = climbingWeeks.get(index);
    return aClimbingWeek;
  }

  public List<ClimbingWeek> getClimbingWeeks()
  {
    List<ClimbingWeek> newClimbingWeeks = Collections.unmodifiableList(climbingWeeks);
    return newClimbingWeeks;
  }

  public int numberOfClimbingWeeks()
  {
    int number = climbingWeeks.size();
    return number;
  }

  public boolean hasClimbingWeeks()
  {
    boolean has = climbingWeeks.size() > 0;
    return has;
  }

  public int indexOfClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    int index = climbingWeeks.indexOf(aClimbingWeek);
    return index;
  }
  /* Code from template association_GetMany */
  public Equipment getAvailableEquipment(int index)
  {
    Equipment aAvailableEquipment = availableEquipment.get(index);
    return aAvailableEquipment;
  }

  public List<Equipment> getAvailableEquipment()
  {
    List<Equipment> newAvailableEquipment = Collections.unmodifiableList(availableEquipment);
    return newAvailableEquipment;
  }

  public int numberOfAvailableEquipment()
  {
    int number = availableEquipment.size();
    return number;
  }

  public boolean hasAvailableEquipment()
  {
    boolean has = availableEquipment.size() > 0;
    return has;
  }

  public int indexOfAvailableEquipment(Equipment aAvailableEquipment)
  {
    int index = availableEquipment.indexOf(aAvailableEquipment);
    return index;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_GetOne */
  public Administrator getAdministrator()
  {
    return administrator;
  }
  /* Code from template association_GetMany */
  public Bundle getAvailableBundle(int index)
  {
    Bundle aAvailableBundle = availableBundles.get(index);
    return aAvailableBundle;
  }

  public List<Bundle> getAvailableBundles()
  {
    List<Bundle> newAvailableBundles = Collections.unmodifiableList(availableBundles);
    return newAvailableBundles;
  }

  public int numberOfAvailableBundles()
  {
    int number = availableBundles.size();
    return number;
  }

  public boolean hasAvailableBundles()
  {
    boolean has = availableBundles.size() > 0;
    return has;
  }

  public int indexOfAvailableBundle(Bundle aAvailableBundle)
  {
    int index = availableBundles.indexOf(aAvailableBundle);
    return index;
  }
  /* Code from template association_GetMany */
  public ClimbAssignment getAssignment(int index)
  {
    ClimbAssignment aAssignment = assignments.get(index);
    return aAssignment;
  }

  public List<ClimbAssignment> getAssignments()
  {
    List<ClimbAssignment> newAssignments = Collections.unmodifiableList(assignments);
    return newAssignments;
  }

  public int numberOfAssignments()
  {
    int number = assignments.size();
    return number;
  }

  public boolean hasAssignments()
  {
    boolean has = assignments.size() > 0;
    return has;
  }

  public int indexOfAssignment(ClimbAssignment aAssignment)
  {
    int index = assignments.indexOf(aAssignment);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClimbingWeeks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ClimbingWeek addClimbingWeek(int aWeekNumber, Date aWeekStartDate, Date aWeekEndDate, ClimbSafe aClimbSafe)
  {
    return new ClimbingWeek(aWeekNumber, aWeekStartDate, aWeekEndDate, aClimbSafe, this);
  }

  public boolean addClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasAdded = false;
    if (climbingWeeks.contains(aClimbingWeek)) { return false; }
    ClimbingSeason existingSeason = aClimbingWeek.getSeason();
    boolean isNewSeason = existingSeason != null && !this.equals(existingSeason);
    if (isNewSeason)
    {
      aClimbingWeek.setSeason(this);
    }
    else
    {
      climbingWeeks.add(aClimbingWeek);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasRemoved = false;
    //Unable to remove aClimbingWeek, as it must always have a season
    if (!this.equals(aClimbingWeek.getSeason()))
    {
      climbingWeeks.remove(aClimbingWeek);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addClimbingWeekAt(ClimbingWeek aClimbingWeek, int index)
  {  
    boolean wasAdded = false;
    if(addClimbingWeek(aClimbingWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbingWeeks()) { index = numberOfClimbingWeeks() - 1; }
      climbingWeeks.remove(aClimbingWeek);
      climbingWeeks.add(index, aClimbingWeek);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveClimbingWeekAt(ClimbingWeek aClimbingWeek, int index)
  {
    boolean wasAdded = false;
    if(climbingWeeks.contains(aClimbingWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbingWeeks()) { index = numberOfClimbingWeeks() - 1; }
      climbingWeeks.remove(aClimbingWeek);
      climbingWeeks.add(index, aClimbingWeek);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addClimbingWeekAt(aClimbingWeek, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAvailableEquipment()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Equipment addAvailableEquipment(String aToolName, double aWeight, int aCost, ClimbSafe aClimbSafe)
  {
    return new Equipment(aToolName, aWeight, aCost, aClimbSafe, this);
  }

  public boolean addAvailableEquipment(Equipment aAvailableEquipment)
  {
    boolean wasAdded = false;
    if (availableEquipment.contains(aAvailableEquipment)) { return false; }
    ClimbingSeason existingSeason = aAvailableEquipment.getSeason();
    boolean isNewSeason = existingSeason != null && !this.equals(existingSeason);
    if (isNewSeason)
    {
      aAvailableEquipment.setSeason(this);
    }
    else
    {
      availableEquipment.add(aAvailableEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAvailableEquipment(Equipment aAvailableEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAvailableEquipment, as it must always have a season
    if (!this.equals(aAvailableEquipment.getSeason()))
    {
      availableEquipment.remove(aAvailableEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAvailableEquipmentAt(Equipment aAvailableEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addAvailableEquipment(aAvailableEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAvailableEquipment()) { index = numberOfAvailableEquipment() - 1; }
      availableEquipment.remove(aAvailableEquipment);
      availableEquipment.add(index, aAvailableEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAvailableEquipmentAt(Equipment aAvailableEquipment, int index)
  {
    boolean wasAdded = false;
    if(availableEquipment.contains(aAvailableEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAvailableEquipment()) { index = numberOfAvailableEquipment() - 1; }
      availableEquipment.remove(aAvailableEquipment);
      availableEquipment.add(index, aAvailableEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAvailableEquipmentAt(aAvailableEquipment, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setClimbSafe(ClimbSafe aNewClimbSafe)
  {
    boolean wasSet = false;
    if (aNewClimbSafe == null)
    {
      //Unable to setClimbSafe to null, as seasonInfo must always be associated to a climbSafe
      return wasSet;
    }
    
    ClimbingSeason existingSeasonInfo = aNewClimbSafe.getSeasonInfo();
    if (existingSeasonInfo != null && !equals(existingSeasonInfo))
    {
      //Unable to setClimbSafe, the current climbSafe already has a seasonInfo, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    ClimbSafe anOldClimbSafe = climbSafe;
    climbSafe = aNewClimbSafe;
    climbSafe.setSeasonInfo(this);

    if (anOldClimbSafe != null)
    {
      anOldClimbSafe.setSeasonInfo(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setAdministrator(Administrator aNewAdministrator)
  {
    boolean wasSet = false;
    if (aNewAdministrator == null)
    {
      //Unable to setAdministrator to null, as seasonInfo must always be associated to a administrator
      return wasSet;
    }
    
    ClimbingSeason existingSeasonInfo = aNewAdministrator.getSeasonInfo();
    if (existingSeasonInfo != null && !equals(existingSeasonInfo))
    {
      //Unable to setAdministrator, the current administrator already has a seasonInfo, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Administrator anOldAdministrator = administrator;
    administrator = aNewAdministrator;
    administrator.setSeasonInfo(this);

    if (anOldAdministrator != null)
    {
      anOldAdministrator.setSeasonInfo(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAvailableBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Bundle addAvailableBundle(int aPercentDiscount, String aBundleName, ClimbSafe aClimbSafe, Equipment... allEquipment)
  {
    return new Bundle(aPercentDiscount, aBundleName, this, aClimbSafe, allEquipment);
  }

  public boolean addAvailableBundle(Bundle aAvailableBundle)
  {
    boolean wasAdded = false;
    if (availableBundles.contains(aAvailableBundle)) { return false; }
    ClimbingSeason existingSeason = aAvailableBundle.getSeason();
    boolean isNewSeason = existingSeason != null && !this.equals(existingSeason);
    if (isNewSeason)
    {
      aAvailableBundle.setSeason(this);
    }
    else
    {
      availableBundles.add(aAvailableBundle);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAvailableBundle(Bundle aAvailableBundle)
  {
    boolean wasRemoved = false;
    //Unable to remove aAvailableBundle, as it must always have a season
    if (!this.equals(aAvailableBundle.getSeason()))
    {
      availableBundles.remove(aAvailableBundle);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAvailableBundleAt(Bundle aAvailableBundle, int index)
  {  
    boolean wasAdded = false;
    if(addAvailableBundle(aAvailableBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAvailableBundles()) { index = numberOfAvailableBundles() - 1; }
      availableBundles.remove(aAvailableBundle);
      availableBundles.add(index, aAvailableBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAvailableBundleAt(Bundle aAvailableBundle, int index)
  {
    boolean wasAdded = false;
    if(availableBundles.contains(aAvailableBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAvailableBundles()) { index = numberOfAvailableBundles() - 1; }
      availableBundles.remove(aAvailableBundle);
      availableBundles.add(index, aAvailableBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAvailableBundleAt(aAvailableBundle, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ClimbAssignment addAssignment(Member aMember, ClimbSafe aClimbSafe, Administrator aAdministrator, ClimbingWeek... allClimbingWeeks)
  {
    return new ClimbAssignment(aMember, this, aClimbSafe, aAdministrator, allClimbingWeeks);
  }

  public boolean addAssignment(ClimbAssignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    ClimbingSeason existingSeason = aAssignment.getSeason();
    boolean isNewSeason = existingSeason != null && !this.equals(existingSeason);
    if (isNewSeason)
    {
      aAssignment.setSeason(this);
    }
    else
    {
      assignments.add(aAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignment(ClimbAssignment aAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAssignment, as it must always have a season
    if (!this.equals(aAssignment.getSeason()))
    {
      assignments.remove(aAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignmentAt(ClimbAssignment aAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addAssignment(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignmentAt(ClimbAssignment aAssignment, int index)
  {
    boolean wasAdded = false;
    if(assignments.contains(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignmentAt(aAssignment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=climbingWeeks.size(); i > 0; i--)
    {
      ClimbingWeek aClimbingWeek = climbingWeeks.get(i - 1);
      aClimbingWeek.delete();
    }
    for(int i=availableEquipment.size(); i > 0; i--)
    {
      Equipment aAvailableEquipment = availableEquipment.get(i - 1);
      aAvailableEquipment.delete();
    }
    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = null;
    if (existingClimbSafe != null)
    {
      existingClimbSafe.setSeasonInfo(null);
    }
    Administrator existingAdministrator = administrator;
    administrator = null;
    if (existingAdministrator != null)
    {
      existingAdministrator.setSeasonInfo(null);
    }
    for(int i=availableBundles.size(); i > 0; i--)
    {
      Bundle aAvailableBundle = availableBundles.get(i - 1);
      aAvailableBundle.delete();
    }
    for(int i=assignments.size(); i > 0; i--)
    {
      ClimbAssignment aAssignment = assignments.get(i - 1);
      aAssignment.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "numberOfWeeks" + ":" + getNumberOfWeeks()+ "," +
            "guideWeeklyPrice" + ":" + getGuideWeeklyPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "seasonStartDate" + "=" + (getSeasonStartDate() != null ? !getSeasonStartDate().equals(this)  ? getSeasonStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "seasonEndDate" + "=" + (getSeasonEndDate() != null ? !getSeasonEndDate().equals(this)  ? getSeasonEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "administrator = "+(getAdministrator()!=null?Integer.toHexString(System.identityHashCode(getAdministrator())):"null");
  }
}