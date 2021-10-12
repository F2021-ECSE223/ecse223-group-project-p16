package ca.mcgill.ecse223.climbsafe.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 80 "slide10.ump"
// line 163 "slide10.ump"
// line 226 "slide10.ump"
public class ClimbAssignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbAssignment Associations
  private Member member;
  private MountainGuide guide;
  private List<Equipment> equipment;
  private List<ClimbingWeek> climbingWeeks;
  private List<Bundle> bundles;
  private Hotel hotel;
  private ClimbingSeason season;
  private ClimbSafe climbSafe;
  private Administrator administrator;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbAssignment(Member aMember, ClimbingSeason aSeason, ClimbSafe aClimbSafe, Administrator aAdministrator, ClimbingWeek... allClimbingWeeks)
  {
    if (aMember == null || aMember.getAssignment() != null)
    {
      throw new RuntimeException("Unable to create ClimbAssignment due to aMember. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    member = aMember;
    equipment = new ArrayList<Equipment>();
    climbingWeeks = new ArrayList<ClimbingWeek>();
    boolean didAddClimbingWeeks = setClimbingWeeks(allClimbingWeeks);
    if (!didAddClimbingWeeks)
    {
      throw new RuntimeException("Unable to create ClimbAssignment, must have at least 1 climbingWeeks. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bundles = new ArrayList<Bundle>();
    boolean didAddSeason = setSeason(aSeason);
    if (!didAddSeason)
    {
      throw new RuntimeException("Unable to create assignment due to season. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create climbAssignment due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAdministrator = setAdministrator(aAdministrator);
    if (!didAddAdministrator)
    {
      throw new RuntimeException("Unable to create assignment due to administrator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public ClimbAssignment(String aNameForMember, boolean aHireClimbGuideForMember, int aNumberOfWeeksForMember, boolean aStayBeforeOrAfterForMember, int aEmergencyContactForMember, ClimbSafe aClimbSafeForMember, ClimbingSeason aSeason, ClimbSafe aClimbSafe, Administrator aAdministrator, ClimbingWeek... allClimbingWeeks)
  {
    member = new Member(aNameForMember, aHireClimbGuideForMember, aNumberOfWeeksForMember, aStayBeforeOrAfterForMember, aEmergencyContactForMember, aClimbSafeForMember, this);
    equipment = new ArrayList<Equipment>();
    climbingWeeks = new ArrayList<ClimbingWeek>();
    boolean didAddClimbingWeeks = setClimbingWeeks(allClimbingWeeks);
    if (!didAddClimbingWeeks)
    {
      throw new RuntimeException("Unable to create ClimbAssignment, must have at least 1 climbingWeeks. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bundles = new ArrayList<Bundle>();
    boolean didAddSeason = setSeason(aSeason);
    if (!didAddSeason)
    {
      throw new RuntimeException("Unable to create assignment due to season. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create climbAssignment due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAdministrator = setAdministrator(aAdministrator);
    if (!didAddAdministrator)
    {
      throw new RuntimeException("Unable to create assignment due to administrator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_GetOne */
  public MountainGuide getGuide()
  {
    return guide;
  }

  public boolean hasGuide()
  {
    boolean has = guide != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipment.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipment()
  {
    List<Equipment> newEquipment = Collections.unmodifiableList(equipment);
    return newEquipment;
  }

  public int numberOfEquipment()
  {
    int number = equipment.size();
    return number;
  }

  public boolean hasEquipment()
  {
    boolean has = equipment.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipment.indexOf(aEquipment);
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
  /* Code from template association_GetOne */
  public Hotel getHotel()
  {
    return hotel;
  }

  public boolean hasHotel()
  {
    boolean has = hotel != null;
    return has;
  }
  /* Code from template association_GetOne */
  public ClimbingSeason getSeason()
  {
    return season;
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
  /* Code from template association_SetOptionalOneToMany */
  public boolean setGuide(MountainGuide aGuide)
  {
    boolean wasSet = false;
    MountainGuide existingGuide = guide;
    guide = aGuide;
    if (existingGuide != null && !existingGuide.equals(aGuide))
    {
      existingGuide.removeAssignment(this);
    }
    if (aGuide != null)
    {
      aGuide.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipment()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    equipment.add(aEquipment);
    if (aEquipment.indexOfAssignment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aEquipment.addAssignment(this);
      if (!wasAdded)
      {
        equipment.remove(aEquipment);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (!equipment.contains(aEquipment))
    {
      return wasRemoved;
    }

    int oldIndex = equipment.indexOf(aEquipment);
    equipment.remove(oldIndex);
    if (aEquipment.indexOfAssignment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aEquipment.removeAssignment(this);
      if (!wasRemoved)
      {
        equipment.add(oldIndex,aEquipment);
      }
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
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipment.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfClimbingWeeksValid()
  {
    boolean isValid = numberOfClimbingWeeks() >= minimumNumberOfClimbingWeeks();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClimbingWeeks()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasAdded = false;
    if (climbingWeeks.contains(aClimbingWeek)) { return false; }
    climbingWeeks.add(aClimbingWeek);
    if (aClimbingWeek.indexOfAssignment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aClimbingWeek.addAssignment(this);
      if (!wasAdded)
      {
        climbingWeeks.remove(aClimbingWeek);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasRemoved = false;
    if (!climbingWeeks.contains(aClimbingWeek))
    {
      return wasRemoved;
    }

    if (numberOfClimbingWeeks() <= minimumNumberOfClimbingWeeks())
    {
      return wasRemoved;
    }

    int oldIndex = climbingWeeks.indexOf(aClimbingWeek);
    climbingWeeks.remove(oldIndex);
    if (aClimbingWeek.indexOfAssignment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aClimbingWeek.removeAssignment(this);
      if (!wasRemoved)
      {
        climbingWeeks.add(oldIndex,aClimbingWeek);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setClimbingWeeks(ClimbingWeek... newClimbingWeeks)
  {
    boolean wasSet = false;
    ArrayList<ClimbingWeek> verifiedClimbingWeeks = new ArrayList<ClimbingWeek>();
    for (ClimbingWeek aClimbingWeek : newClimbingWeeks)
    {
      if (verifiedClimbingWeeks.contains(aClimbingWeek))
      {
        continue;
      }
      verifiedClimbingWeeks.add(aClimbingWeek);
    }

    if (verifiedClimbingWeeks.size() != newClimbingWeeks.length || verifiedClimbingWeeks.size() < minimumNumberOfClimbingWeeks())
    {
      return wasSet;
    }

    ArrayList<ClimbingWeek> oldClimbingWeeks = new ArrayList<ClimbingWeek>(climbingWeeks);
    climbingWeeks.clear();
    for (ClimbingWeek aNewClimbingWeek : verifiedClimbingWeeks)
    {
      climbingWeeks.add(aNewClimbingWeek);
      if (oldClimbingWeeks.contains(aNewClimbingWeek))
      {
        oldClimbingWeeks.remove(aNewClimbingWeek);
      }
      else
      {
        aNewClimbingWeek.addAssignment(this);
      }
    }

    for (ClimbingWeek anOldClimbingWeek : oldClimbingWeeks)
    {
      anOldClimbingWeek.removeAssignment(this);
    }
    wasSet = true;
    return wasSet;
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
    if (aBundle.indexOfAssignment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBundle.addAssignment(this);
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
    if (aBundle.indexOfAssignment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBundle.removeAssignment(this);
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
  /* Code from template association_SetOptionalOneToMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      existingHotel.removeAssignment(this);
    }
    if (aHotel != null)
    {
      aHotel.addAssignment(this);
    }
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
      existingSeason.removeAssignment(this);
    }
    season.addAssignment(this);
    wasSet = true;
    return wasSet;
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
      existingClimbSafe.removeClimbAssignment(this);
    }
    climbSafe.addClimbAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAdministrator(Administrator aAdministrator)
  {
    boolean wasSet = false;
    if (aAdministrator == null)
    {
      return wasSet;
    }

    Administrator existingAdministrator = administrator;
    administrator = aAdministrator;
    if (existingAdministrator != null && !existingAdministrator.equals(aAdministrator))
    {
      existingAdministrator.removeAssignment(this);
    }
    administrator.addAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.delete();
    }
    if (guide != null)
    {
      MountainGuide placeholderGuide = guide;
      this.guide = null;
      placeholderGuide.removeAssignment(this);
    }
    ArrayList<Equipment> copyOfEquipment = new ArrayList<Equipment>(equipment);
    equipment.clear();
    for(Equipment aEquipment : copyOfEquipment)
    {
      aEquipment.removeAssignment(this);
    }
    ArrayList<ClimbingWeek> copyOfClimbingWeeks = new ArrayList<ClimbingWeek>(climbingWeeks);
    climbingWeeks.clear();
    for(ClimbingWeek aClimbingWeek : copyOfClimbingWeeks)
    {
      aClimbingWeek.removeAssignment(this);
    }
    ArrayList<Bundle> copyOfBundles = new ArrayList<Bundle>(bundles);
    bundles.clear();
    for(Bundle aBundle : copyOfBundles)
    {
      aBundle.removeAssignment(this);
    }
    if (hotel != null)
    {
      Hotel placeholderHotel = hotel;
      this.hotel = null;
      placeholderHotel.removeAssignment(this);
    }
    ClimbingSeason placeholderSeason = season;
    this.season = null;
    if(placeholderSeason != null)
    {
      placeholderSeason.removeAssignment(this);
    }
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeClimbAssignment(this);
    }
    Administrator placeholderAdministrator = administrator;
    this.administrator = null;
    if(placeholderAdministrator != null)
    {
      placeholderAdministrator.removeAssignment(this);
    }
  }

}