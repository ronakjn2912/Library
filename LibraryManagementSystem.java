/*

 Variables
 Main Method Variables
 * issuerArrPointer - Points to the rows of the issuerArr.
 * arrPointer - Points to the rows of the original arr.
 * sc - Scanner object reference variable.
 * arr - Stores the bookName, authorName and copies of a particular book.
 * issuerArr - Stores the issuerName and the bookName that has been issued.
 * choice - Describes the choice of the user about a particular library operation.

 addBook Variables
 * bookName - Describes the name of the book.
 * authorName - Describes the name of the author.
 * copies - Describes the number of copies of a particular book in original arr.
 * isNull - If any arr row is null from i = 0 to i = arr.length, then it will return true else
 false.
 * k - In addBook function, traverses the original arr by storing the row number for the current
 copies for incrementing; if there exist any null value in the original arr then it helps to store
 the row number for the null row;
 * book in it; used to check if the requested bookName exists in the original arr; stores the



 issueBook Variables
 * issuerName - Describes the name of the person who issued the book.
 * bookId - Describes the row of the book from "arr" that has been issued and stored in issuerArr.
 * c - Stores the int value of "copies".
 * issuedBookRow - In issueBook function, stores the row number for the current book in the original
 arr; used to check if the requested bookName exists in the original arr;

returnBook Variables
 * checkBook - Checks if the book to be returned exists in issuerArr;
 * checkIssuerName - Checks if the book to be returned is being returned by a valid issuer in
 issuerArr;
 * p - Used to check for the book in the issuerArr if it exists;
 * issuedBookCopies - Stores the bookId.

removeBookVariables
 * z - Used to store the original arr row for removal of book.

showBookVariables
 * strings - Looping variable to show all the books from original arr.


 Book Methods
 * addBook()
 * removeBook()
 * issueBook()
 * returnBook()


 */

package Project.Library;
import java.util.Arrays;
import java.util.Scanner;
public class LibraryManagementSystem{

    static void addBook(String[][] arr, int arrPointer) {
        Scanner sc = new Scanner(System.in);

        String bookName = sc.nextLine();
        String authorName = sc.nextLine();
        String copies = sc.nextLine();

        int k;
        boolean isNull = false;

        for (k = 0; k < arr.length; k++) {
            if (arr[k][0] == null){
                isNull = true;
                break;
            }
            if (bookName.equals(arr[k][0])) {
                int c = Integer.parseInt(arr[k][2]);

                c = c + Integer.parseInt(copies);
                arr[k][2] = String.valueOf(c);
                arrPointer--;

                break;
            }
        }
        if (isNull){
            arr[k][0] = bookName;
            arr[k][1] = authorName;
            arr[k][2] = copies;

            System.out.println("You added: " + arr[k][0] + " by " + arr[k][1] + " You added " + arr[k][2] + " copies.");
        }
        else if (k == arr.length)
        {
            arr[arrPointer][0] = bookName;
            arr[arrPointer][1] = authorName;
            arr[arrPointer][2] = copies;

            System.out.println("You added: " + arr[arrPointer][0] + " by " + arr[arrPointer][1] + " You added " + arr[arrPointer][2] + " copies.");
        }
    }

    static void removeBook(String[][] arr,int arrPointer){
        Scanner sc = new Scanner(System.in);
        String bookName = sc.nextLine();

        for (int z = 0; z < arrPointer; z++) {
            if (bookName.equals(arr[z][0])) {
                arr[z][0] = null;
                arr[z][1] = null;
                arr[z][2] = null;
            }
        }
    }
    static void showBook(String[][] arr) {
        for (String[] strings : arr) {
            System.out.println(Arrays.toString(strings));
        }
    }

