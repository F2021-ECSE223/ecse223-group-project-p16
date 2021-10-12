package ca.mcgill.ecse223.climbsafe.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 21 "slide10.ump"
// line 115 "slide10.ump"
// line 191 "slide10.ump"
public class Administrator extends AccountType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Administrator Associations
  private List<ClimbAssignment> assignments;
  private ClimbingSeason seasonInfo;
  private ClimbSafe climbSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Administrator(ClimbSafe aClimbSafe)
  {
    super();
    assignments = new ArrayList<ClimbAssignment>();
    if (aClimbSafe == null || aClimbSafe.getAdmin() != null)
    {
      throw new RuntimeException("Unable to create Administrator due to aClimbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climbSafe = aClimbSafe;
  }

  public Administrator()
  {
    super();
    assignments = new ArrayList<ClimbAssignment>();
    climbSafe = new ClimbSafe(this);
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  /* Code from template association_GetOne */
  public ClimbingSeason getSeasonInfo()
  {
    return seasonInfo;
  }

  public boolean hasSeasonInfo()
  {
    boolean has = seasonInfo != null;
    return has;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ClimbAssignment addAssignment(Member aMember, ClimbingSeason aSeason, ClimbSafe aClimbSafe, ClimbingWeek... allClimbingWeeks)
  {
    return new ClimbAssignment(aMember, aSeason, aClimbSafe, this, allClimbingWeeks);
  }

  public boolean addAssignment(ClimbAssignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    Administrator existingAdministrator = aAssignment.getAdministrator();
    boolean isNewAdministrator = existingAdministrator != null && !this.equals(existingAdministrator);
    if (isNewAdministrator)
    {
      aAssignment.setAdministrator(this);
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
    //Unable to remove aAssignment, as it must always have a administrator
    if (!this.equals(aAssignment.getAdministrator()))
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
  /* Code from template association_SetOptionalOneToOne */
  public boolean setSeasonInfo(ClimbingSeason aNewSeasonInfo)
  {
    boolean wasSet = false;
    if (seasonInfo != null && !seasonInfo.equals(aNewSeasonInfo) && equals(seasonInfo.getAdministrator()))
    {
      //Unable to setSeasonInfo, as existing seasonInfo would become an orphan
      return wasSet;
    }

    seasonInfo = aNewSeasonInfo;
    Administrator anOldAdministrator = aNewSeasonInfo != null ? aNewSeasonInfo.getAdministrator() : null;

    if (!this.equals(anOldAdministrator))
    {
      if (anOldAdministrator != null)
      {
        anOldAdministrator.seasonInfo = null;
      }
      if (seasonInfo != null)
      {
        seasonInfo.setAdministrator(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=assignments.size(); i > 0; i--)
    {
      ClimbAssignment aAssignment = assignments.get(i - 1);
      aAssignment.delete();
    }
    ClimbingSeason existingSeasonInfo = seasonInfo;
    seasonInfo = null;
    if (existingSeasonInfo != null)
    {
      existingSeasonInfo.delete();
    }
    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = null;
    if (existingClimbSafe != null)
    {
      existingClimbSafe.delete();
    }
    super.delete();
  }

}