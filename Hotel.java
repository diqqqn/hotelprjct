import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Hotel {
    static Scanner sc;
    static final String datePattern = "(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((?:19|20)[0-9][0-9])";
    static Pattern pattern = Pattern.compile(datePattern);
    static String[] rooms = { "101", "102", "103", "104", "201", "202", "203",
            "301", "302", "303", "401", "402", "501", "502", "601" };
    static int[] roomsBeds = { 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 5 };
    static String[] startDate = new String[rooms.length];
    static String[] endDate = new String[rooms.length];
    static String[] comment = new String[rooms.length];

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

    public static boolean getDateRange(String startDate, String endDate, String checkDate) {
        String[] checkDateArr;
        String[] checkStartDateArr;
        String[] checkEndDateArr;

        if (checkDate == null) {
            return false;
        } else {
            checkDateArr = checkDate.split("\\.");
        }
        if (startDate == null) {
            return false;
        } else {
            checkStartDateArr = startDate.split("\\.");
        }
        if (endDate == null) {
            return false;
        } else {
            checkEndDateArr = endDate.split("\\.");
        }

        int checkDateYear = Integer.parseInt(checkDateArr[2]);
        int checkDateMonth = Integer.parseInt(checkDateArr[1]);
        int checkDateday = Integer.parseInt(checkDateArr[0]);
        LocalDate myDate = LocalDate.of(checkDateYear, checkDateMonth, checkDateday);

        int startDateYear = Integer.parseInt(checkStartDateArr[2]);
        int startDateMonth = Integer.parseInt(checkStartDateArr[1]);
        int startDateday = Integer.parseInt(checkStartDateArr[0]);
        LocalDate start = LocalDate.of(startDateYear, startDateMonth, startDateday);

        int endDateYear = Integer.parseInt(checkEndDateArr[2]);
        int endDateMonth = Integer.parseInt(checkEndDateArr[1]);
        int endDateday = Integer.parseInt(checkEndDateArr[0]);
        LocalDate stop = LocalDate.of(endDateYear, endDateMonth, endDateday);

        Boolean containsToday = (!myDate.isBefore(start)) && (!myDate.isBefore(stop));
        return containsToday;
    }

    public static boolean getDateRangeForStatus(String startDate, String endDate, String checkDate) {
        String[] checkDateArr;
        String[] checkStartDateArr;
        String[] checkEndDateArr;

        if (checkDate == null) {
            return false;
        } else {
            checkDateArr = checkDate.split("\\.");
        }
        if (startDate == null) {
            return false;
        } else {
            checkStartDateArr = startDate.split("\\.");
        }
        if (endDate == null) {
            return false;
        } else {
            checkEndDateArr = endDate.split("\\.");
        }

        int checkDateYear = Integer.parseInt(checkDateArr[2]);
        int checkDateMonth = Integer.parseInt(checkDateArr[1]);
        int checkDateday = Integer.parseInt(checkDateArr[0]);
        LocalDate myDate = LocalDate.of(checkDateYear, checkDateMonth, checkDateday);

        int startDateYear = Integer.parseInt(checkStartDateArr[2]);
        int startDateMonth = Integer.parseInt(checkStartDateArr[1]);
        int startDateday = Integer.parseInt(checkStartDateArr[0]);
        LocalDate start = LocalDate.of(startDateYear, startDateMonth, startDateday);

        int endDateYear = Integer.parseInt(checkEndDateArr[2]);
        int endDateMonth = Integer.parseInt(checkEndDateArr[1]);
        int endDateday = Integer.parseInt(checkEndDateArr[0]);
        LocalDate stop = LocalDate.of(endDateYear, endDateMonth, endDateday);

        Boolean containsToday = (!myDate.isBefore(start)) && (myDate.isBefore(stop));
        return containsToday;
    }

    public static void updateRoom() {
        sc = new Scanner(System.in);
        System.out.print("Enter room number: ");
        String roomNum = sc.nextLine();
        int roomNumIndex = -1;
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].equals(roomNum)) {
                roomNumIndex = i;
                break;
            }
        }

        if (roomNumIndex == -1) {
            System.out.println("non-existent room ..");
            return;
        }

        if (startDate[roomNumIndex] == "" && endDate[roomNumIndex] == ""
                || startDate[roomNumIndex] == null && endDate[roomNumIndex] == null) {
            System.out.println("This room is free");
            return;
        }

        String sDateArr[];
        String eDateArr[];
        String sDate;
        String eDate;
        do {
            System.out.println("current start date " + startDate[roomNumIndex]);
            System.out.print("new start date: ");
            sDate = sc.nextLine();
            System.out.println("current end date " + endDate[roomNumIndex]);
            System.out.print("new end date: ");
            eDate = sc.nextLine();
            if (!pattern.matcher(sDate).matches() && !pattern.matcher(eDate).matches()) {
                System.out.println("Invalid date!");
                return;
            }

            sDateArr = sDate.split("\\.");
            eDateArr = eDate.split("\\.");
            if (sDateArr.length <= 2 && eDateArr.length <= 2) {
                System.out.println("Invalid Date:");
                return;
            }
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

        startDate[roomNumIndex] = sDate;
        endDate[roomNumIndex] = eDate;

        do {
            System.out.println("Your current note is : '" + comment[roomNumIndex] + "'");
            System.out.print("Enter your new note: ");
            comment[roomNumIndex] = sc.nextLine();
            if (comment[roomNumIndex] == "" || comment[roomNumIndex] == null) {
                System.out.println("Please add note:");
            }
        } while (comment[roomNumIndex] == "" || comment[roomNumIndex] == null);

    }

    public static void findRoom() {
        sc = new Scanner(System.in);

        String sDateArr[];
        String eDateArr[];
        String sDate;
        String eDate;
        int numberOfBed = -1;

        do {
            System.out.print("Enter number of beds: ");
            numberOfBed = sc.nextInt();
        } while (numberOfBed <= 0 && numberOfBed >= 5);
        sc = new Scanner(System.in);
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

        for (int ind = 0; ind < rooms.length; ind++) {
            if (roomsBeds[ind] == numberOfBed) {

                if (comment[ind] == null && startDate[ind] == null && endDate[ind] == null) {
                    System.out.println(rooms[ind]);
                } else {
                    if (getDateRange(sDate, eDate, startDate[ind]) && getDateRange(sDate, eDate, endDate[ind])) {
                        System.out.println(rooms[ind]);
                    }
                }

            }
        }

    }

    public static void status() {
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
            if (sDateArr.length <= 2 && eDateArr.length <= 2) {
                System.out.println("Invalid Date:");
                return;
            }
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
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        long d1;
        long d2;
        long total = 0;
        for (int i = 0; i < rooms.length; i++) {
            if (comment[i] == null && startDate[i] == null && endDate[i] == null) {
                System.out.println(rooms[i] + ": 0 days");
            } else {
                if (getDateRangeForStatus(sDate, eDate, startDate[i])
                        || getDateRangeForStatus(sDate, eDate, endDate[i])) {

                    System.out.println(rooms[i]);
                }
            }
        }
    }

    public static void checkoutRoom() {
        sc = new Scanner(System.in);
        System.out.print("Enter room num for checkout: ");
        String checkoutRoomNumber = sc.nextLine();
        int checkoutRoomIndex = -1;

        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].equals(checkoutRoomNumber)) {
                checkoutRoomIndex = rooms[i].indexOf(checkoutRoomNumber);
            }
        }
        if (checkoutRoomIndex != -1) {
            if (startDate[checkoutRoomIndex] != null && endDate[checkoutRoomIndex] != null
                    && comment[checkoutRoomIndex] != null) {
                startDate[checkoutRoomIndex] = null;
                endDate[checkoutRoomIndex] = null;
                comment[checkoutRoomIndex] = null;
                System.out.println(rooms[checkoutRoomIndex] + "[+] room now is free !");
            } else {
                System.out.println("Room is free");
            }
        } else {
            System.out.println("Invalid room number");
        }

    }

    public static void listFreeRooms() {
        for (int i = 0; i < rooms.length; i++) {
            if (startDate[i] == null && endDate[i] == null && comment[i] == null) {
                System.out.println("Free room - " + rooms[i]);
            }
        }
    }

    public static void makeReservation() {
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
                if (sDateArr.length <= 2 && eDateArr.length <= 2) {
                    System.out.println("Invalid Date:");
                    return;
                }
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
        // SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        // long d1;
        // long d2;
        // try {
        // d1 = formater.parse("1.1.2001").getTime();
        // d2 = formater.parse("1.1.2002").getTime();
        // System.out.println(Math.abs((d1 - d2) / (1000 * 60 * 60 * 24)));
        // } catch (ParseException e) {
        // e.printStackTrace();
        // }

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
