
import java.util.*;

public class sec1_1202416{

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of Players: "); // ask user to enter number of players
        int numberOfPlayer = in.nextInt(); // save the number of players as an int variable

        System.out.println("Enter the number of teams: "); // ask user to enter number of teams
        int teams = in.nextInt(); // save the number of teams as an int variable
        Player[][] players = new Player[teams][]; // place the number of teams as rows in array of player "players"

        int playersInEachTeam; // int variable for players in each team

        for (int i = 0; teams != 0; i++) { // for loop will continue looping until team is equal to zero
            int min = (numberOfPlayer / teams) - 1; // the minimum of the function
            int max = (numberOfPlayer / teams) + 1; // the maximum of the function

            if (teams == 1) // if there was only one team left
                playersInEachTeam = numberOfPlayer; // then put all the remaining players in the team

            else // if there isn't one team left
                playersInEachTeam = min + (int) (Math.random() * (max - min)); // then divide the players randomly

            players[i] = new Player[playersInEachTeam]; //put the number of columns equal to the players in each team
            numberOfPlayer -= playersInEachTeam; //after the players were placed randomly, remove those amount of players from the total number
            teams--; // after you finish one team, minus the amount of teams by one

            for (int j = 0; j < players[i].length; j++) { // loop based on how many players there are
                System.out.println("Please Enter information about players " + (j + 1) + " into team " + (i + 1)); // asking user to enter information of player
                System.out.print("Name: "); // ask user for the name
                String name = in.next(); // store name info
                System.out.print("Age: "); // ask user for the age
                int age = in.nextInt(); // store age info
                System.out.print("Height: "); // ask user for the height
                double height = in.nextDouble(); // store height info
                players[i][j] = new Player(name, age, height); // call constructor to store information in players


            }
        }
        double avg_height = avgHeight(players); // call function and store result in avg_height
        System.out.println("The Average Height for All Players is: " + avg_height); // outputting average height of players

        choosePlayer(players); // call function

