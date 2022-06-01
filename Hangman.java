import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int randnum = (int)(Math.random()*words.length);

        String test = words[randnum];
        char[] result = test.toCharArray();
        //step3: making the placeholder
        char[] placeholder = new char[result.length];
        for(int i=0;i<result.length;i++){
            placeholder[i] = '_';
        }
        char[] errchar = new char[0];
        int[] usedindx = new int[0];
        int error = 0;
        //step 5: make a copy of the key
        char[] resultcopy = Arrays.copyOf(result, result.length);
        //step 4: creating a while loop to verify that errors don't exceed 6
        int[] flag = {-1};
        while(error<6 && Check('_', placeholder,flag)!=-1){
            System.out.println(gallows[error]);
            System.out.print("Word: ");
            Display(placeholder);
            System.out.print("Misses: ");
            Display(errchar);
            System.out.print("Guess: ");
            char letter = scan.next().charAt(0);
            int index = Check(letter, resultcopy,usedindx);
            if(index==-1){
                errchar = append(letter, errchar);
                error++;
            }else{
                placeholder[index] = letter;
                usedindx = appendindx(index, usedindx);
            }
        }
        if(error == 6){
            System.out.println(gallows[6]);
            System.out.print("The word is ");
            Display(result);
            System.out.println("RIP!");
        }else{
            System.out.print("Word: ");
            Display(placeholder);
            System.out.println("GOOD WORK!");
        }
        scan.close();




    }
    //step 1: make a fun that displays array
    public static void Display(char[] Arr){
        for(int i=0;i<Arr.length;i++){
            System.out.print(Arr[i]+" ");
        }
        System.out.print("\n");
    }
    //step 6: make a fun that veryfy if letter is there in the word and if it is there it replaces word with _
    public static int Check(char letter, char[] Arr,int[] usedindx){
        for(int i=0;i<Arr.length;i++){
            if(Arr[i] == letter && Checkindx(i, usedindx)==-1){
                return i;
            }
        }
        return -1;
    }

    public static int Checkindx(int index, int[] usedindx){
        for(int i=0;i<usedindx.length;i++){
            if(usedindx[i] == index){
                return i;
            }
        }
        return -1;
    }
    //step 7: make an append funtion
    public static char[] append(char letter, char[] word){
        char[] newArray = Arrays.copyOf(word, word.length+1);
        newArray[word.length] = letter;
        return newArray;
    }

    public static int[] appendindx(int index, int[] Arr){
        int[] newArray = Arrays.copyOf(Arr, Arr.length+1);
        newArray[Arr.length] = index;
        return newArray;
    }

}




