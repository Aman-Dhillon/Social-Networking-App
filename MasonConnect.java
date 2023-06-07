import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * This class is the main class of the social networking app. It is a graph of
 * profiles that are connected together to create the social network of users.
 * Each user is represented by a profile object.
 * 
 * @author amandhillon
 *
 */
public class MasonConnect extends Graph<Profile> {

    /**
     * initializes the social networking app.
     */
    public MasonConnect() {
        super();
    }

    /**
     * Adds a new user to the social network.
     * 
     * @param p is the user to be added.
     * @return true if the user is added.
     */
    public boolean addUser(Profile p) {

        return this.addVertex(p);

    }

    /**
     * Removes an existing user from the social network. If the user does not exist,
     * it returns null.
     * 
     * @param p is the user to be removed.
     */
    public void removeUser(Profile p) {
        if (this.exists(p)) {

            ArrayList<Profile> friendsOfP = p.getFriendProfiles();
            for (int i = 0; i < friendsOfP.size(); i++) {
                Profile friend = friendsOfP.get(i);
                friend.unFriend(p);
            }
            p.getFriendProfiles().clear();

            this.removeVertex(p);

        }
    }

    /**
     * Creates a friendship between two users on MasonConnect.
     * 
     * @param a is a user to be connected.
     * @param b is a user to be connected.
     * @return true if connected, false otherwise.
     */
    public boolean createFriendship(Profile a, Profile b) {
        boolean created = false;

        if (exists(a) && exists(b)) {

            created = this.addEdge(a, b);

            a.addFriend(b);
            b.addFriend(a);
        }
        return created;
    }

    /**
     * Removes a friendship between two users on MasonConnect.
     * 
     * @param a is a user to be removed.
     * @param b is a user to be removed.
     * @return true if removed, false otherwise.
     */
    public boolean removeFriendship(Profile a, Profile b) {
        boolean removed = false;

        if (exists(a) && exists(b)) {
            removed = this.removeEdge(a, b);
            a.unFriend(b);
            b.unFriend(a);

        }

        return removed;
    }

    /**
     * Checks if there is friendship between Profiles a and b.
     * 
     * @param a is a user of MasonConnect.
     * @param b is a user of MasonConnect.
     * @return true if a and b are connected, false otherwise.
     */
    public boolean hasFriendship(Profile a, Profile b) {
        return this.hasEdge(a, b);

    }

    /**
     * This method displays each profiles information and friends, starting from the
     * startPoint profile.
     * 
     * @param startPoint is the start of the path.
     */
    public void traverse(Profile startPoint) {
        Queue<Profile> q = new LinkedList<Profile>();
        q = getBreadthFirstTraversal(startPoint);
        for (Profile p : q) {
            p.display();

        }
    }

    /**
     * Outputs a list of Profiles, who are friends with one or more of the profile's
     * friends.
     * 
     * @param user of MasonConnect.
     * @return a list of Profiles, who are friends with one or more of the profile's
     *         friends. Else null.
     */
    public List<Profile> friendSuggestion(Profile user) {

        HashMap<Profile, Integer> map = new HashMap<>();
        for (Profile p : user.getFriendProfiles()) {
            map.put(p, 0);
        }

        ArrayList<Profile> friendSuggestion = new ArrayList<Profile>();
        if (!exists(user) && user.getFriendProfiles().isEmpty()) {
            return null;
        }

        for (Profile u : user.getFriendProfiles()) {

            for (Profile friendOfFriend : u.getFriendProfiles()) {
                if (!map.containsKey(friendOfFriend)) {
                    friendSuggestion.add(friendOfFriend);
                    friendSuggestion.remove(user);
                }

            }

        }
        return friendSuggestion;
    }

    /**
     * Determines the distance between two vertices.
     * 
     * @param a is a user of MasonConnect.
     * @param b is a user of MasonConnect.
     * @return Returns the friendship distance between two profiles.
     */
    public int friendshipDistance(Profile a, Profile b) {
        
        Stack<Profile> stack = new Stack<>();
        int result = this.getShortestPath(a, b, stack);
        if (this.getShortestPath(a, b, stack) == Integer.MAX_VALUE) {
            result = -1;
        } else {
            result = this.getShortestPath(a, b, stack);
        }
        return result;
    }

    /**
     * This method determines whether the user exists in the graph.
     * 
     * @param user is the user of the social network.
     * @return true is user exists, else false.
     */
    public boolean exists(Profile user) {
        return this.vertices.get(user) != null;
    }

}
