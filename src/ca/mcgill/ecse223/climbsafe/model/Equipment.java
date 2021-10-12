package ca.mcgill.ecse223.climbsafe.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 65 "slide10.ump"
// line 149 "slide10.ump"
// line 216 "slide10.ump"
public class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private String toolName;
  private double weight;
  private int cost;

  //Equipment Associations
  private ClimbSafe climbSafe;
  private ClimbingSeason season;
  private List<Bundle> bundles;
  private List<ClimbAssignment> assignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(String aToolName, double aWeight, int aCost, ClimbSafe aClimbSafe, ClimbingSeason aSeason)
  {
    toolName = aToolName;
    weight = aWeight;
    cost = aCost;
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create equipment due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddSeason = setSeason(aSeason);
    if (!didAddSeason)
    {
      throw new RuntimeException("Unable to create availableEquipment due to season. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bundles = new ArrayList<Bundle>();
    assignments = new ArrayList<ClimbAssignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setToolName(String aToolName)
  {
    boolean wasSet = false;
    toolName = aToolName;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeight(double aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setCost(int aCost)
  {
    boolean wasSet = false;
    cost = aCost;
    wasSet = true;
    return wasSet;
  }

  public String getToolName()
  {
    return toolName;
  }

  public double getWeight()
  {
    return weight;
  }

  public int getCost()
  {
    return cost;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_GetOne */
  public ClimbingSeason getSeason()
  {
    return season;
  }
  /* Code from template association_GetMany */
  public Bundle getBundle(int index)
  {
    Bundle aBundle = bundles.get(index);
    return aBundle;
  }

  public List<Bundle> getBundles()
  {
    List<Bundle> newBundles = Collections.unmodifiableList(bundles);
    return newBundles;
  }

  public int numberOfBundles()
  {
    int number = bundles.size();
    return number;
  }

  public boolean hasBundles()
  {
    boolean has = bundles.size() > 0;
    return has;
  }

  public int indexOfBundle(Bundle aBundle)
  {
    int index = bundles.indexOf(aBundle);
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
  /* Code from template association_SetOneToMany */
  public boolean setClimbSafe(ClimbSafe aClimbSafe)
  {
    boolean wasSet = false;
    if (aClimbSafe == null)
    {
      return wasSet;
    }

    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = aClimbSafe;
    if (existingClimbSafe != null && !existingClimbSafe.equals(aClimbSafe))
    {
      existingClimbSafe.removeEquipment(this);
    }
    climbSafe.addEquipment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSeason(ClimbingSeason aSeason)
  {
    boolean wasSet = false;
    if (aSeason == null)
    {
      return wasSet;
    }

    ClimbingSeason existingSeason = season;
    season = aSeason;
    if (existingSeason != null && !existingSeason.equals(aSeason))
    {
      existingSeason.removeAvailableEquipment(this);
    }
    season.addAvailableEquipment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addBundle(Bundle aBundle)
  {
    boolean wasAdded = false;
    if (bundles.contains(aBundle)) { return false; }
    bundles.add(aBundle);
    if (aBundle.indexOfEquipment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBundle.addEquipment(this);
      if (!wasAdded)
      {
        bundles.remove(aBundle);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeBundle(Bundle aBundle)
  {
    boolean wasRemoved = false;
    if (!bundles.contains(aBundle))
    {
      return wasRemoved;
    }

    int oldIndex = bundles.indexOf(aBundle);
    bundles.remove(oldIndex);
    if (aBundle.indexOfEquipment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBundle.removeEquipment(this);
      if (!wasRemoved)
      {
        bundles.add(oldIndex,aBundle);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBundleAt(Bundle aBundle, int index)
  {  
    boolean wasAdded = false;
    if(addBundle(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBundleAt(Bundle aBundle, int index)
  {
    boolean wasAdded = false;
    if(bundles.contains(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBundleAt(aBundle, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAssignment(ClimbAssignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    assignments.add(aAssignment);
    if (aAssignment.indexOfEquipment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAssignment.addEquipment(this);
      if (!wasAdded)
      {
        assignments.remove(aAssignment);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAssignment(ClimbAssignment aAssignment)
  {
    boolean wasRemoved = false;
    if (!assignments.contains(aAssignment))
    {
      return wasRemoved;
    }

    int oldIndex = assignments.indexOf(aAssignment);
    assignments.remove(oldIndex);
    if (aAssignment.indexOfEquipment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAssignment.removeEquipment(this);
      if (!wasRemoved)
      {
        assignments.add(oldIndex,aAssignment);
      }
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
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeEquipment(this);
    }
    ClimbingSeason placeholderSeason = season;
    this.season = null;
    if(placeholderSeason != null)
    {
      placeholderSeason.removeAvailableEquipment(this);
    }
    ArrayList<Bundle> copyOfBundles = new ArrayList<Bundle>(bundles);
    bundles.clear();
    for(Bundle aBundle : copyOfBundles)
    {
      if (aBundle.numberOfEquipment() <= Bundle.minimumNumberOfEquipment())
      {
        aBundle.delete();
      }
      else
      {
        aBundle.removeEquipment(this);
      }
    }
    ArrayList<ClimbAssignment> copyOfAssignments = new ArrayList<ClimbAssignment>(assignments);
    assignments.clear();
    for(ClimbAssignment aAssignment : copyOfAssignments)
    {
      aAssignment.removeEquipment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "toolName" + ":" + getToolName()+ "," +
            "weight" + ":" + getWeight()+ "," +
            "cost" + ":" + getCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "season = "+(getSeason()!=null?Integer.toHexString(System.identityHashCode(getSeason())):"null");
  }
}