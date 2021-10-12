package ca.mcgill.ecse223.climbsafe.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 45 "slide10.ump"
// line 135 "slide10.ump"
// line 206 "slide10.ump"
public class ClimbingWeek
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbingWeek Attributes
  private int weekNumber;
  private Date weekStartDate;
  private Date weekEndDate;

  //ClimbingWeek Associations
  private ClimbSafe climbSafe;
  private ClimbingSeason season;
  private List<ClimbAssignment> assignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbingWeek(int aWeekNumber, Date aWeekStartDate, Date aWeekEndDate, ClimbSafe aClimbSafe, ClimbingSeason aSeason)
  {
    weekNumber = aWeekNumber;
    weekStartDate = aWeekStartDate;
    weekEndDate = aWeekEndDate;
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create climbingWeek due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddSeason = setSeason(aSeason);
    if (!didAddSeason)
    {
      throw new RuntimeException("Unable to create climbingWeek due to season. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    assignments = new ArrayList<ClimbAssignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWeekNumber(int aWeekNumber)
  {
    boolean wasSet = false;
    weekNumber = aWeekNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeekStartDate(Date aWeekStartDate)
  {
    boolean wasSet = false;
    weekStartDate = aWeekStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeekEndDate(Date aWeekEndDate)
  {
    boolean wasSet = false;
    weekEndDate = aWeekEndDate;
    wasSet = true;
    return wasSet;
  }

  public int getWeekNumber()
  {
    return weekNumber;
  }

  /**
   * this is a derived attribute from weekNumber
   */
  public Date getWeekStartDate()
  {
    return weekStartDate;
  }

  /**
   * this is a derived attribute from weekNumber
   */
  public Date getWeekEndDate()
  {
    return weekEndDate;
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
      existingClimbSafe.removeClimbingWeek(this);
    }
    climbSafe.addClimbingWeek(this);
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
      existingSeason.removeClimbingWeek(this);
    }
    season.addClimbingWeek(this);
    wasSet = true;
    return wasSet;
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
    if (aAssignment.indexOfClimbingWeek(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAssignment.addClimbingWeek(this);
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
    if (aAssignment.indexOfClimbingWeek(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAssignment.removeClimbingWeek(this);
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
      placeholderClimbSafe.removeClimbingWeek(this);
    }
    ClimbingSeason placeholderSeason = season;
    this.season = null;
    if(placeholderSeason != null)
    {
      placeholderSeason.removeClimbingWeek(this);
    }
    ArrayList<ClimbAssignment> copyOfAssignments = new ArrayList<ClimbAssignment>(assignments);
    assignments.clear();
    for(ClimbAssignment aAssignment : copyOfAssignments)
    {
      if (aAssignment.numberOfClimbingWeeks() <= ClimbAssignment.minimumNumberOfClimbingWeeks())
      {
        aAssignment.delete();
      }
      else
      {
        aAssignment.removeClimbingWeek(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "weekNumber" + ":" + getWeekNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "weekStartDate" + "=" + (getWeekStartDate() != null ? !getWeekStartDate().equals(this)  ? getWeekStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "weekEndDate" + "=" + (getWeekEndDate() != null ? !getWeekEndDate().equals(this)  ? getWeekEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "season = "+(getSeason()!=null?Integer.toHexString(System.identityHashCode(getSeason())):"null");
  }
}