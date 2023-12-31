import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MeetingTimeSelector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of team members: ");
        int numMembers = scanner.nextInt();
        scanner.nextLine(); 

        List<String> teamMembers = new ArrayList<>();
        for (int i = 1; i <= numMembers; i++) {
            System.out.print("Enter the name of team member " + i + ": ");
            String memberName = scanner.nextLine();
            teamMembers.add(memberName);
        }

        Map<String, List<String>> availability = new HashMap<>();
        for (String member : teamMembers) {
            while (true) {
                System.out.print("Enter the availability for " + member + " (e.g., 09:00-10:00): ");
                String availabilityString = scanner.nextLine();
                if (isValidTimeSlotFormat(availabilityString)) {
                    List<String> availabilityList = new ArrayList<>();
                    availabilityList.add(availabilityString);
                    availability.put(member, availabilityList);
                    break; // Exit the loop if the format is correct
                } else {
                    System.out.println("Incorrect format. Please enter in HH:MM-HH:MM format.");
                }
            }
        }

        // Find the meeting time that works for the majority
        List<String> commonAvailability = new ArrayList<>(availability.get(teamMembers.get(0)));
        for (List<String> memberAvailability : availability.values()) {
            commonAvailability.retainAll(memberAvailability);
        }

        // Print the common meeting time
        System.out.println("\nCommon Meeting Time:");
        for (String timeSlot : commonAvailability) {
            System.out.println(timeSlot);
        }

        scanner.close();
    }

    // Validate time slot format using regular expression
    private static boolean isValidTimeSlotFormat(String timeSlot) {
        String pattern = "^\\d{2}:\\d{2}-\\d{2}:\\d{2}$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(timeSlot);
        return matcher.matches();
    }
}