    /*
     *
     * Array for mapping who has which book
     * The new 2d array will contain the book name along with the issuer name.
     * */
    static int issueBook(String[][] arr, String[][] issuerArr ,int arrPointer, int issuerArrPointer){

        Scanner sc = new Scanner(System.in);

        String bookName = sc.nextLine();
        String issuerName = sc.nextLine();


        /*arr: abcd dfgh
         * i = 2
         * l = 0 1 2
         *
         * 0 0, 0 1
         * 1 0, 1 1
         *               0           1    2
         * issuerArr: abcd       xyz   dfh
         *              halosugar  ronak jain
         * */

        int bookId;
        int issuedBookRow;
        int c = 0;
        for (issuedBookRow = 0; issuedBookRow < arrPointer; issuedBookRow++) {
            if(bookName.equals(arr[issuedBookRow][0]))
            {
                issuerArr[issuerArrPointer][0] = bookName;
                c = Integer.parseInt(arr[issuedBookRow][2]);
                if(c == 0)
                {
                    break;
                }
                c = c - 1;
                arr[issuedBookRow][2] = String.valueOf(c);

                issuerArr[issuerArrPointer][1] = issuerName;

                bookId = issuedBookRow;
                issuerArr[issuerArrPointer][2] = String.valueOf(bookId);
                break;
            }
        }
        if(c == 0)
        {
            System.out.println("Out of stock!");
        }
        if(issuedBookRow == arrPointer)
        {
            System.out.println("Book doesn't exist!");
        }
        else
        {
            System.out.println("Issuer Name: " + issuerArr[issuerArrPointer][1] + "\nIssued Book: " + issuerArr[issuerArrPointer][0]);
            issuerArrPointer++;
        }
        return issuerArrPointer;
    }
    /*
     * If book exists in issuerArr then increment the copy in arr for that book and remove the
     * issuer entry for that book from issuerArr
     * If book doesnt exist then give error
     *
     * Enter an ID in issueBook method the moment we add
     * */
    static void returnBook(String[][] arr, String[][] issuerArr, int issuerArrPointer){

        Scanner sc = new Scanner(System.in);

        String bookName = sc.nextLine();
        String issuerName = sc.nextLine();
        int p;
        int checkBook = 0;
        int checkIssuerName = 0;
        for (p = 0; p <= issuerArrPointer; p++) {
            if(bookName.equals(issuerArr[p][0]))
            {
                checkBook++;
            }
            if (issuerName.equals(issuerArr[p][1]))
            {
                checkIssuerName++;
            }
            if(checkBook > 0 && checkIssuerName > 0)
            {
            /*Get the copies of the bookName that is present at index specified by the 3rd row of
             the lth column of issuerArr; from "arr"*/
                int issuedBookCopies = Integer.parseInt(issuerArr[p][2]);
                int c = Integer.parseInt(arr[issuedBookCopies][2]);
                c = c + 1;
                arr[issuedBookCopies][2]=String.valueOf(c);
                issuerArrPointer--;
                break;
            }
        }
        if(p > issuerArrPointer)
        {
            if(checkBook > 0 && checkIssuerName == 0)
            {
                System.out.println("No such issuer exists!");
            }
            else if (checkBook == 0 && checkIssuerName > 0)
            {
                System.out.println("No such book exists on that issuer name.");
            }
            else
            {
                System.out.println("Neither match.");
            }
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[][] arr = new String[5][3];
        String[][] issuerArr = new String[5][3];

        int arrPointer = 0, issuerArrPointer = 0;

        while(true){
            System.out.println("  1. Add Book" +
                    "           2. Remove Book" +
                    "           3. Issue Book" +
                    "           4. Return Book"+
                    "           5. Show Book" +
                    "           6. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter Book name,Author name and No. of copies");
                    addBook(arr, arrPointer);
                    arrPointer++;
                }
                case 2 -> {
                    System.out.println("Enter bookName you want to return: ");
                    removeBook(arr, arrPointer);
                }
                case 3 -> {
                    System.out.println("Enter book you want to issue and your name: ");
                    issuerArrPointer = issueBook(arr, issuerArr, arrPointer, issuerArrPointer);
                }
                case 4 -> {
                    System.out.println("Enter the book you want to return: ");
                    returnBook(arr, issuerArr, issuerArrPointer);
                }
                case 5 -> showBook(arr);
                case 6 -> System.out.println("AAV JO");
                default -> System.out.println("Enter Valid Choice");
            }
        }
    }
}
