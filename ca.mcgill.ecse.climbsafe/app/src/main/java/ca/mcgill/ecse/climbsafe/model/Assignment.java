/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;

// line 1 "../../../../../ClimbSafeStates.ump"
// line 83 "../../../../../ClimbSafe.ump"
public class Assignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private String authCode;
  private int refundPercent;
  private int startWeek;
  private int endWeek;

  //Assignment State Machines
  public enum AssignmentStatus { Active, Finished, Cancelled }
  public enum AssignmentStatusActive { Null, Assigned, Paid, Started }
  private AssignmentStatus assignmentStatus;
  private AssignmentStatusActive assignmentStatusActive;

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
    refundPercent = 0;
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
    setAssignmentStatusActive(AssignmentStatusActive.Null);
    setAssignmentStatus(AssignmentStatus.Active);
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

  public boolean setRefundPercent(int aRefundPercent)
  {
    boolean wasSet = false;
    refundPercent = aRefundPercent;
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

  public int getRefundPercent()
  {
    return refundPercent;
  }

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getEndWeek()
  {
    return endWeek;
  }

  public String getAssignmentStatusFullName()
  {
    String answer = assignmentStatus.toString();
    if (assignmentStatusActive != AssignmentStatusActive.Null) { answer += "." + assignmentStatusActive.toString(); }
    return answer;
  }

  public AssignmentStatus getAssignmentStatus()
  {
    return assignmentStatus;
  }

  public AssignmentStatusActive getAssignmentStatusActive()
  {
    return assignmentStatusActive;
  }

  public boolean cancel()
  {
    boolean wasEventProcessed = false;
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    switch (aAssignmentStatus)
    {
      case Active:
        exitAssignmentStatus();
        // line 50 "../../../../../ClimbSafeStates.ump"
        cancelTrip(member);
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 69 "../../../../../ClimbSafeStates.ump"
        String error = "Cannot cancel a trip which has finished";
        rejectCancelRequest(error);
        setAssignmentStatus(AssignmentStatus.Finished);
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
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    AssignmentStatusActive aAssignmentStatusActive = assignmentStatusActive;
    switch (aAssignmentStatus)
    {
      case Finished:
        // line 59 "../../../../../ClimbSafeStates.ump"
        String error = "Cannot pay for a trip which has finished";
        rejectPayment(error);
        setAssignmentStatus(AssignmentStatus.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 79 "../../../../../ClimbSafeStates.ump"
        error = "Cannot pay for a trip which has been cancelled";
        rejectPayment(error);
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAssignmentStatusActive)
    {
      case Assigned:
        if (isAuthCodeValid(aAuthCode))
        {
          exitAssignmentStatusActive();
        // line 11 "../../../../../ClimbSafeStates.ump"
          makePayment(member, aAuthCode);
          setAssignmentStatusActive(AssignmentStatusActive.Paid);
          wasEventProcessed = true;
          break;
        }
        break;
      case Paid:
        exitAssignmentStatusActive();
        // line 28 "../../../../../ClimbSafeStates.ump"
        String error = "Trip has already been paid for";
          rejectPayment(error);
        setAssignmentStatusActive(AssignmentStatusActive.Paid);
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
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    AssignmentStatusActive aAssignmentStatusActive = assignmentStatusActive;
    switch (aAssignmentStatus)
    {
      case Finished:
        // line 64 "../../../../../ClimbSafeStates.ump"
        String error = "Cannot start a trip which has finished";
        rejectStartRequest(error);
        setAssignmentStatus(AssignmentStatus.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 84 "../../../../../ClimbSafeStates.ump"
        error = "Cannot start a trip which has been Cancelled";
        rejectStartRequest(error);
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAssignmentStatusActive)
    {
      case Assigned:
        exitAssignmentStatusActive();
        // line 15 "../../../../../ClimbSafeStates.ump"
        banMember(member);
        setAssignmentStatusActive(AssignmentStatusActive.Assigned);
        wasEventProcessed = true;
        break;
      case Paid:
        exitAssignmentStatusActive();
        // line 24 "../../../../../ClimbSafeStates.ump"
        startTrip(member);
        setAssignmentStatusActive(AssignmentStatusActive.Started);
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
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    AssignmentStatusActive aAssignmentStatusActive = assignmentStatusActive;
    switch (aAssignmentStatus)
    {
      case Cancelled:
        // line 89 "../../../../../ClimbSafeStates.ump"
        String error = "Cannot finish a trip which has been Cancelled";
        rejectFinishRequest(error);
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAssignmentStatusActive)
    {
      case Paid:
        exitAssignmentStatusActive();
        // line 33 "../../../../../ClimbSafeStates.ump"
        String error = "Cannot finish a trip which has not started";
          rejectFinishRequest(error);
        setAssignmentStatusActive(AssignmentStatusActive.Paid);
        wasEventProcessed = true;
        break;
      case Started:
        exitAssignmentStatus();
        // line 43 "../../../../../ClimbSafeStates.ump"
        finishTrip(member);
        setAssignmentStatus(AssignmentStatus.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitAssignmentStatus()
  {
    switch(assignmentStatus)
    {
      case Active:
        exitAssignmentStatusActive();
        break;
    }
  }

  private void setAssignmentStatus(AssignmentStatus aAssignmentStatus)
  {
    assignmentStatus = aAssignmentStatus;

    // entry actions and do activities
    switch(assignmentStatus)
    {
      case Active:
        if (assignmentStatusActive == AssignmentStatusActive.Null) { setAssignmentStatusActive(AssignmentStatusActive.Assigned); }
        break;
    }
  }

  private void exitAssignmentStatusActive()
  {
    switch(assignmentStatusActive)
    {
      case Assigned:
        setAssignmentStatusActive(AssignmentStatusActive.Null);
        break;
      case Paid:
        setAssignmentStatusActive(AssignmentStatusActive.Null);
        break;
      case Started:
        setAssignmentStatusActive(AssignmentStatusActive.Null);
        break;
    }
  }

  private void setAssignmentStatusActive(AssignmentStatusActive aAssignmentStatusActive)
  {
    assignmentStatusActive = aAssignmentStatusActive;
    if (assignmentStatus != AssignmentStatus.Active && aAssignmentStatusActive != AssignmentStatusActive.Null) { setAssignmentStatus(AssignmentStatus.Active); }
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

  // line 98 "../../../../../ClimbSafeStates.ump"
   private boolean isAuthCodeValid(String aAuthCode){
    return aAuthCode != null;
  }

  // line 102 "../../../../../ClimbSafeStates.ump"
   private boolean isMemberValid(Member member){
    return member.getMemberStatusFullName().equals("NotBanned");
  }

  // line 106 "../../../../../ClimbSafeStates.ump"
   private void makePayment(Member member, String aAuthCode){
    if (!isMemberValid(member)){
      throw new RuntimeException("Cannot pay for the trip due to a Ban");
    }
    setAuthCode(aAuthCode);
  }

  // line 113 "../../../../../ClimbSafeStates.ump"
   private void startTrip(Member member){
    if (!isMemberValid(member)){
      throw new RuntimeException("Cannot start the trip due to a ban");
    }
  }

  // line 119 "../../../../../ClimbSafeStates.ump"
   private void cancelTrip(Member member){
    if (!isMemberValid(member)){
      throw new RuntimeException("Cannot cancel the trip due to a ban");
    }
  
  	switch(this.assignmentStatusActive) {
  	  case Paid:
  	  	setRefundPercent(50);
  	  	break;
  	  case Started:
  	  	setRefundPercent(50);
  	  	break;
  	  default:
  	  	setRefundPercent(0);	
  	  }
  }

  // line 137 "../../../../../ClimbSafeStates.ump"
   private void finishTrip(Member member){
    if (!isMemberValid(member)){
      throw new RuntimeException("Cannot finish the trip due to a ban");
    }
  }

  // line 143 "../../../../../ClimbSafeStates.ump"
   private void banMember(Member member){
    member.ban();
  }

  // line 148 "../../../../../ClimbSafeStates.ump"
   private void rejectPayment(String error){
    throw new RuntimeException(error);
  }

  // line 152 "../../../../../ClimbSafeStates.ump"
   private void rejectFinishRequest(String error){
    throw new RuntimeException(error);
  }

  // line 156 "../../../../../ClimbSafeStates.ump"
   private void rejectStartRequest(String error){
    throw new RuntimeException(error);
  }

  // line 160 "../../../../../ClimbSafeStates.ump"
   private void rejectCancelRequest(String error){
    throw new RuntimeException(error);
  }


  public String toString()
  {
    return super.toString() + "["+
            "authCode" + ":" + getAuthCode()+ "," +
            "refundPercent" + ":" + getRefundPercent()+ "," +
            "startWeek" + ":" + getStartWeek()+ "," +
            "endWeek" + ":" + getEndWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }
}