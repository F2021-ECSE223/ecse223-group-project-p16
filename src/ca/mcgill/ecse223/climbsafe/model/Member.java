package ca.mcgill.ecse223.climbsafe.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 28 "slide10.ump"
// line 125 "slide10.ump"
// line 196 "slide10.ump"
public class Member extends AccountType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member Attributes
  private String name;
  private boolean hireClimbGuide;
  private int numberOfWeeks;
  private boolean stayBeforeOrAfter;
  private int emergencyContact;

  //Member Associations
  private ClimbSafe climbSafe;
  private ClimbAssignment assignment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(String aName, boolean aHireClimbGuide, int aNumberOfWeeks, boolean aStayBeforeOrAfter, int aEmergencyContact, ClimbSafe aClimbSafe, ClimbAssignment aAssignment)
  {
    super();
    name = aName;
    hireClimbGuide = aHireClimbGuide;
    numberOfWeeks = aNumberOfWeeks;
    stayBeforeOrAfter = aStayBeforeOrAfter;
    emergencyContact = aEmergencyContact;
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create member due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aAssignment == null || aAssignment.getMember() != null)
    {
      throw new RuntimeException("Unable to create Member due to aAssignment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    assignment = aAssignment;
  }

  public Member(String aName, boolean aHireClimbGuide, int aNumberOfWeeks, boolean aStayBeforeOrAfter, int aEmergencyContact, ClimbSafe aClimbSafe, ClimbingSeason aSeasonForAssignment, ClimbSafe aClimbSafeForAssignment, Administrator aAdministratorForAssignment, ClimbingWeek... allClimbingWeeksForAssignment)
  {
    super();
    name = aName;
    hireClimbGuide = aHireClimbGuide;
    numberOfWeeks = aNumberOfWeeks;
    stayBeforeOrAfter = aStayBeforeOrAfter;
    emergencyContact = aEmergencyContact;
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create member due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    assignment = new ClimbAssignment(this, aSeasonForAssignment, aClimbSafeForAssignment, aAdministratorForAssignment, allClimbingWeeksForAssignment);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setHireClimbGuide(boolean aHireClimbGuide)
  {
    boolean wasSet = false;
    hireClimbGuide = aHireClimbGuide;
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

  public boolean setStayBeforeOrAfter(boolean aStayBeforeOrAfter)
  {
    boolean wasSet = false;
    stayBeforeOrAfter = aStayBeforeOrAfter;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmergencyContact(int aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public boolean getHireClimbGuide()
  {
    return hireClimbGuide;
  }

  public int getNumberOfWeeks()
  {
    return numberOfWeeks;
  }

  public boolean getStayBeforeOrAfter()
  {
    return stayBeforeOrAfter;
  }

  public int getEmergencyContact()
  {
    return emergencyContact;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_GetOne */
  public ClimbAssignment getAssignment()
  {
    return assignment;
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
      existingClimbSafe.removeMember(this);
    }
    climbSafe.addMember(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeMember(this);
    }
    ClimbAssignment existingAssignment = assignment;
    assignment = null;
    if (existingAssignment != null)
    {
      existingAssignment.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "hireClimbGuide" + ":" + getHireClimbGuide()+ "," +
            "numberOfWeeks" + ":" + getNumberOfWeeks()+ "," +
            "stayBeforeOrAfter" + ":" + getStayBeforeOrAfter()+ "," +
            "emergencyContact" + ":" + getEmergencyContact()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignment = "+(getAssignment()!=null?Integer.toHexString(System.identityHashCode(getAssignment())):"null");
  }
}