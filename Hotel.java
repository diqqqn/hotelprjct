import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.ParseException;
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

    public static boolean checkTheSecondDateIsGreaterThanTheFirst(String[] firstDate, String[] secondDate) {
        int firstDateYear = Integer.parseInt(firstDate[2]);
        int firstDateMouth = Integer.parseInt(firstDate[1]);
        int firstDateDay = Integer.parseInt(firstDate[0]);
        LocalDate date1 = LocalDate.of(firstDateYear, firstDateMouth, firstDateDay);

        int secondDateYear = Integer.parseInt(secondDate[2]);
        int secondDateMouth = Integer.parseInt(secondDate[1]);
        int secondDateDay = Integer.parseInt(secondDate[0]);
        LocalDate date2 = LocalDate.of(secondDateYear, secondDateMouth, secondDateDay);

        if (date1.isBefore(date2)) {
            return true;
        }

        return false;
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

    public static boolean getDateRangeForStartAndEnd(String startDate, String endDate, String checkDate) {
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

    public static long returnDiffirenceBetween2date(String sDate, String eDate) {
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        long d1;
        long d2;
        long diffirence = 0;
        try {
            d1 = formater.parse(sDate).getTime();
            d2 = formater.parse(eDate).getTime();
            diffirence = Math.abs((d1 - d2) / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diffirence;
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
            if (!pattern.matcher(sDate).matches() || !pattern.matcher(eDate).matches()) {
                System.out.println("Invalid date format!");
                return;
            }

            sDateArr = sDate.split("\\.");
            eDateArr = eDate.split("\\.");
            if (!checkTheSecondDateIsGreaterThanTheFirst(sDateArr, eDateArr)) {
                System.out.println("Invalid date!");
            }
        } while (!checkTheSecondDateIsGreaterThanTheFirst(sDateArr, eDateArr));

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
            try {
                System.out.print("Enter number of beds: ");
                numberOfBed = sc.nextInt();
                if (numberOfBed <= 0 || numberOfBed >= 6) {
                    System.out.println("rooms range is from 1 to 5 beds");
                }
            } catch (Exception e) {
                System.out.println("[!] Wrong key input ...");
                System.out.println("[!] System set default bed 1 ...");
                numberOfBed = 1;
            }
        } while (numberOfBed <= 0 || numberOfBed >= 6);

        sc = new Scanner(System.in);
        do {
            System.out.print("Start date: ");
            sDate = sc.nextLine();
            System.out.print("End date: ");
            eDate = sc.nextLine();
            if (!pattern.matcher(sDate).matches() || !pattern.matcher(eDate).matches()) {
                System.out.println("Invalid date format!");
                return;
            }
            sDateArr = sDate.split("\\.");
            eDateArr = eDate.split("\\.");
            if (!checkTheSecondDateIsGreaterThanTheFirst(sDateArr, eDateArr)) {
                System.out.println("Invalid date!");
            }
        } while (!checkTheSecondDateIsGreaterThanTheFirst(sDateArr, eDateArr));

        System.out.println("Available rooms:");
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
            if (!pattern.matcher(sDate).matches() || !pattern.matcher(eDate).matches()) {
                System.out.println("Invalid date format!");
                return;
            }

            sDateArr = sDate.split("\\.");
            eDateArr = eDate.split("\\.");
            if (!checkTheSecondDateIsGreaterThanTheFirst(sDateArr, eDateArr)) {
                System.out.println("Invalid date!");
            }
        } while (!checkTheSecondDateIsGreaterThanTheFirst(sDateArr, eDateArr));

        for (int i = 0; i < rooms.length; i++) {
            if (comment[i] == null && startDate[i] == null && endDate[i] == null) {
                System.out.println(rooms[i] + ": 0 days");
            } else {
                if (getDateRangeForStartAndEnd(sDate, eDate, startDate[i])
                        || getDateRangeForStartAndEnd(sDate, eDate, endDate[i])) {
                    if (getDateRangeForStartAndEnd(sDate, eDate, startDate[i])) {
                        System.out
                                .println(rooms[i] + ": " + returnDiffirenceBetween2date(eDate, startDate[i]) + " days");
                    } else if (getDateRangeForStartAndEnd(sDate, eDate, endDate[i])) {
                        System.out.println(rooms[i] + ": " + returnDiffirenceBetween2date(sDate, endDate[i]) + " days");
                    }
                } else if (getDateRange(sDate, eDate, startDate[i])
                        || getDateRange(sDate, eDate, endDate[i])) {
                    System.out.println(rooms[i] + ": " + returnDiffirenceBetween2date(sDate, eDate) + " days");
                }
            }
        }
    }

    public static void checkoutRoom() {
        sc = new Scanner(System.in);
        System.out.print("Enter room number for checkout: ");
        String checkoutRoomNumber = sc.nextLine();
        int checkoutRoomIndex = -1;

        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].equals(checkoutRoomNumber)) {
                checkoutRoomIndex = i;
                break;
            }
        }
        if (checkoutRoomIndex != -1) {
            if (startDate[checkoutRoomIndex] != null && endDate[checkoutRoomIndex] != null
                    && comment[checkoutRoomIndex] != null) {
                startDate[checkoutRoomIndex] = null;
                endDate[checkoutRoomIndex] = null;
                comment[checkoutRoomIndex] = null;
                System.out.println("[+] " + rooms[checkoutRoomIndex] + " room now is free !");
            } else {
                System.out.println("[!] Room is free");
            }
        } else {
            System.out.println("[-] Invalid room number");
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
                    break;
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
                if (!pattern.matcher(sDate).matches() || !pattern.matcher(eDate).matches()) {
                    System.out.println("Invalid date format!");
                    return;
                }

                sDateArr = sDate.split("\\.");
                eDateArr = eDate.split("\\.");
                if (!checkTheSecondDateIsGreaterThanTheFirst(sDateArr, eDateArr)) {
                    System.out.println("Invalid date!");
                }
            } while (!checkTheSecondDateIsGreaterThanTheFirst(sDateArr, eDateArr));

            startDate[roomInd] = sDate;
            endDate[roomInd] = eDate;

            do {
                System.out.print("Enter notes: ");
                comment[roomInd] = sc.nextLine();
                if (comment[roomInd] == "" || comment[roomInd] == null) {
                    System.out.println("Please add note:");
                }
            } while (comment[roomInd] == "" || comment[roomInd] == null);
            System.out.println("Reservation was created!");
        } else {
            System.out.println("invalid room number ...");
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
