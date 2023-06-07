import java.util.ArrayList;

/**
 * This class represents the profiles of the users of the MasonConnect.
 * 
 * @author Amanjot Dhillon
 *
 */
public class Profile {

    /**
     * String representation of name of user.
     */
    private String name;

    /**
     * a String that the user uses to specify their status.
     */
    private String status;

    /**
     * an arraylist of profiles that stores friends of the user.
     */
    private ArrayList<Profile> friendProfiles;

    /**
     * initializes all the String attributes to empty strings and a default
     * arraylist.
     */
    public Profile() {
        this.name = "";
        this.status = "";
        this.friendProfiles = new ArrayList<>();
    }

    /**
     * initializes the attributes with the accepted valued.
     * 
     * @param name           is the name of user.
     * @param status         is the status of user.
     * @param friendProfiles is the list of friends of user.
     */
    public Profile(String name, String status, ArrayList<Profile> friendProfiles) {
        super();
        this.name = name;
        this.status = status;
        this.friendProfiles = friendProfiles;
    }

    /**
     * initializes the attributes with the accepted valued and the last attribute
     * with a default arraylist object.
     * 
     * @param name   is name of user.
     * @param status is status of user.
     */
    public Profile(String name, String status) {
        super();
        this.name = name;
        this.status = status;
        this.friendProfiles = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {

        boolean isEqual;

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            isEqual = false;
        }

        if (getClass() != obj.getClass()) {
            isEqual = false;
        } else {
            Profile otherObj = (Profile) obj;
            isEqual = ((this.getName().equals(otherObj.getName()) && (this.getStatus().equals(otherObj.getStatus()))
                    && (this.friendProfiles.equals(otherObj.getFriendProfiles()))));
        }
        return isEqual;

    }

    /**
     * getter method for username.
     * 
     * @return the name of user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * setter method for username.
     * 
     * @param firstName the name to set
     * @param lastName  the name to set
     */
    public void setName(String firstName, String lastName) {
        this.name = firstName + " " + lastName;
    }

    /**
     * getter method for status.
     * 
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * setter method for status.
     * 
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Helper function to represent a user profile.
     * 
     * @return string builder that represents a user profile.
     */
    private String stringDisplay() {

        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + this.name);
        sb.append("\n\tStatus: " + this.status);
        sb.append("\n\tNumber of friend profiles: " + this.friendProfiles.size() + "\n");
        return sb.toString();

    }

    /**
     * a string that represents the profile of the user.
     * 
     * @return string representation of user profile.
     */
    public String toString() {

        return this.stringDisplay();

    }

    /**
     * displays the profile and the friends profiles.
     */
    public void display() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.toString());
        sb.append("\nFriends: ");
        for (Profile friend : friendProfiles) {
            sb.append("\n\t" + friend.getName().toString().replace("[", "").replace("]", "").replace(",", ""));
        }
        System.out.println(sb);

    }

    /**
     * Returns a list of the users friends.
     * 
     * @return the friendProfiles
     */
    public ArrayList<Profile> getFriendProfiles() {
        return this.friendProfiles;
    }

    /**
     * This method adds a friend to the users list.
     * 
     * @param user is user that gets a user added to their friends list.
     */
    public void addFriend(Profile user) {// issue here
        for (Profile u : friendProfiles) {
            if (u.equals(user)) {
                return;
            }

        }
        friendProfiles.add(user);

    }

    /**
     * Removes an existing friend from the list of friends.
     * 
     * @param user to be removed from friends list.
     * @return true if removed, else false.
     */
    public boolean unFriend(Profile user) {
        boolean removed = false;

        removed = friendProfiles.remove(user);

        return removed;
    }

}
