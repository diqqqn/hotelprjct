import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hotel {
    static Scanner sc;
    static final String datePattern = "(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((?:19|20)[0-9][0-9])";
    static Pattern pattern = Pattern.compile(datePattern);
    static Matcher sDateMatcher;
    static Matcher eDateMatcher;
    static String[] rooms = { "101", "102", "103", "104", "105", "106", "201", "202", "203", "204", "205",
            "301", "302", "303", "304", "401", "402", "403", "501", "502", "601" };
    static String[] startDate = new String[rooms.length];
    static String[] endDate = new String[rooms.length];
    static String[] comment = new String[rooms.length];
    static String[] comment2 = new String[rooms.length];

    public static void checkChoice(int choice) {
        switch (choice) {
            case 1:
                makeReservation();
                break;
            case 2:
                listFreeRooms();
                break;
            case 3:
                checkoutRoom();
                break;
            case 4:
                status();
                break;
            case 5:
                findRoom();
                break;
            case 6:
                updateRoom();
                break;
            default:
                System.out.println("Wrong input: ");
                break;
        }
    }

    private static void updateRoom() {
    }

    private static void findRoom() {
    }

    private static void status() {
        sc = new Scanner(System.in);

        String sDateArr[];
        String eDateArr[];
        String sDate;
        String eDate;

        do {
            System.out.print("Start date: ");
            sDate = sc.nextLine();
            System.out.print("End date: ");
            eDate = sc.nextLine();
            if (!pattern.matcher(sDate).matches() && !pattern.matcher(eDate).matches()) {
                System.out.println("Invalid date!");
                return;
            }

            sDateArr = sDate.split("\\.");
            eDateArr = eDate.split("\\.");

            if (Integer.parseInt(sDateArr[1]) > Integer.parseInt(eDateArr[1]) ||
                    Integer.parseInt(sDateArr[1]) >= Integer.parseInt(eDateArr[1])
                            && Integer.parseInt(sDateArr[0]) > Integer.parseInt(eDateArr[0])
                    || Integer.parseInt(sDateArr[1]) >= Integer.parseInt(eDateArr[1])
                            && Integer.parseInt(sDateArr[2]) > Integer.parseInt(eDateArr[2])) {
                System.out.println("Invalid date!");
            }
        } while (Integer.parseInt(sDateArr[1]) > Integer.parseInt(eDateArr[1]) ||
                Integer.parseInt(sDateArr[1]) >= Integer.parseInt(eDateArr[1])
                        && Integer.parseInt(sDateArr[0]) > Integer.parseInt(eDateArr[0])
                || Integer.parseInt(sDateArr[1]) >= Integer.parseInt(eDateArr[1])
                        && Integer.parseInt(sDateArr[2]) > Integer.parseInt(eDateArr[2]));

        for (int i = 0; i < rooms.length; i++) {
            if (startDate[i] != null && endDate[i] != null && comment[i] != null) {
                String[] currentRoomStartDateArr = startDate[i].split("\\.");
                String[] currentRoomEndDateArr = startDate[i].split("\\.");

                if (Integer.parseInt(currentRoomStartDateArr[0]) >= Integer.parseInt(sDateArr[0])
                        && Integer.parseInt(currentRoomEndDateArr[0]) <= Integer.parseInt(eDateArr[0])) {

                    System.out.println(rooms[i] + ": "
                            + (Integer.parseInt(eDateArr[0]) - Integer.parseInt(currentRoomStartDateArr[0]) + " days"));
                }

            }
        }

    }

    private static void checkoutRoom() {

    }

    private static void listFreeRooms() {
        for (int i = 0; i < rooms.length; i++) {
            if (startDate[i] == null && endDate[i] == null && comment[i] == null) {
                System.out.println("Free room - " + rooms[i]);
            }
        }
    }

    private static void makeReservation() {
        sc = new Scanner(System.in);
        System.out.print("Enter room number: ");
        String roomNum = sc.nextLine();
        int roomInd = -1;

        for (int i = 0; i < rooms.length; i++) {
            if (startDate[i] == null && endDate[i] == null && comment[i] == null) {
                if (rooms[i].equals(roomNum)) {
                    roomInd = i;
                }
            }
        }

        if (roomInd != -1) {
            String sDateArr[];
            String eDateArr[];
            String sDate;
            String eDate;

            do {
                System.out.print("Start date: ");
                sDate = sc.nextLine();
                System.out.print("End date: ");
                eDate = sc.nextLine();
                sDateArr = sDate.split("\\.");
                eDateArr = eDate.split("\\.");
                if (Integer.parseInt(sDateArr[1]) > Integer.parseInt(eDateArr[1])
                        || Integer.parseInt(sDateArr[2]) > Integer.parseInt(eDateArr[2])
                        || Integer.parseInt(sDateArr[0]) >= Integer.parseInt(eDateArr[0])
                                && Integer.parseInt(sDateArr[1]) >= Integer.parseInt(eDateArr[1])) {
                    System.out.println("Invalid date!");
                }

            } while (Integer.parseInt(sDateArr[1]) > Integer.parseInt(eDateArr[1])
                    && Integer.parseInt(sDateArr[2]) > Integer.parseInt(eDateArr[2])
                    || Integer.parseInt(sDateArr[0]) >= Integer.parseInt(eDateArr[0])
                            && Integer.parseInt(sDateArr[1]) >= Integer.parseInt(eDateArr[1]));

            if (pattern.matcher(sDate).matches() && pattern.matcher(eDate).matches()) {
                startDate[roomInd] = sDate;
                endDate[roomInd] = eDate;
            } else {
                System.out.println("Invalid Date:");
                return;
            }

            do {
                System.out.print("Enter notes: ");
                comment[roomInd] = sc.nextLine();
                if (comment[roomInd] == "" || comment[roomInd] == null) {
                    System.out.println("Please add note:");
                }
            } while (comment[roomInd] == "" || comment[roomInd] == null);
            System.out.println("Reservation was created!");
        } else {
            System.out.println("Room is not free or invalid room number:");
        }
    }

    public static void myHotel() {
        sc = new Scanner(System.in);
        int choice;

        while (true) {
            do {
                System.out.print(
                        "[!] Please select what you want to do \n1 - Make a reservation: \n2 - List free rooms: \n3 - Checkout room: \n4 - Stats: \n5 - Find a room: \n6 - Update a room:\n[?] >> ");
                choice = sc.nextInt();
            } while (choice <= 0 || choice >= 7);

            checkChoice(choice);
            System.out.println("----------------------------------------------------------------");
        }

    }

    public static void main(String[] args) {
        myHotel();
    }

}
