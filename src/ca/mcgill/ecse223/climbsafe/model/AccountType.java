package ca.mcgill.ecse223.climbsafe.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 15 "slide10.ump"
// line 109 "slide10.ump"
public abstract class AccountType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AccountType Associations
  private User user;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AccountType()
  {}

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }

  public boolean hasUser()
  {
    boolean has = user != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setUser(User aNewUser)
  {
    boolean wasSet = false;
    if (user != null && !user.equals(aNewUser) && equals(user.getAccountType()))
    {
      //Unable to setUser, as existing user would become an orphan
      return wasSet;
    }

    user = aNewUser;
    AccountType anOldAccountType = aNewUser != null ? aNewUser.getAccountType() : null;

    if (!this.equals(anOldAccountType))
    {
      if (anOldAccountType != null)
      {
        anOldAccountType.user = null;
      }
      if (user != null)
      {
        user.setAccountType(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    User existingUser = user;
    user = null;
    if (existingUser != null)
    {
      existingUser.delete();
    }
  }

}