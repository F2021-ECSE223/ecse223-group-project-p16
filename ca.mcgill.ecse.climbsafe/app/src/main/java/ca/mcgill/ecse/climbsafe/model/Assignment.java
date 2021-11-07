/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;

import ca.mcgill.ecse.climbsafe.model.Member.MemberStatus;

// line 1 "../../../../../ClimbSafeStates.ump"
// line 83 "../../../../../ClimbSafe.ump"
public class Assignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private String authCode;
  private int startWeek;
  private int endWeek;

  //Assignment State Machines
  public enum AssigmentStatus { AssignmentProgress, Finished, Cancelled }
  public enum AssigmentStatusAssignmentProgress { Null, Assigned, Paid, Started }
  private AssigmentStatus assigmentStatus;
  private AssigmentStatusAssignmentProgress assigmentStatusAssignmentProgress;

  //Assignment Associations
  private Member member;
  private Guide guide;
  private Hotel hotel;
  private ClimbSafe climbSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(int aStartWeek, int aEndWeek, Member aMember, ClimbSafe aClimbSafe)
  {
    authCode = null;
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create assignment due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create assignment due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setAssigmentStatusAssignmentProgress(AssigmentStatusAssignmentProgress.Null);
    setAssigmentStatus(AssigmentStatus.AssignmentProgress);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAuthCode(String aAuthCode)
  {
    boolean wasSet = false;
    authCode = aAuthCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartWeek(int aStartWeek)
  {
    boolean wasSet = false;
    startWeek = aStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeek(int aEndWeek)
  {
    boolean wasSet = false;
    endWeek = aEndWeek;
    wasSet = true;
    return wasSet;
  }

  public String getAuthCode()
  {
    return authCode;
  }

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getEndWeek()
  {
    return endWeek;
  }

  public String getAssigmentStatusFullName()
  {
    String answer = assigmentStatus.toString();
    if (assigmentStatusAssignmentProgress != AssigmentStatusAssignmentProgress.Null) { answer += "." + assigmentStatusAssignmentProgress.toString(); }
    return answer;
  }

  public AssigmentStatus getAssigmentStatus()
  {
    return assigmentStatus;
  }

  public AssigmentStatusAssignmentProgress getAssigmentStatusAssignmentProgress()
  {
    return assigmentStatusAssignmentProgress;
  }

  public boolean cancel()
  {
    boolean wasEventProcessed = false;
    
    AssigmentStatus aAssigmentStatus = assigmentStatus;
    switch (aAssigmentStatus)
    {
      case AssignmentProgress:
        exitAssigmentStatus();
        // line 49 "../../../../../ClimbSafeStates.ump"
        cancelTrip(member);
        setAssigmentStatus(AssigmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 68 "../../../../../ClimbSafeStates.ump"
        String error = "Cannot cancel a trip which has finished";
        rejectCancelRequest(error);
        setAssigmentStatus(AssigmentStatus.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pay(Member member,String aAuthCode)
  {
    boolean wasEventProcessed = false;
    
    AssigmentStatus aAssigmentStatus = assigmentStatus;
    AssigmentStatusAssignmentProgress aAssigmentStatusAssignmentProgress = assigmentStatusAssignmentProgress;
    switch (aAssigmentStatus)
    {
      case Finished:
        // line 58 "../../../../../ClimbSafeStates.ump"
        String error = "Cannot pay for a trip which has finished";
        rejectPayment(error);
        setAssigmentStatus(AssigmentStatus.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 78 "../../../../../ClimbSafeStates.ump"
        error = "Cannot pay for a trip which has been cancelled";
        rejectPayment(error);
        setAssigmentStatus(AssigmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAssigmentStatusAssignmentProgress)
    {
      case Assigned:
        if (isAuthCodeValid(aAuthCode))
        {
          exitAssigmentStatusAssignmentProgress();
        // line 9 "../../../../../ClimbSafeStates.ump"
          makePayment(member);
          setAssigmentStatusAssignmentProgress(AssigmentStatusAssignmentProgress.Paid);
          wasEventProcessed = true;
          break;
        }
        break;
      case Paid:
        exitAssigmentStatusAssignmentProgress();
        // line 27 "../../../../../ClimbSafeStates.ump"
        String error = "Trip has already been paid for";
          rejectPayment(error);
        setAssigmentStatusAssignmentProgress(AssigmentStatusAssignmentProgress.Paid);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean start(Member member)
  {
    boolean wasEventProcessed = false;
    
    AssigmentStatus aAssigmentStatus = assigmentStatus;
    AssigmentStatusAssignmentProgress aAssigmentStatusAssignmentProgress = assigmentStatusAssignmentProgress;
    switch (aAssigmentStatus)
    {
      case Finished:
        // line 63 "../../../../../ClimbSafeStates.ump"
        String error = "Cannot start a trip which has finished";
        rejectStartRequest(error);
        setAssigmentStatus(AssigmentStatus.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 83 "../../../../../ClimbSafeStates.ump"
        error = "Cannot start a trip which has been Cancelled";
        rejectStartRequest(error);
        setAssigmentStatus(AssigmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAssigmentStatusAssignmentProgress)
    {
      case Assigned:
        exitAssigmentStatusAssignmentProgress();
        // line 14 "../../../../../ClimbSafeStates.ump"
        banMember(member);
        setAssigmentStatusAssignmentProgress(AssigmentStatusAssignmentProgress.Assigned);
        wasEventProcessed = true;
        break;
      case Paid:
        exitAssigmentStatusAssignmentProgress();
        // line 23 "../../../../../ClimbSafeStates.ump"
        startTrip(member);
        setAssigmentStatusAssignmentProgress(AssigmentStatusAssignmentProgress.Started);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finish(Member member)
  {
    boolean wasEventProcessed = false;
    
    AssigmentStatus aAssigmentStatus = assigmentStatus;
    AssigmentStatusAssignmentProgress aAssigmentStatusAssignmentProgress = assigmentStatusAssignmentProgress;
    switch (aAssigmentStatus)
    {
      case Cancelled:
        // line 88 "../../../../../ClimbSafeStates.ump"
        String error = "Cannot finish a trip which has been Cancelled";
        rejectFinishRequest(error);
        setAssigmentStatus(AssigmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAssigmentStatusAssignmentProgress)
    {
      case Paid:
        exitAssigmentStatusAssignmentProgress();
        // line 32 "../../../../../ClimbSafeStates.ump"
        String error = "Cannot finish a trip which has not started";
          rejectFinishRequest(error);
        setAssigmentStatusAssignmentProgress(AssigmentStatusAssignmentProgress.Paid);
        wasEventProcessed = true;
        break;
      case Started:
        exitAssigmentStatus();
        // line 42 "../../../../../ClimbSafeStates.ump"
        finishTrip(member);
        setAssigmentStatus(AssigmentStatus.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitAssigmentStatus()
  {
    switch(assigmentStatus)
    {
      case AssignmentProgress:
        exitAssigmentStatusAssignmentProgress();
        break;
    }
  }

  private void setAssigmentStatus(AssigmentStatus aAssigmentStatus)
  {
    assigmentStatus = aAssigmentStatus;

    // entry actions and do activities
    switch(assigmentStatus)
    {
      case AssignmentProgress:
        if (assigmentStatusAssignmentProgress == AssigmentStatusAssignmentProgress.Null) { setAssigmentStatusAssignmentProgress(AssigmentStatusAssignmentProgress.Assigned); }
        break;
    }
  }

  private void exitAssigmentStatusAssignmentProgress()
  {
    switch(assigmentStatusAssignmentProgress)
    {
      case Assigned:
        setAssigmentStatusAssignmentProgress(AssigmentStatusAssignmentProgress.Null);
        break;
      case Paid:
        setAssigmentStatusAssignmentProgress(AssigmentStatusAssignmentProgress.Null);
        break;
      case Started:
        setAssigmentStatusAssignmentProgress(AssigmentStatusAssignmentProgress.Null);
        break;
    }
  }

  private void setAssigmentStatusAssignmentProgress(AssigmentStatusAssignmentProgress aAssigmentStatusAssignmentProgress)
  {
    assigmentStatusAssignmentProgress = aAssigmentStatusAssignmentProgress;
    if (assigmentStatus != AssigmentStatus.AssignmentProgress && aAssigmentStatusAssignmentProgress != AssigmentStatusAssignmentProgress.Null) { setAssigmentStatus(AssigmentStatus.AssignmentProgress); }
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_GetOne */
  public Guide getGuide()
  {
    return guide;
  }

  public boolean hasGuide()
  {
    boolean has = guide != null;
    return has;
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
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMember(Member aNewMember)
  {
    boolean wasSet = false;
    if (aNewMember == null)
    {
      //Unable to setMember to null, as assignment must always be associated to a member
      return wasSet;
    }
    
    Assignment existingAssignment = aNewMember.getAssignment();
    if (existingAssignment != null && !equals(existingAssignment))
    {
      //Unable to setMember, the current member already has a assignment, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Member anOldMember = member;
    member = aNewMember;
    member.setAssignment(this);

    if (anOldMember != null)
    {
      anOldMember.setAssignment(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setGuide(Guide aGuide)
  {
    boolean wasSet = false;
    Guide existingGuide = guide;
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
      existingClimbSafe.removeAssignment(this);
    }
    climbSafe.addAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.setAssignment(null);
    }
    if (guide != null)
    {
      Guide placeholderGuide = guide;
      this.guide = null;
      placeholderGuide.removeAssignment(this);
    }
    if (hotel != null)
    {
      Hotel placeholderHotel = hotel;
      this.hotel = null;
      placeholderHotel.removeAssignment(this);
    }
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeAssignment(this);
    }
  }

  // line 96 "../../../../../ClimbSafeStates.ump"
   private boolean isAuthCodeValid(String aAuthCode){
    return aAuthCode != null;
  }

  // line 100 "../../../../../ClimbSafeStates.ump"
   private boolean isMemberValid(Member member){
    return member.getMemberStatus() == MemberStatus.NotBanned;
  }

  // line 104 "../../../../../ClimbSafeStates.ump"
   private void makePayment(Member member){
    if (!isMemberValid(member)){
      throw new RuntimeException("Cannot pay for the trip due to a Ban");
    }
  }

  // line 110 "../../../../../ClimbSafeStates.ump"
   private void startTrip(Member member){
    if (!isMemberValid(member)){
      throw new RuntimeException("Cannot start the trip due to a ban");
    }
  }

  // line 115 "../../../../../ClimbSafeStates.ump"
   private void cancelTrip(Member member){
    if (!isMemberValid(member)){
      throw new RuntimeException("Cannot cancel the trip due to a ban");
    }
  }

  // line 121 "../../../../../ClimbSafeStates.ump"
   private void finishTrip(Member member){
    if (!isMemberValid(member)){
      throw new RuntimeException("Cannot finish the trip due to a ban");
    }
  }

  // line 127 "../../../../../ClimbSafeStates.ump"
   private void banMember(Member member){
    member.ban();
  }

  // line 132 "../../../../../ClimbSafeStates.ump"
   private void rejectPayment(String error){
    throw new RuntimeException(error);
  }

  // line 136 "../../../../../ClimbSafeStates.ump"
   private void rejectFinishRequest(String error){
    throw new RuntimeException(error);
  }

  // line 140 "../../../../../ClimbSafeStates.ump"
   private void rejectStartRequest(String error){
    throw new RuntimeException(error);
  }

  // line 144 "../../../../../ClimbSafeStates.ump"
   private void rejectCancelRequest(String error){
    throw new RuntimeException(error);
  }


  public String toString()
  {
    return super.toString() + "["+
            "authCode" + ":" + getAuthCode()+ "," +
            "startWeek" + ":" + getStartWeek()+ "," +
            "endWeek" + ":" + getEndWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }
}