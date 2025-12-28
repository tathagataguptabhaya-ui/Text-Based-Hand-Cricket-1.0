import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class HandCricket 
{
    static int consecutiveWickets=0;
    static int h_team[]=new int[10];
    static int c_team[]=new int[10];
    static int target;//checks the target
    static int h_team_score=0;//takes the sum of all the players in the h_team
    static int c_team_score=0;////takes the sum of all the players in the c_team
    static String h_choice;//human_player's choice after winning toss
    static String c_choice;//cpu player's choice in the ball after winning toss
    static int h_wickets_lost=0;//keeps track of h_team's wickets
    static int c_wickets_lost=0;//keeps track of c_team's wickets
    static int h_wins=0;//keeps track of h_team's win in a series
    static int cpu_wins=0;//keeps track of c_team's win in a series
    static int wickets=0;
    static int match_ties=0;
    static int lastNotOut=0;
    static int totalMatches = 0, totalHWin = 0, totalCWin = 0, totalMTies = 0;
    static int totalHSeries = 0, totalCSeries = 0, totalSTies = 0;

    static void dualPrint(String text) {
        System.out.println(text); // Print to console
        try (FileWriter fw = new FileWriter("Cricket_History.txt", true);
            PrintWriter pw = new PrintWriter(fw)) {
            pw.println(text); // Append to file
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    static void updateFileStructure() {
        StringBuilder previousSummaries = new StringBuilder();
        File file = new File("Cricket_History.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean summaryStarted = false;
                while ((line = reader.readLine()) != null) {
                    // This flag ensures we only grab the "SESSION STARTED" parts and below
                    if (line.contains("SESSION STARTED AT")) summaryStarted = true;
                    if (summaryStarted) {
                        previousSummaries.append(line).append("\n");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading history.");
            }
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter("Cricket_History.txt"))) {
            pw.println("==HISTORICAL STATS==");
            pw.println("1. matches played: " + totalMatches);
            pw.println("2. matches won by humans: " + totalHWin);
            pw.println("3. matches won by cpu: " + totalCWin);
            pw.println("4. MATCHES TIED: " + totalMTies);
            pw.println("5. SERIES WON BY HUMANS: " + totalHSeries);
            pw.println("6. SERIES WON BY CPU: " + totalCSeries);
            pw.println("7. SERIES TIED: " + totalSTies);
            pw.println("=".repeat(40));
            pw.print(previousSummaries.toString());
        } catch (IOException e) {
            System.out.println("Error updating file structure.");
        }
    }
    static void loadHistoricalStats() {
        File file = new File("stats.txt");
        if (!file.exists()) return;
        try (Scanner fs = new Scanner(file)) {
            if (fs.hasNextInt()) totalMatches = fs.nextInt();
            if (fs.hasNextInt()) totalHWin = fs.nextInt();
            if (fs.hasNextInt()) totalCWin = fs.nextInt();
            if (fs.hasNextInt()) totalMTies = fs.nextInt();
            if (fs.hasNextInt()) totalHSeries = fs.nextInt();
            if (fs.hasNextInt()) totalCSeries = fs.nextInt();
            if (fs.hasNextInt()) totalSTies = fs.nextInt();
        } catch (Exception e) {
            System.out.println("Could not load history.");
        }
    }

    static void saveHistoricalStats() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("stats.txt"))) {
            pw.println(totalMatches + " " + totalHWin + " " + totalCWin + " " + totalMTies + " " +
                       totalHSeries + " " + totalCSeries + " " + totalSTies);
        } catch (IOException e) {
            System.out.println("Could not save history.");
        }
    }
    public static void main(String[] args)
    {
    loadHistoricalStats();
    SecureRandom  sr = new SecureRandom() ;
    Scanner sc = new Scanner(System.in);
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm a");
    dualPrint("\n" + "=".repeat(40));
    dualPrint("SESSION STARTED AT " + now.format(dtf));


    System.out.println("WELCOME TO THE PAYTM HUMAN VS AI INFINITE BALL IN THEORY SERIES LIVE FROM YOUR OWN COMPUTER MEMORY SPONSORED BY JDK 25 AND VSCODE 2025-FOREVER");
    System.out.println("Enter the number of wickets you want to play with ");
    wickets=sc.nextInt();
    while(wickets>10||wickets==0)
    {
        System.out.println("CANNOT EXCEED 10 wickets or BE LESS THAN 0 wicket. PLEASE ENTER THE NUMBER OF WICKETS AGAIN!!!");
        wickets=sc.nextInt();
    }
    sc.nextLine();
    dualPrint("wickets per match " + wickets);

    for(int io=0;io<wickets;io++)
    {
        h_team[io]=0;
    }
    for(int jo=0;jo<wickets;jo++)
    {
        c_team[jo]=0;
    }
    System.out.println("Please state the amount of matches you want your memory to suffer from: ");
    int m=sc.nextInt();
    dualPrint("series of " + m + " matches");
    sc.nextLine();
    for(int i = 1; i<=m;i++)
    {
            System.out.println("WELCOME TO MATCH. I AM R@V1 SH@5TR1 LIVE FROM THE COMPUTER MAIN MEMORY AND HERE IS THE TOSS MASTER. WHAT DOES THE TOSS MACHINE HAVE FOR US TODAY"+ i);
            if (h_wins==(int)(m/2) && cpu_wins==((int)(m/2)) )
            {
                String msg_SD[]={"IT’S THE BIG ONE, THE FINAL FRONTIER, THE WINNER TAKES IT ALL-----WELCOME TO THE GRAND FINALE!","THE ATMOSPHERE IS ELECTRIC, THE TENSION IS PALPABLE... IT’S TIME TO RUMBLE!","LOOK AT THE CROWD, LISTEN TO THE NOISE! IT’S PANDEMONIUM HERE FOR THE FINAL SHOWDOWN!",String.format("IT’S %d-%d, IT’S THE GRAND STAGE, AND SOMEONE IS ABOUT TO BECOME A HERO!", h_wins, cpu_wins)};
                System.out.println(msg_SD[sr.nextInt(msg_SD.length)]);
            }
            else if(h_wins<cpu_wins)
            {
                System.out.println("CAN THE HUMANS COME BACK FROM GETTING OWNED IN THE LAST MATCH BY WHAT CAN BE DESCRIBED AS COMPUTAIONAL BRILLIANCE??");
            }
            else if(h_wins>cpu_wins)
            {
                System.out.println("DEFEATING THE HUMAN IS NOT AN EASY TASK AS THE SCIENTISTS THOUGHT IT WOULD BE.CAN THEY CALCULATE THEIR WAY BACK INTO THIS SERIES??ONLY TIME WILL TELL");
            }
            else if((h_wins==cpu_wins)&&(h_wins<((int)(m/2)-1))&&(h_wins!=0))
            {
                System.out.println("OOH THE SERIES HAS BEEN LEVELLED IT SEEMS!! CRACKER OF A MATCH THIS WILL BE!!!");
            }
            else if (((h_wins>((int)(m/2)-1))||(cpu_wins>((int)(m/2)+1)))&&(h_wins!=0&&cpu_wins!=0))
            {
                System.out.println("Nothing but pride and consolation is on the line as the series has been decided already.");
            }

            int a=toss();
            if(a==1)//if human wins
            {
            System.out.println("O captain, my captain, what do you choose to do first?");
            while (true) 
            {
                h_choice = sc.nextLine().trim().toUpperCase(); // Clean the input
                if (h_choice.equalsIgnoreCase("BAT") || h_choice.equalsIgnoreCase("BOWL")) {
                    break; // Valid input! Exit the loop
                }
                else 
                {
                    System.out.println("INVALID CHOICE! Please enter 'BAT' or 'BOWL':");
                }
            }
            if(h_choice.equalsIgnoreCase("BAT"))
            {
                System.out.println("You won the toss and grace the computer world by batting first");
                System.out.println("PLAY ON!!!!");
                h_bat();
                consecutiveWickets=0;
                target=h_team_score+1;
                System.out.println("The target for the computer is "+target);
                h_bowl(target);
                summary(h_choice);
            }
            if(h_choice.equalsIgnoreCase("BOWL"))
            {
                System.out.println("You won the toss and grace the computer world by bowling first");
                System.out.println("PLAY ON!!!!!!!");
                h_bowl();
                consecutiveWickets=0;
                target=c_team_score+1;
                System.out.println("The target for the human is "+target);
                h_bat(target);
                summary(h_choice);
            }
        }
        else if(a==2)//cpu wins
        {
            System.out.println("Beep Boop Beep Boop");
            System.out.println("Win Toss: affirmative");
            c_choice=sr.nextBoolean() ? "BAT" : "BOWL";
            if(c_choice.equalsIgnoreCase("BAT"))
            {
                System.out.println("Cpu wins the toss and chooses to bat");
                System.out.println("PLAY ON!!!!!");
                h_bowl();
                consecutiveWickets=0;
                target=c_team_score+1;
                System.out.println("The target for the human is "+target);
                h_bat(target);
                summaryCPU(c_choice);
            }
            else
            {
                System.out.println("Cpu wins the toss and chooses to bowl");
                System.out.println("PLAY ON!!!!");
                h_bat();
                consecutiveWickets=0;
                target=h_team_score+1;
                System.out.println("The target for the computer is "+target);
                h_bowl(target);
                summaryCPU(c_choice);
            }
        }
        java.util.Arrays.fill(h_team, 0);
        java.util.Arrays.fill(c_team, 0);
        h_team_score=0;
        c_team_score=0;
        h_wickets_lost=0;
        c_wickets_lost=0;
    }
    if(h_wins>cpu_wins)
    {
        totalHSeries+=1;
        dualPrint("HUMANS ARE THE VICTORS. THEY STAND TALL AFTER DEFEATING THEIR CPU SLAVES BY A MARGIN OF "+h_wins+"-"+cpu_wins);
        if(cpu_wins==0)
        {
            System.out.println("Damn son ya really know the randomness of a computer. You just whitewashed your machine just like Gopi Bahu!!!");
        }
    }
    else if(cpu_wins>h_wins)
    {
        dualPrint("HUMAN!! BOW DOWN TO YOUR MACHINE!!! YOU GOT TERMINATED BY A MARGIN OF"+cpu_wins+"-"+h_wins);
        totalCSeries+=1;
        if(h_wins==0)
        {
            System.out.println("Damn son ya got toasted by a computer. You just got whitewashed by your machine but atleast you are not the English playing an Ashes down under!!!");
        }
    }
    else
    {
        System.out.println("After everything that happened, I must say I would have felt bad if this series had a winner.WHAT A THRILLING FINISH");
        totalSTies+=1;
    }
    totalMatches += m;
    totalHWin += h_wins;
    totalCWin += cpu_wins;
    totalMTies += match_ties;
    dualPrint("\n==HISTORICAL STATS==");
    dualPrint("1. matches played: " + totalMatches);
    dualPrint("2. matches won by humans: " + totalHWin);
    dualPrint("3. matches won by cpu: " + totalCWin);
    dualPrint("4. MATCHES TIED: " + totalMTies);
    dualPrint("5. SERIES WON BY HUMANS: " + totalHSeries);
    dualPrint("6. SERIES WON BY CPU: " + totalCSeries);
    dualPrint("7. SERIES TIED: " + totalSTies);

        saveHistoricalStats();
        updateFileStructure();
    }

    static int toss()
    {
        SecureRandom sr1 = new SecureRandom();
        return sr1.nextBoolean() ? 1:2;
    }

    static void h_bat()
    {
        SecureRandom sr2= new SecureRandom();
        Scanner sc = new Scanner(System.in);
        int i=0;
        while(h_wickets_lost<wickets)
        {
            int h_ch=sc.nextInt();
            int c_ch= 1 + sr2.nextInt(6);
            if(h_ch>0 && h_ch<7)
            {
                if(h_ch!=c_ch)
                {
                    if(consecutiveWickets>0)
                    {
                        consecutiveWickets=0;
                    }
                    if(h_ch==4)
                    {
                        String msg4[]={"THATS GONE TO THE BOUNDARY LIKE A TRACER BULLET!!!!!!","HERE,COME THE BOWLER, AND HERE GOES THE BOWLER!!4 RUNS","FOUR OF THE FINEST THROUGH THE COVERS","THATS A SHOT WORTH FRAMING IN THE LOUVRE","CREAMED THROUGH THE RAM FOR A FOUR","THAT SHOT WAS SO IMMACULATE THAT THIS STRING TOOK ONLY 4 BYTES TO PRINT(JUST A JOKE)","1 2 3 4, AND THE BOWLER'S GONE FOR A STROLL"};
                        System.out.println(msg4[sr2.nextInt(msg4.length)]);
                    }
                    else if(h_ch==6)
                    {
                        String msg6[]={"THAT'S GONE MILES INTO THE BYTE FAN STANDS FOR A MAXIMUM!!!!!","THAT IS ABSOLUTUELY MASSIVE!!!","GONE INTO THE NIGHT SKY AND ITS NEVER COMING BACK","NOW THATS 6 BYTES THAT THE PLAYER HAS FOREVER SENT TO THE GARBAGE COLLECTOR!!!!"};
                        System.out.println(msg6[sr2.nextInt(msg6.length)]);
                    }
                    h_team[i]+=h_ch;
                    h_team_score+=h_ch;
                    System.out.println("The score is "+h_team_score+"/"+h_wickets_lost);
                }
                else
                {
                    consecutiveWickets+=1;
                    String msgW[] ={"THE UMPIRE SAYS THAT THE PLAYER'S ALOCATION TIME IS OVER!!! OUT IT GOES TO THE GARBAGE COLLECTOR","OH WHAT A DELIVERY,THAT JUST RIPPED THE STUMP IN HALF!!!!!","AND THE FINGER IS RAISED. THE BATSMAN DID EVERYTHING BUT COULD NOT KEEP THE BALL AWAY FROM HIS PADS!!!","EDGED AND GONE!!!!!!!","WHAT A SENSATIONAL CATCH OFF AN OTHERWISE AVERAGE DELIVERY","THE BATSMAN AND THE STUMPS ARE GONE WITH THE WIND!!!!!!!","THIS BALL HAD THE NAME OF THE BATSMAN.","THE DREADED FINGER HAS BEEN RAISED BY THE UMPIRE,OUT GOES THE BATSMAN BACK TO HIS PAVILLION"};
                    System.out.println(msgW[sr2.nextInt(msgW.length)]);
                    System.out.println("The batsman scored "+h_team[i]+" runs");
                    if(h_team[i]==0)
                    {
                        System.out.println("THE BATSMAN's OUT FOR A DUCK");
                    }
                    if(h_team[i]==0 && consecutiveWickets==2)
                    {
                        System.out.println("THE BOWLER'S ON A HATTRICK");
                    }
                    else if(h_team[i]==0 && consecutiveWickets==3)
                    {
                        System.out.println("THE BOWLER'S GOT A  HATTRICK!!!!WHAT A BRILLIANT SPELL");
                        consecutiveWickets=0;
                    }
                    else if(h_team[i]>=50 && h_team[i]<100)
                    {
                        System.out.println("That was a great innings as the player departs for a well made half century!!");
                    }
                    else if(h_team[i]>=70 && h_team[i]<100)
                    {
                        System.out.println("Great knock but perhaps the plaer will not be happy as it wil think it missed out on the big score today!!");
                    }
                    else if(h_team[i]>=100)
                    {
                        System.out.println("What a knock!!!The player departs for a well made century and has helped his team to get to a dominating position in this match");
                    }
                    h_wickets_lost+=1;
                    System.out.println("The score is "+ h_team_score+"/"+ h_wickets_lost);
                    i+=1;
                }
            }
            else
            {
                System.out.println("Please enter a run between 1-6");
                continue;
            }
        }
    }

    static void h_bat(int target)
    {
        SecureRandom sr2= new SecureRandom();
        Scanner sc = new Scanner(System.in);
        int i=0;
        while(h_wickets_lost<wickets && h_team_score<target)
        {
            int h_ch=sc.nextInt();
            int c_ch= 1 + sr2.nextInt(6);
            if(h_ch>0 && h_ch<7)
            {
                if(h_ch!=c_ch)
                {
                    if(consecutiveWickets>0)
                    {
                        consecutiveWickets=0;
                    }
                    if(h_ch==4)
                    {
                        String msg4[]={"THATS GONE TO THE BOUNDARY LIKE A TRACER BULLET!!!!!!","HERE,COME THE BOWLER, AND HERE GOES THE BOWLER!!4 RUNS","FOUR OF THE FINEST THROUGH THE COVERS","THATS A SHOT WORTH FRAMING IN THE LOUVRE","CREAMED THROUGH THE RAM FOR A FOUR","THAT SHOT WAS SO IMMACULATE THAT THIS STRING TOOK ONLY 4 BYTES TO PRINT(JUST A JOKE)","1 2 3 4, AND THE BOWLER'S GONE FOR A STROLL"};
                        System.out.println(msg4[sr2.nextInt(msg4.length)]);
                    }
                    else if(h_ch==6)
                    {
                        String msg6[]={"THAT'S GONE MILES INTO THE BYTE FAN STANDS FOR A MAXIMUM!!!!!","THAT IS ABSOLUTUELY MASSIVE!!!","GONE INTO THE NIGHT SKY AND ITS NEVER COMING BACK","NOW THATS 6 BYTES THAT THE PLAYER HAS FOREVER SENT TO THE GARBAGE COLLECTOR!!!!"};
                        System.out.println(msg6[sr2.nextInt(msg6.length)]);
                    }
                    h_team[i]+=h_ch;
                    h_team_score+=h_ch;
                    System.out.println("The score is "+h_team_score+"/"+h_wickets_lost);
                    System.out.println("The team need "+(target-h_team_score)+" runs to win");
                }
                else
                {
                    consecutiveWickets+=1;
                    String msgW[] ={"THE UMPIRE SAYS THAT THE PLAYER'S ALOCATION TIME IS OVER!!! OUT IT GOES TO THE GARBAGE COLLECTOR","OH WHAT A DELIVERY,THAT JUST RIPPED THE STUMP IN HALF!!!!!","AND THE FINGER IS RAISED. THE BATSMAN DID EVERYTHING BUT COULD NOT KEEP THE BALL AWAY FROM HIS PADS!!!","EDGED AND GONE!!!!!!!","WHAT A SENSATIONAL CATCH OFF AN OTHERWISE AVERAGE DELIVERY","THE BATSMAN AND THE STUMPS ARE GONE WITH THE WIND!!!!!!!","THIS BALL HAD THE NAME OF THE BATSMAN.","THE DREADED FINGER HAS BEEN RAISED BY THE UMPIRE,OUT GOES THE BATSMAN BACK TO HIS PAVILLION"};
                    System.out.println(msgW[sr2.nextInt(msgW.length)]);
                    System.out.println("The batsman scored "+h_team[i]+" runs");
                    if(h_team[i]==0)
                    {
                        System.out.println("THE BATSMAN's OUT FOR A DUCK");
                    }
                    if(h_team[i]==0 && consecutiveWickets==2)
                    {
                        System.out.println("THE BOWLER'S ON A HATTRICK");
                    }
                    else if(h_team[i]==0 && consecutiveWickets==3)
                    {
                        System.out.println("THE BOWLER'S GOT A  HATTRICK!!!!WHAT A BRILLIANT SPELL");
                        consecutiveWickets=0;
                    }
                    else if(h_team[i]>=50 && h_team[i]<100)
                    {
                        System.out.println("That was a great innings as the player departs for a well made half century!!");
                    }
                    else if(h_team[i]>=70 && h_team[i]<100)
                    {
                        System.out.println("Great knock but perhaps the plaer will not be happy as it wil think it missed out on the big score today!!");
                    }
                    else if(h_team[i]>=100)
                    {
                        System.out.println("What a knock under pressure!!!The player departs for a well made century and has helped his team to get closer to the target in this match");
                    }
                    h_wickets_lost+=1;
                    System.out.println("The score is "+ h_team_score+"/"+ h_wickets_lost);
                    i+=1;
                    System.out.println("Humans need "+(target-h_team_score)+" runs to win");
                }
            }
            else
            {
                System.out.println("Please enter a run between 1-6");
                continue;
            }
        }
        if(h_team_score>=target)
        {
            lastNotOut=i;
        }
    }

    static void h_bowl()
    {
        SecureRandom sr2= new SecureRandom();
        Scanner sc = new Scanner(System.in);
        int i=0;
        while(c_wickets_lost<wickets)
        {
            int h_ch=sc.nextInt();
            int c_ch= 1 + sr2.nextInt(6);
            if(h_ch>0 && h_ch<7)
            {
                if(h_ch!=c_ch)
                {
                    if(consecutiveWickets>0)
                    {
                        consecutiveWickets=0;
                    }
                    if(c_ch==4)
                    {
                        String msg4[]={"THATS GONE TO THE BOUNDARY LIKE A TRACER BULLET!!!!!!","HERE,COME THE BOWLER, AND HERE GOES THE BOWLER!!4 RUNS","FOUR OF THE FINEST THROUGH THE COVERS","THATS A SHOT WORTH FRAMING IN THE LOUVRE","CREAMED THROUGH THE RAM FOR A FOUR","THAT SHOT WAS SO IMMACULATE THAT THIS STRING TOOK ONLY 4 BYTES TO PRINT(JUST A JOKE)","1 2 3 4, AND THE BOWLER'S GONE FOR A STROLL"};
                        System.out.println(msg4[sr2.nextInt(msg4.length)]);
                    }
                    else if(c_ch==6)
                    {
                        String msg6[]={"THAT'S GONE MILES INTO THE BYTE FAN STANDS FOR A MAXIMUM!!!!!","THAT IS ABSOLUTUELY MASSIVE!!!","GONE INTO THE NIGHT SKY AND ITS NEVER COMING BACK","NOW THATS 6 BYTES THAT THE PLAYER HAS FOREVER SENT TO THE GARBAGE COLLECTOR!!!!"};
                        System.out.println(msg6[sr2.nextInt(msg6.length)]);
                    }
                    c_team[i]+=c_ch;
                    c_team_score+=c_ch;
                    System.out.println("The score is "+c_team_score+"/"+c_wickets_lost);
                }
                else
                {
                    consecutiveWickets+=1;
                    String msgW[] ={"THE PLAYER SAYS THAT THE RAM'S ALOCATION TIME IS OVER!!! OUT IT GOES TO THE GARBAGE COLLECTOR","OH WHAT A DELIVERY,THAT JUST RIPPED THE STUMP IN HALF!!!!!","AND THE FINGER IS RAISED. THE BATSMAN DID EVERYTHING BUT COULD NOT KEEP THE BALL AWAY FROM HIS PADS!!!","EDGED AND GONE!!!!!!!","WHAT A SENSATIONAL CATCH OFF AN OTHERWISE AVERAGE DELIVERY","THE BATSMAN AND THE STUMPS ARE GONE WITH THE WIND!!!!!!!","THIS BALL HAD THE NAME OF THE BATSMAN.","THE DREADED FINGER HAS BEEN RAISED BY THE UMPIRE,OUT GOES THE BATSMAN BACK TO HIS PAVILLION"};
                    System.out.println(msgW[sr2.nextInt(msgW.length)]);
                    System.out.println("The batsman scored "+c_team[i]+" runs");
                    if(c_team[i]==0)
                    {
                        System.out.println("THE BATSMAN's OUT FOR A DUCK");
                    }
                    if(c_team[i]==0 && consecutiveWickets==2)
                    {
                        System.out.println("THE BOWLER'S ON A HATTRICK");
                    }
                    else if(c_team[i]==0 && consecutiveWickets==3)
                    {
                        System.out.println("THE BOWLER'S GOT A  HATTRICK!!!!WHAT A BRILLIANT SPELL");
                        consecutiveWickets=0;
                    }
                    else if(c_team[i]>=50 && c_team[i]<100)
                    {
                        System.out.println("That was a great innings as the player departs for a well made half century!!");
                    }
                    else if(c_team[i]>=70 && c_team[i]<100)
                    {
                        System.out.println("Great knock but perhaps the plaer will not be happy as it wil think it missed out on the big score today!!");
                    }
                    else if(c_team[i]>=100)
                    {
                        System.out.println("What a knock!!!The player departs for a well made century and has helped his team to get to a dominating position in this match");
                    }
                    c_wickets_lost+=1;
                    System.out.println("The score is "+ c_team_score+"/"+ c_wickets_lost);
                    i+=1;
                }
            }
            else
            {
                System.out.println("Please enter a run between 1-6");
                continue;
            }
        }
    }

    static void h_bowl(int target)
    {
        SecureRandom sr2= new SecureRandom();
        Scanner sc = new Scanner(System.in);
        int i=0;
        while(c_wickets_lost<wickets && c_team_score<target)
        {
            int h_ch=sc.nextInt();
            int c_ch= 1 + sr2.nextInt(6);
            if(h_ch>0 && h_ch<7)
            {
                if(h_ch!=c_ch)
                {
                    if(consecutiveWickets>0)
                    {
                        consecutiveWickets=0;
                    }
                    if(c_ch==4)
                    {
                        String msg4[]={"THATS GONE TO THE BOUNDARY LIKE A TRACER BULLET!!!!!!","HERE,COME THE BOWLER, AND HERE GOES THE BOWLER!!4 RUNS","FOUR OF THE FINEST THROUGH THE COVERS","THATS A SHOT WORTH FRAMING IN THE LOUVRE","CREAMED THROUGH THE RAM FOR A FOUR","THAT SHOT WAS SO IMMACULATE THAT THIS STRING TOOK ONLY 4 BYTES TO PRINT(JUST A JOKE)","1 2 3 4, AND THE BOWLER'S GONE FOR A STROLL"};
                        System.out.println(msg4[sr2.nextInt(msg4.length)]);
                    }
                    else if(c_ch==6)
                    {
                        String msg6[]={"THAT'S GONE MILES INTO THE BYTE FAN STANDS FOR A MAXIMUM!!!!!","THAT IS ABSOLUTUELY MASSIVE!!!","GONE INTO THE NIGHT SKY AND ITS NEVER COING BACK","NOW THATS 6 BYTES THAT THE PLAYER HAS FOREVER SENT TO THE GARBAGE COLLECTOR!!!!",""};
                        System.out.println(msg6[sr2.nextInt(msg6.length)]);
                    }
                    c_team[i]+=c_ch;
                    c_team_score+=c_ch;
                    System.out.println("The score is "+c_team_score+"/"+c_wickets_lost);
                    System.out.println("CPU need "+(target-c_team_score)+" runs to win");
                }
                else
                {
                    consecutiveWickets+=1;
                    String msgW[] ={"THE PLAYER SAYS THAT THE RAM'S ALOCATION TIME IS OVER!!! OUT IT GOES TO THE GARBAGE COLLECTOR","OH WHAT A DELIVERY,THAT JUST RIPPED THE STUMP IN HALF!!!!!","AND THE FINGER IS RAISED. THE BATSMAN DID EVERYTHING BUT COULD NOT KEEP THE BALL AWAY FROM HIS PADS!!!","EDGED AND GONE!!!!!!!","WHAT A SENSATIONAL CATCH OFF AN OTHERWISE AVERAGE DELIVERY","THE BATSMAN AND THE STUMPS ARE GONE WITH THE WIND!!!!!!!","THIS BALL HAD THE NAME OF THE BATSMAN.","THE DREADED FINGER HAS BEEN RAISED BY THE UMPIRE,OUT GOES THE BATSMAN BACK TO HIS PAVILLION"};
                    System.out.println(msgW[sr2.nextInt(msgW.length)]);
                    System.out.println("The batsman scored "+c_team[i]+" runs");
                    if(c_team[i]==0)
                    {
                        System.out.println("THE BATSMAN's OUT FOR A DUCK");
                    }
                    if(c_team[i]==0 && consecutiveWickets==2)
                    {
                        System.out.println("THE BOWLER'S ON A HATTRICK");
                    }
                    else if(c_team[i]==0 && consecutiveWickets==3)
                    {
                        System.out.println("THE BOWLER'S GOT A  HATTRICK!!!!WHAT A BRILLIANT SPELL");
                        consecutiveWickets=0;
                    }
                    else if(c_team[i]>=50 && c_team[i]<100)
                    {
                        System.out.println("That was a great innings as the player departs for a well made half century!!");
                    }
                    else if(c_team[i]>=70 && c_team[i]<100)
                    {
                        System.out.println("Great knock but perhaps the plaer will not be happy as it wil think it missed out on the big score today!!");
                    }
                    else if(c_team[i]>=100)
                    {
                        System.out.println("What a knock!!!The player departs for a well made century and has helped his team to get to the target in this match");
                    }
                    c_wickets_lost+=1;
                    System.out.println("The score is "+ c_team_score+"/"+ c_wickets_lost);
                    i+=1;
                    System.out.println("CPU need "+(target-c_team_score)+" runs to win");
                }
            }
            else
            {
                System.out.println("Please enter a run between 1-6");
                continue;
            }
        }
        if(c_team_score>=target)
        {
            lastNotOut=i;
        }
    }

    static void summary(String h_choice)
    {
        if(h_choice.equalsIgnoreCase("BAT"))
        {
            if(h_team_score>c_team_score)
            {
                dualPrint("----------------------------");
                dualPrint(String.format("|   HUMAN          %3d     |%n", h_team_score));
                dualPrint(String.format("|   CPU            %3d     |%n", c_team_score));
                dualPrint("----------------------------");
                dualPrint("HUMANS WON BY " + (h_team_score - c_team_score) + " RUNS");
                h_wins+=1;
                if(h_wins>cpu_wins)
                {
                    System.out.println("Humans lead the series "+h_wins+"-"+cpu_wins+"\n");
                }
                else if(cpu_wins>h_wins)
                {
                    System.out.println("CPU lead the series "+cpu_wins+"-"+h_wins+"\n");
                }
                else
                {
                    System.out.println("THE SERIES HAS BEEN LEVELLED");
                }
            }        
            else if(c_team_score>h_team_score)
            {
                System.out.println("The batter scored "+c_team[lastNotOut]+" runs");
                dualPrint("----------------------------");
                dualPrint(String.format("| %-15s %7s |%n", "HUMAN", h_team_score));
                dualPrint(String.format("| %-15s %7s |%n", "CPU", c_team_score + "/" + c_wickets_lost));
                dualPrint("----------------------------");
                int wicketMargin = wickets - c_wickets_lost;
                dualPrint("CPU WON BY " + wicketMargin + (wicketMargin == 1 ? " WICKET" : " WICKETS"));
                cpu_wins+=1;
                if(h_wins>cpu_wins)
                {
                    System.out.println("Humans lead the series "+h_wins+"-"+cpu_wins+"\n");
                }
                else if(cpu_wins>h_wins)
                {
                    System.out.println("CPU lead the series "+cpu_wins+"-"+h_wins+"\n");
                }
                else
                {
                    System.out.println("THE SERIES HAS BEEN LEVELLED");
                }
            }
            else
            {
                dualPrint("----------------------------");
                dualPrint(String.format("|  %-10s %8d     |%n", "HUMAN", h_team_score));
                dualPrint(String.format("|  %-10s %8d     |%n", "CPU", c_team_score));
                dualPrint("----------------------------");
                dualPrint("MATCH TIED");
                match_ties+=1;
                if(h_wins>cpu_wins)
                {
                    System.out.println("Humans lead the series "+h_wins+"-"+cpu_wins+"\n");
                }
                else if(cpu_wins>h_wins)
                {
                    System.out.println("CPU lead the series "+cpu_wins+"-"+h_wins+"\n");
                }
                else
                {
                    System.out.println("THE SERIES HAS BEEN LEVELLED");
                }
            }
        }
        else
        {
            if(h_team_score>c_team_score)
            {
                int lIndex=h_team.length-1;
                dualPrint("The batter scored "+h_team[lIndex]+" runs");
                dualPrint("----------------------------");
                dualPrint(String.format("| %-15s %7s |%n", "CPU", c_team_score));
                dualPrint(String.format("| %-15s %7s |%n", "HUMAN", h_team_score + "/" + h_wickets_lost));
                dualPrint("----------------------------");
                int wicketMargin = wickets - h_wickets_lost;
                dualPrint("HUMAN WON BY " + wicketMargin + (wicketMargin == 1 ? " WICKET" : " WICKETS"));
                h_wins+=1;
                if(h_wins>cpu_wins)
                {
                    System.out.println("Humans lead the series "+h_wins+"-"+cpu_wins+"\n");
                }
                else if(cpu_wins>h_wins)
                {
                    System.out.println("CPU lead the series "+cpu_wins+"-"+h_wins+"\n");
                }
                else
                {
                    System.out.println("THE SERIES HAS BEEN LEVELLED");
                }
            }        
            else if(c_team_score>h_team_score)
            {
                dualPrint("----------------------------");
                dualPrint(String.format("|   CPU          %3d     |%n", c_team_score));
                dualPrint(String.format("|   HUMAN            %3d     |%n", h_team_score));
                dualPrint("----------------------------");
                dualPrint("CPU WON BY " + (c_team_score - h_team_score) + " RUNS");
                cpu_wins+=1;
                if(h_wins>cpu_wins)
                {
                    System.out.println("Humans lead the series "+h_wins+"-"+cpu_wins+"\n");
                }
                else if(cpu_wins>h_wins)
                {
                    System.out.println("CPU lead the series "+cpu_wins+"-"+h_wins+"\n");
                }
                else
                {
                    System.out.println("THE SERIES HAS BEEN LEVELLED");
                }
            }
            else
            {
                dualPrint("----------------------------");
                dualPrint(String.format("|  %-10s %8d     |%n", "CPU", c_team_score));
                dualPrint(String.format("|  %-10s %8d     |%n", "HUMAN", h_team_score));
                dualPrint("----------------------------");
                dualPrint("MATCH TIED");
                match_ties+=1;
                if(h_wins>cpu_wins)
                {
                    System.out.println("Humans lead the series "+h_wins+"-"+cpu_wins+"\n");
                }
                else if(cpu_wins>h_wins)
                {
                    System.out.println("CPU lead the series "+cpu_wins+"-"+h_wins+"\n");
                }
                else
                {
                    System.out.println("THE SERIES HAS BEEN LEVELLED");
                }
            }
        
        }
    }

    static void summaryCPU(String c_choice)
    {
        if(c_choice.equalsIgnoreCase("BAT"))
        {
            
            if(h_team_score>c_team_score)
            {
                int lIndex=h_team.length-1;
                dualPrint("The batter scored "+h_team[lIndex]+" runs");
                dualPrint("----------------------------");
                dualPrint(String.format("| %-15s %7s |%n", "CPU", c_team_score));
                dualPrint(String.format("| %-15s %7s |%n", "HUMAN", h_team_score + "/" + h_wickets_lost));
                dualPrint("----------------------------");
                int wicketMargin = wickets - h_wickets_lost;
                dualPrint("HUMAN WON BY " + wicketMargin + (wicketMargin == 1 ? " WICKET" : " WICKETS"));
                h_wins+=1;
                if(h_wins>cpu_wins)
                {
                    System.out.println("Humans lead the series "+h_wins+"-"+cpu_wins+"\n");
                }
                else if(cpu_wins>h_wins)
                {
                    System.out.println("CPU lead the series "+cpu_wins+"-"+h_wins+"\n");
                }
                else
                {
                    System.out.println("THE SERIES HAS BEEN LEVELLED");
                }
                
            }        
            else if(c_team_score>h_team_score)
            {
                dualPrint("----------------------------");
                dualPrint(String.format("|   CPU          %3d     |%n", c_team_score));
                dualPrint(String.format("|   HUMAN            %3d     |%n", h_team_score));
                dualPrint("----------------------------");
                dualPrint("CPU WON BY " + (c_team_score - h_team_score) + " RUNS");
                cpu_wins+=1;
                if(h_wins>cpu_wins)
                {
                    System.out.println("Humans lead the series "+h_wins+"-"+cpu_wins+"\n");
                }
                else if(cpu_wins>h_wins)
                {
                    System.out.println("CPU lead the series "+cpu_wins+"-"+h_wins+"\n");
                }
                else
                {
                    System.out.println("THE SERIES HAS BEEN LEVELLED");
                }
            }
            else
            {
                dualPrint("----------------------------");
                dualPrint(String.format("|  %-10s %8d     |%n", "CPU", c_team_score));
                dualPrint(String.format("|  %-10s %8d     |%n", "HUMAN", h_team_score));
                dualPrint("----------------------------");
                dualPrint("MATCH TIED");
                match_ties+=1;
                if(h_wins>cpu_wins)
                {
                    System.out.println("Humans lead the series "+h_wins+"-"+cpu_wins+"\n");
                }
                else if(cpu_wins>h_wins)
                {
                    System.out.println("CPU lead the series "+cpu_wins+"-"+h_wins+"\n");
                }
                else
                {
                    System.out.println("THE SERIES HAS BEEN LEVELLED");
                }
            }
        }
        else
        {
            if(h_team_score>c_team_score)
            {
                dualPrint("----------------------------");
                dualPrint(String.format("|   HUMAN          %3d     |%n", h_team_score));
                dualPrint(String.format("|   CPU            %3d     |%n", c_team_score));
                dualPrint("----------------------------");
                dualPrint("HUMANS WON BY " + (h_team_score - c_team_score) + " RUNS");
                h_wins+=1;
                if(h_wins>cpu_wins)
                {
                    System.out.println("Humans lead the series "+h_wins+"-"+cpu_wins+"\n");
                }
                else if(cpu_wins>h_wins)
                {
                    System.out.println("CPU lead the series "+cpu_wins+"-"+h_wins+"\n");
                }
                else
                {
                    System.out.println("THE SERIES HAS BEEN LEVELLED");
                }
            }        
            else if(c_team_score>h_team_score)
            {
                int lIndex=c_team.length-1;
                System.out.println("The batter scored "+c_team[lIndex]+" runs");
                dualPrint("----------------------------");
                dualPrint(String.format("| %-15s %7s |%n", "HUMAN", h_team_score));
                dualPrint(String.format("| %-15s %7s |%n", "CPU", c_team_score + "/" + c_wickets_lost));
                dualPrint("----------------------------");
                int wicketMargin = wickets - c_wickets_lost;
                dualPrint("CPU WON BY " + wicketMargin + (wicketMargin == 1 ? " WICKET" : " WICKETS"));
                cpu_wins+=1;
                if(h_wins>cpu_wins)
                {
                    System.out.println("Humans lead the series "+h_wins+"-"+cpu_wins+"\n");
                }
                else if(cpu_wins>h_wins)
                {
                    System.out.println("CPU lead the series "+cpu_wins+"-"+h_wins+"\n");
                }
                else
                {
                    System.out.println("THE SERIES HAS BEEN LEVELLED");
                }
            }
            else
            {
                dualPrint("----------------------------");
                dualPrint(String.format("|  %-10s %8d     |%n", "HUMAN", h_team_score));
                dualPrint(String.format("|  %-10s %8d     |%n", "CPU", c_team_score));
                dualPrint("----------------------------");
                dualPrint("MATCH TIED");
                match_ties+=1;
                if(h_wins>cpu_wins)
                {
                    System.out.println("Humans lead the series "+h_wins+"-"+cpu_wins+"\n");
                }
                else if(cpu_wins>h_wins)
                {
                    System.out.println("CPU lead the series "+cpu_wins+"-"+h_wins+"\n");
                }
                else
                {
                    System.out.println("THE SERIES HAS BEEN LEVELLED");
                }
            }
        }
    }
}