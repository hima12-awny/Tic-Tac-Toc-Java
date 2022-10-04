import java.util.Scanner;
public class Main {

    public static String input(String st) {
        System.out.print(st);
        Scanner n = new Scanner(System.in);
        return n.nextLine();
    }

    public static void print(String st, String end) {
        if (end.equals("\n")) {
            System.out.println(st);
        }
        if (end.equals("")) {
            System.out.print(st);
        }
    }

    public static void printTable(char[][] table) {
        print("---------", "\n");
        for (int w = 0; w < 3; w++) {
            print("| ", "");
            for (int e = 0; e < 3; e++) {
                print(table[w][e] + " ", "");
            }
            print("|", "\n");
        }
        print("---------", "\n");
    }

    public static String chickTable(char[][] table) {
        boolean x = false;
        boolean o = false;
        String res = "Draw";

        for (int i = 0; i < 3; i++) {
            if (table[i][0] == 'X' && table[i][1] == 'X' && table[i][2] == 'X') {
                x = true;
            }
            if (table[0][i] == 'X' && table[1][i] == 'X' && table[2][i] == 'X') {
                x = true;
            }


            if (table[i][0] == 'O' && table[i][1] == 'O' && table[i][2] == 'O') {
                o = true;
            }
            if (table[0][i] == 'O' && table[1][i] == 'O' && table[2][i] == 'O') {
                o = true;
            }
        }

        if (table[0][0] == 'X' && table[1][1] == 'X' && table[2][2] == 'X') {
            x = true;
        }
        if (table[0][2] == 'X' && table[1][1] == 'X' && table[2][0] == 'X') {
            x = true;
        }

        if (table[0][0] == 'O' && table[1][1] == 'O' && table[2][2] == 'O') {
            o = true;
        }
        if (table[0][2] == 'O' && table[1][1] == 'O' && table[2][0] == 'O') {
            o = true;
        }

        if (o && !x) {
            res = "O wins";
        } else if (!o && x) {
            res = "X wins";
        } else if (o && x) {
            res = "Impossible";
        } else {
            int xx = 0;
            int oo = 0;
            int under = 0;
            for (char[] i : table) {
                for (char e : i) {
                    under = e == ' ' ? ++under : under;
                    xx = e == 'X' ? ++xx : xx;
                    oo = e == 'O' ? ++oo : oo;
                }
            }
            if (under > 0) {
                res = "Game not finished";
            }
            if (Math.abs(xx - oo) > 1) {
                res = "Impossible";
            }
        }
        return res;
    }

    public static Boolean inputResponse(char[][] table, char turn) {
        Scanner in = new Scanner(System.in);
        System.out.print("> ");
        int n1, n2;

        try {
            String n01 = in.next();
            n1 = Integer.parseInt(n01);

            String n02 = in.next();
            n2 = Integer.parseInt(n02);

        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return false;
        }

        if (n1 > 3 || n2 > 3 || n1 < 1 || n2 < 1) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        if (table[n1 - 1][n2 - 1] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        table[n1 - 1][n2 - 1] = turn;

        return true;
    }

    public static void main(String[] args) {
        var t = "         ";
        char[][] table = new char[3][3];
        String status;
        int CurrTurn = 1;

        int len = 0;
        // read table
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = t.charAt(len++);
            }
        }

        printTable(table);
        while (true) {

            if (CurrTurn % 2 == 0) {
                if (inputResponse(table, 'O')) {
                    printTable(table);
                    CurrTurn++;
                }
            } else {
                if (inputResponse(table, 'X')) {
                    printTable(table);
                    CurrTurn++;
                }
            }

            status = chickTable(table);
            if (status.equals("X wins") || status.equals("O wins")) {
                print(status, "");
                break;
            }


        }
    }
}
