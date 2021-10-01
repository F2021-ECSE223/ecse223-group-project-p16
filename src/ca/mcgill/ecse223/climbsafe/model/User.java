package ca.mcgill.ecse223.climbsafe.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 98 "slide10.ump"
// line 183 "slide10.ump"
// line 236 "slide10.ump"
public class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByEmail = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String email;
  private String password;

  //User Associations
  private ClimbSafe climbSafe;
  private AccountType accountType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aEmail, String aPassword, ClimbSafe aClimbSafe, AccountType aAccountType)
  {
    password = aPassword;
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create user due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAccountType = setAccountType(aAccountType);
    if (!didAddAccountType)
    {
      throw new RuntimeException("Unable to create user due to accountType. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    String anOldEmail = getEmail();
    if (anOldEmail != null && anOldEmail.equals(aEmail)) {
      return true;
    }
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    email = aEmail;
    wasSet = true;
    if (anOldEmail != null) {
      usersByEmail.remove(anOldEmail);
    }
    usersByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithEmail(String aEmail)
  {
    return usersByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_GetOne */
  public AccountType getAccountType()
  {
    return accountType;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setClimbSafe(ClimbSafe aClimbSafe)
  {
    boolean wasSet = false;
    //Must provide climbSafe to user
    if (aClimbSafe == null)
    {
      return wasSet;
    }

    if (climbSafe != null && climbSafe.numberOfUsers() <= ClimbSafe.minimumNumberOfUsers())
    {
      return wasSet;
    }

    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = aClimbSafe;
    if (existingClimbSafe != null && !existingClimbSafe.equals(aClimbSafe))
    {
      boolean didRemove = existingClimbSafe.removeUser(this);
      if (!didRemove)
      {
        climbSafe = existingClimbSafe;
        return wasSet;
      }
    }
    climbSafe.addUser(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setAccountType(AccountType aNewAccountType)
  {
    boolean wasSet = false;
    if (aNewAccountType == null)
    {
      //Unable to setAccountType to null, as user must always be associated to a accountType
      return wasSet;
    }
    
    User existingUser = aNewAccountType.getUser();
    if (existingUser != null && !equals(existingUser))
    {
      //Unable to setAccountType, the current accountType already has a user, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    AccountType anOldAccountType = accountType;
    accountType = aNewAccountType;
    accountType.setUser(this);

    if (anOldAccountType != null)
    {
      anOldAccountType.setUser(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    usersByEmail.remove(getEmail());
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeUser(this);
    }
    AccountType existingAccountType = accountType;
    accountType = null;
    if (existingAccountType != null)
    {
      existingAccountType.setUser(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "accountType = "+(getAccountType()!=null?Integer.toHexString(System.identityHashCode(getAccountType())):"null");
  }
}