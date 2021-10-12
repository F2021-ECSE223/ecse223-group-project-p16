package ca.mcgill.ecse223.climbsafe.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 1 "slide10.ump"
public class ClimbSafe
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbSafe Associations
  private Administrator admin;
  private List<Member> members;
  private List<MountainGuide> mountainGuides;
  private List<ClimbingWeek> climbingWeeks;
  private ClimbingSeason seasonInfo;
  private List<Equipment> equipments;
  private List<Bundle> bundles;
  private List<ClimbAssignment> climbAssignments;
  private List<Hotel> hotels;
  private List<User> users;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbSafe(Administrator aAdmin)
  {
    if (aAdmin == null || aAdmin.getClimbSafe() != null)
    {
      throw new RuntimeException("Unable to create ClimbSafe due to aAdmin. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    admin = aAdmin;
    members = new ArrayList<Member>();
    mountainGuides = new ArrayList<MountainGuide>();
    climbingWeeks = new ArrayList<ClimbingWeek>();
    equipments = new ArrayList<Equipment>();
    bundles = new ArrayList<Bundle>();
    climbAssignments = new ArrayList<ClimbAssignment>();
    hotels = new ArrayList<Hotel>();
    users = new ArrayList<User>();
  }

  public ClimbSafe()
  {
    admin = new Administrator(this);
    members = new ArrayList<Member>();
    mountainGuides = new ArrayList<MountainGuide>();
    climbingWeeks = new ArrayList<ClimbingWeek>();
    equipments = new ArrayList<Equipment>();
    bundles = new ArrayList<Bundle>();
    climbAssignments = new ArrayList<ClimbAssignment>();
    hotels = new ArrayList<Hotel>();
    users = new ArrayList<User>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Administrator getAdmin()
  {
    return admin;
  }
  /* Code from template association_GetMany */
  public Member getMember(int index)
  {
    Member aMember = members.get(index);
    return aMember;
  }

  public List<Member> getMembers()
  {
    List<Member> newMembers = Collections.unmodifiableList(members);
    return newMembers;
  }

  public int numberOfMembers()
  {
    int number = members.size();
    return number;
  }

  public boolean hasMembers()
  {
    boolean has = members.size() > 0;
    return has;
  }

  public int indexOfMember(Member aMember)
  {
    int index = members.indexOf(aMember);
    return index;
  }
  /* Code from template association_GetMany */
  public MountainGuide getMountainGuide(int index)
  {
    MountainGuide aMountainGuide = mountainGuides.get(index);
    return aMountainGuide;
  }

  public List<MountainGuide> getMountainGuides()
  {
    List<MountainGuide> newMountainGuides = Collections.unmodifiableList(mountainGuides);
    return newMountainGuides;
  }

  public int numberOfMountainGuides()
  {
    int number = mountainGuides.size();
    return number;
  }

  public boolean hasMountainGuides()
  {
    boolean has = mountainGuides.size() > 0;
    return has;
  }

  public int indexOfMountainGuide(MountainGuide aMountainGuide)
  {
    int index = mountainGuides.indexOf(aMountainGuide);
    return index;
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
  /* Code from template association_GetMany */
  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipments.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipments()
  {
    List<Equipment> newEquipments = Collections.unmodifiableList(equipments);
    return newEquipments;
  }

  public int numberOfEquipments()
  {
    int number = equipments.size();
    return number;
  }

  public boolean hasEquipments()
  {
    boolean has = equipments.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipments.indexOf(aEquipment);
    return index;
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
  public ClimbAssignment getClimbAssignment(int index)
  {
    ClimbAssignment aClimbAssignment = climbAssignments.get(index);
    return aClimbAssignment;
  }

  public List<ClimbAssignment> getClimbAssignments()
  {
    List<ClimbAssignment> newClimbAssignments = Collections.unmodifiableList(climbAssignments);
    return newClimbAssignments;
  }

  public int numberOfClimbAssignments()
  {
    int number = climbAssignments.size();
    return number;
  }

  public boolean hasClimbAssignments()
  {
    boolean has = climbAssignments.size() > 0;
    return has;
  }

  public int indexOfClimbAssignment(ClimbAssignment aClimbAssignment)
  {
    int index = climbAssignments.indexOf(aClimbAssignment);
    return index;
  }
  /* Code from template association_GetMany */
  public Hotel getHotel(int index)
  {
    Hotel aHotel = hotels.get(index);
    return aHotel;
  }

  public List<Hotel> getHotels()
  {
    List<Hotel> newHotels = Collections.unmodifiableList(hotels);
    return newHotels;
  }

  public int numberOfHotels()
  {
    int number = hotels.size();
    return number;
  }

  public boolean hasHotels()
  {
    boolean has = hotels.size() > 0;
    return has;
  }

  public int indexOfHotel(Hotel aHotel)
  {
    int index = hotels.indexOf(aHotel);
    return index;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMembers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Member addMember(String aName, boolean aHireClimbGuide, int aNumberOfWeeks, boolean aStayBeforeOrAfter, int aEmergencyContact, ClimbAssignment aAssignment)
  {
    return new Member(aName, aHireClimbGuide, aNumberOfWeeks, aStayBeforeOrAfter, aEmergencyContact, this, aAssignment);
  }

  public boolean addMember(Member aMember)
  {
    boolean wasAdded = false;
    if (members.contains(aMember)) { return false; }
    ClimbSafe existingClimbSafe = aMember.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aMember.setClimbSafe(this);
    }
    else
    {
      members.add(aMember);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMember(Member aMember)
  {
    boolean wasRemoved = false;
    //Unable to remove aMember, as it must always have a climbSafe
    if (!this.equals(aMember.getClimbSafe()))
    {
      members.remove(aMember);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMemberAt(Member aMember, int index)
  {  
    boolean wasAdded = false;
    if(addMember(aMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMembers()) { index = numberOfMembers() - 1; }
      members.remove(aMember);
      members.add(index, aMember);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMemberAt(Member aMember, int index)
  {
    boolean wasAdded = false;
    if(members.contains(aMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMembers()) { index = numberOfMembers() - 1; }
      members.remove(aMember);
      members.add(index, aMember);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMemberAt(aMember, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMountainGuides()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MountainGuide addMountainGuide(String aName, int aEmergencyContact)
  {
    return new MountainGuide(aName, aEmergencyContact, this);
  }

  public boolean addMountainGuide(MountainGuide aMountainGuide)
  {
    boolean wasAdded = false;
    if (mountainGuides.contains(aMountainGuide)) { return false; }
    ClimbSafe existingClimbSafe = aMountainGuide.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aMountainGuide.setClimbSafe(this);
    }
    else
    {
      mountainGuides.add(aMountainGuide);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMountainGuide(MountainGuide aMountainGuide)
  {
    boolean wasRemoved = false;
    //Unable to remove aMountainGuide, as it must always have a climbSafe
    if (!this.equals(aMountainGuide.getClimbSafe()))
    {
      mountainGuides.remove(aMountainGuide);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMountainGuideAt(MountainGuide aMountainGuide, int index)
  {  
    boolean wasAdded = false;
    if(addMountainGuide(aMountainGuide))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMountainGuides()) { index = numberOfMountainGuides() - 1; }
      mountainGuides.remove(aMountainGuide);
      mountainGuides.add(index, aMountainGuide);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMountainGuideAt(MountainGuide aMountainGuide, int index)
  {
    boolean wasAdded = false;
    if(mountainGuides.contains(aMountainGuide))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMountainGuides()) { index = numberOfMountainGuides() - 1; }
      mountainGuides.remove(aMountainGuide);
      mountainGuides.add(index, aMountainGuide);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMountainGuideAt(aMountainGuide, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClimbingWeeks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ClimbingWeek addClimbingWeek(int aWeekNumber, Date aWeekStartDate, Date aWeekEndDate, ClimbingSeason aSeason)
  {
    return new ClimbingWeek(aWeekNumber, aWeekStartDate, aWeekEndDate, this, aSeason);
  }

  public boolean addClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasAdded = false;
    if (climbingWeeks.contains(aClimbingWeek)) { return false; }
    ClimbSafe existingClimbSafe = aClimbingWeek.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aClimbingWeek.setClimbSafe(this);
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
    //Unable to remove aClimbingWeek, as it must always have a climbSafe
    if (!this.equals(aClimbingWeek.getClimbSafe()))
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
  /* Code from template association_SetOptionalOneToOne */
  public boolean setSeasonInfo(ClimbingSeason aNewSeasonInfo)
  {
    boolean wasSet = false;
    if (seasonInfo != null && !seasonInfo.equals(aNewSeasonInfo) && equals(seasonInfo.getClimbSafe()))
    {
      //Unable to setSeasonInfo, as existing seasonInfo would become an orphan
      return wasSet;
    }

    seasonInfo = aNewSeasonInfo;
    ClimbSafe anOldClimbSafe = aNewSeasonInfo != null ? aNewSeasonInfo.getClimbSafe() : null;

    if (!this.equals(anOldClimbSafe))
    {
      if (anOldClimbSafe != null)
      {
        anOldClimbSafe.seasonInfo = null;
      }
      if (seasonInfo != null)
      {
        seasonInfo.setClimbSafe(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Equipment addEquipment(String aToolName, double aWeight, int aCost, ClimbingSeason aSeason)
  {
    return new Equipment(aToolName, aWeight, aCost, this, aSeason);
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipments.contains(aEquipment)) { return false; }
    ClimbSafe existingClimbSafe = aEquipment.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aEquipment.setClimbSafe(this);
    }
    else
    {
      equipments.add(aEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aEquipment, as it must always have a climbSafe
    if (!this.equals(aEquipment.getClimbSafe()))
    {
      equipments.remove(aEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipments.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Bundle addBundle(int aPercentDiscount, String aBundleName, ClimbingSeason aSeason, Equipment... allEquipment)
  {
    return new Bundle(aPercentDiscount, aBundleName, aSeason, this, allEquipment);
  }

  public boolean addBundle(Bundle aBundle)
  {
    boolean wasAdded = false;
    if (bundles.contains(aBundle)) { return false; }
    ClimbSafe existingClimbSafe = aBundle.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aBundle.setClimbSafe(this);
    }
    else
    {
      bundles.add(aBundle);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBundle(Bundle aBundle)
  {
    boolean wasRemoved = false;
    //Unable to remove aBundle, as it must always have a climbSafe
    if (!this.equals(aBundle.getClimbSafe()))
    {
      bundles.remove(aBundle);
      wasRemoved = true;
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
  public static int minimumNumberOfClimbAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ClimbAssignment addClimbAssignment(Member aMember, ClimbingSeason aSeason, Administrator aAdministrator, ClimbingWeek... allClimbingWeeks)
  {
    return new ClimbAssignment(aMember, aSeason, this, aAdministrator, allClimbingWeeks);
  }

  public boolean addClimbAssignment(ClimbAssignment aClimbAssignment)
  {
    boolean wasAdded = false;
    if (climbAssignments.contains(aClimbAssignment)) { return false; }
    ClimbSafe existingClimbSafe = aClimbAssignment.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aClimbAssignment.setClimbSafe(this);
    }
    else
    {
      climbAssignments.add(aClimbAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeClimbAssignment(ClimbAssignment aClimbAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aClimbAssignment, as it must always have a climbSafe
    if (!this.equals(aClimbAssignment.getClimbSafe()))
    {
      climbAssignments.remove(aClimbAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addClimbAssignmentAt(ClimbAssignment aClimbAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addClimbAssignment(aClimbAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbAssignments()) { index = numberOfClimbAssignments() - 1; }
      climbAssignments.remove(aClimbAssignment);
      climbAssignments.add(index, aClimbAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveClimbAssignmentAt(ClimbAssignment aClimbAssignment, int index)
  {
    boolean wasAdded = false;
    if(climbAssignments.contains(aClimbAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbAssignments()) { index = numberOfClimbAssignments() - 1; }
      climbAssignments.remove(aClimbAssignment);
      climbAssignments.add(index, aClimbAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addClimbAssignmentAt(aClimbAssignment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHotels()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Hotel addHotel(String aName, String aAddress, int aStars)
  {
    return new Hotel(aName, aAddress, aStars, this);
  }

  public boolean addHotel(Hotel aHotel)
  {
    boolean wasAdded = false;
    if (hotels.contains(aHotel)) { return false; }
    ClimbSafe existingClimbSafe = aHotel.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aHotel.setClimbSafe(this);
    }
    else
    {
      hotels.add(aHotel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHotel(Hotel aHotel)
  {
    boolean wasRemoved = false;
    //Unable to remove aHotel, as it must always have a climbSafe
    if (!this.equals(aHotel.getClimbSafe()))
    {
      hotels.remove(aHotel);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHotelAt(Hotel aHotel, int index)
  {  
    boolean wasAdded = false;
    if(addHotel(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHotelAt(Hotel aHotel, int index)
  {
    boolean wasAdded = false;
    if(hotels.contains(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHotelAt(aHotel, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfUsersValid()
  {
    boolean isValid = numberOfUsers() >= minimumNumberOfUsers();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public User addUser(String aEmail, String aPassword, AccountType aAccountType)
  {
    User aNewUser = new User(aEmail, aPassword, this, aAccountType);
    return aNewUser;
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    ClimbSafe existingClimbSafe = aUser.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);

    if (isNewClimbSafe && existingClimbSafe.numberOfUsers() <= minimumNumberOfUsers())
    {
      return wasAdded;
    }
    if (isNewClimbSafe)
    {
      aUser.setClimbSafe(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a climbSafe
    if (this.equals(aUser.getClimbSafe()))
    {
      return wasRemoved;
    }

    //climbSafe already at minimum (1)
    if (numberOfUsers() <= minimumNumberOfUsers())
    {
      return wasRemoved;
    }

    users.remove(aUser);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Administrator existingAdmin = admin;
    admin = null;
    if (existingAdmin != null)
    {
      existingAdmin.delete();
    }
    while (members.size() > 0)
    {
      Member aMember = members.get(members.size() - 1);
      aMember.delete();
      members.remove(aMember);
    }
    
    while (mountainGuides.size() > 0)
    {
      MountainGuide aMountainGuide = mountainGuides.get(mountainGuides.size() - 1);
      aMountainGuide.delete();
      mountainGuides.remove(aMountainGuide);
    }
    
    while (climbingWeeks.size() > 0)
    {
      ClimbingWeek aClimbingWeek = climbingWeeks.get(climbingWeeks.size() - 1);
      aClimbingWeek.delete();
      climbingWeeks.remove(aClimbingWeek);
    }
    
    ClimbingSeason existingSeasonInfo = seasonInfo;
    seasonInfo = null;
    if (existingSeasonInfo != null)
    {
      existingSeasonInfo.delete();
      existingSeasonInfo.setClimbSafe(null);
    }
    while (equipments.size() > 0)
    {
      Equipment aEquipment = equipments.get(equipments.size() - 1);
      aEquipment.delete();
      equipments.remove(aEquipment);
    }
    
    while (bundles.size() > 0)
    {
      Bundle aBundle = bundles.get(bundles.size() - 1);
      aBundle.delete();
      bundles.remove(aBundle);
    }
    
    while (climbAssignments.size() > 0)
    {
      ClimbAssignment aClimbAssignment = climbAssignments.get(climbAssignments.size() - 1);
      aClimbAssignment.delete();
      climbAssignments.remove(aClimbAssignment);
    }
    
    while (hotels.size() > 0)
    {
      Hotel aHotel = hotels.get(hotels.size() - 1);
      aHotel.delete();
      hotels.remove(aHotel);
    }
    
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
  }

}