        int[] asking = askPlayer(choosePlayer(players)); // call function and store result in an array of integer named asking
        if(asking[0]>asking[1]) { // if the first player had more questions answered than the other
            System.out.println("The winner is Player 1"); // print that first player won
            System.out.println("He/she has answered " + asking[0] + "/4 but player 2 has answered " + asking[1] + "/4"); // print how many questions both players answered correctly
        }
        else if(asking[0]<asking[1]) { // if the second player had more questions answered than the other
            System.out.println("The winner is Player 2"); // print that second player won
            System.out.println("He/she has answered " + asking[0] + "/4 but player 2 has answered " + asking[1] + "/4"); // print how many questions both players answered correctly
        }
        else
            System.out.println("Both Are Equal"); // if both answered the same amount of questions correctly then print both are equal


    }
    public static double avgHeight(Player[][] players){
        double sum=0, count=0; // create double variable for sum and number of players
        for(int i=0; i<players.length; i++){ // loop for length of players
            for(int j=0; j<players[i].length; j++){ // loop for amount of players
                sum+=players[i][j].getHeight(); // get the sum of heights by calling the getter of height
                count++; // count for everytime the loop will loop
            }
        }
        return sum/count; // function of average
    }

    public static Player[] choosePlayer(Player[][] players) {

        int team1 = (int)(Math.random () * players.length); // choose a random team for variable team 1
        int team2 = (int)(Math.random () * players.length); // choose a random team for variable team 2

        while (team2 == team1) { // if the teams are the same
            team2 = (int)(Math.random () * players.length); // redo the random function for the second team
        }

        int team1_player =  (int)(Math.random ()  * players[team1].length); // choose a random player for variable team1_player
        int team2_player = (int)(Math.random ()  * players[team2].length); // choose a random player for variable team2_player

        Player[] chosen = new Player[2]; // create an array with only two indexes

        chosen[0] = players[team1][team1_player]; // the first index will hold the first team and first player
        chosen[1] = players[team2][team2_player]; // the second index will hold the second team and second player

        System.out.println("Player #1 from team #" + team1 + " her/his name is: " + chosen[0].getName() + " with age " + chosen[0].getAge() + " and his/her height is: " + chosen[0].getHeight()); // print player 1's team, name, age, and height
        System.out.println("Player #2 from team #" + team2 + " her/his name is: " + chosen[1].getName() + " with age " + chosen[1].getAge() + " and his/her height is: " + chosen[1].getHeight()); // print player 2's team, name, age, and height

        return chosen; // return array of the two teams
    }

    public static int[] askPlayer(Player[] player) {
        int[] count = new int[2]; // create an array of int with 2 indexes
        for (int i = 0; i < player.length; i++) { // loop that loops the length of player array
            System.out.println("Question for Player #" + (i + 1)); // asking the first player
            for (int j = 0; j < 4; j++) { // loop for the amount of questions (it should be four questions)
                char[][] charArray = { { 'm', 'd', 'o', 'p' }, { 'i', 'f' }, { 's', 'c' }, { 'l' } }; // 2D char array that has the 9 different functions that can be used
                double[][] mathArray = { { 2, 3, 1, 5, 9, 8, 4, 7, 6 },
                        { 1.8, 2.5, -2.9, -1.5, 1.3, 1.4, 2.9, 3.6, 4.5 }, { 30, 45, 60, 90, 180, 270, 135, 180, 0 },
                        { 2, 4, 8, 16, 32, 64, 128, 256, 512 } }; // 1D math array that has all the numbers that can be used in the 9 functions
                int x = ((int) (Math.random() * 4)); // randomly choose between the first four functions and store in x
                int z = ((int) (Math.random() * charArray[x].length)); // randomly choose between the 9 functions and store in z
                char operator = charArray[x][z]; // the operator character will have a row of the first four functions and columns of the 9 functions
                double correctResult; // identifying a double called correct result
                Scanner sc = new Scanner(System.in);

                double inputResult; // identifying a double called input result for user
                if (operator == 'm') { // for the multiply function
                    double var1 = mathArray[0][((int) (Math.random() * 9))]; // have the first variable be any of the numbers from mathArray
                    double var2 = mathArray[0][((int) (Math.random() * 9))]; // have the second variable be any of the numbers from mathArray
                    System.out.println("What is the multiple of " + var1 + " and " + var2 + " ?"); // ask player what the first and second variable multiplied are equal to
                    correctResult = var1 * var2; // store the correct answer in correct result
                    inputResult = sc.nextDouble(); // store user's input in the input result
                    if (correctResult == inputResult) // if they are equal
                        count[i]++; // count the amount of correct questions answered
                } else if (operator == 'd') { // for the divide function
                    double var1 = mathArray[0][((int) (Math.random() * 9))]; // have the first variable be any of the numbers from mathArray
                    double var2 = mathArray[0][((int) (Math.random() * 9))]; // have the second variable be any of the numbers from mathArray
                    System.out.println("What is the division of " + var1 + " and " + var2 + " ?"); // ask player what the first and second variable divided are equal to
                    correctResult = (double) var1 / var2; // store the correct answer in correct result
                    inputResult = Double.parseDouble(sc.next()); // store user's input in the input result
                    if (correctResult == inputResult) // if they are equal
                        count[i]++; // count the amount of correct questions answered
                } else if (operator == 'o') { // for the mod function
                    double var1 = mathArray[0][((int) (Math.random() * 9))]; // have the first variable be any of the numbers from mathArray
                    double var2 = mathArray[0][((int) (Math.random() * 9))]; // have the second variable be any of the numbers from mathArray
                    System.out.println("What is the mod of " + var1 + " and " + var2 + " ?"); /// ask player what the first and second variable modded are equal to
                    correctResult = var1 % var2; // store the correct answer in correct result
                    inputResult = Double.parseDouble(sc.next()); // store user's input in the input result
                    if (correctResult == inputResult) // if they are equal
                        count[i]++; // count the amount of correct questions answered
                } else if (operator == 'p') { //for the power function
                    double var1 = mathArray[0][((int) (Math.random() * 9))]; // have the first variable be any of the numbers from mathArray
                    double var2 = mathArray[0][((int) (Math.random() * 9))]; // have the second variable be any of the numbers from mathArray
                    System.out.println("What is the power of " + var2 + " for " + var1 + " ?"); /// ask player what the first and second variable powered are equal to
                    correctResult = Math.pow(var1, var2);  // store the correct answer in correct result
                    inputResult = Double.parseDouble(sc.next()); // store user's input in the input result
                    if (correctResult == inputResult) // if they are equal
                        count[i]++; // count the amount of correct questions answered
                } else if (operator == 'i') { // for the ceil function
                    double var1 = mathArray[1][((int) (Math.random() * 9))]; // have the first variable be any of the numbers from mathArray
                    System.out.println("What is the ceil of " + var1 +  " ?");  // ask player what the first variable ceil is equal to
                    correctResult = Math.ceil(var1); // store the correct answer in correct result
                    inputResult = Double.parseDouble(sc.next()); // store user's input in the input result
                    if (correctResult == inputResult) // if they are equal
                        count[i]++; // count the amount of correct questions answered
                } else if (operator == 'f') { //for the floor function
                    double var1 = mathArray[1][((int) (Math.random() * 9))]; // have the first variable be any of the numbers from mathArray
                    System.out.println("What is the floor of " + var1 +  " ?");  // ask player what the first variable floor is equal to
                    correctResult = Math.floor(var1); // store the correct answer in correct result
                    inputResult = Double.parseDouble(sc.next()); // store user's input in the input result
                    if (correctResult == inputResult) // if they are equal
                        count[i]++; // count the amount of correct questions answered
                } else if (operator == 's') { // for the sin function
                    double var1 = mathArray[2][((int) (Math.random() * 9))]; // have the first variable be any of the numbers from mathArray
                    System.out.println("What is the sin of " + var1 +  " ?"); // ask player what the first variable sin is equal to
                    correctResult = Math.sin(var1); // store the correct answer in correct result
                    inputResult = Double.parseDouble(sc.next()); // store user's input in the input result
                    if (correctResult == inputResult) // if they are equal
                        count[i]++; // count the amount of correct questions answered
                } else if (operator == 'c') { // for the cosine function
                    double var1 = mathArray[2][((int) (Math.random() * 9))]; // have the first variable be any of the numbers from mathArray
                    System.out.println("What is the cos of " + var1 +  " ?"); // ask player what the first variable cos is equal to
                    correctResult = Math.cos(var1); // store the correct answer in correct result
                    inputResult = Double.parseDouble(sc.next()); // store user's input in the input result
                    if (correctResult == inputResult) // if they are equal
                        count[i]++; // count the amount of correct questions answered
                } else { // for the log10 function
                    double var1 = mathArray[3][((int) (Math.random() * 9))]; // have the first variable be any of the numbers from mathArray
                    System.out.println("What is the log10 of " + var1 +  " ?"); // ask player what the first variable cos is equal to
                    correctResult = Math.log10(var1); // store the correct answer in correct result
                    inputResult =Double.parseDouble(sc.next()); // store user's input in the input result
                    if (correctResult == inputResult)  // if they are equal
                        count[i]++; // count the amount of correct questions answered
                }
            }
        }
        return count; // return the amount of correct questions answered
    }

}


