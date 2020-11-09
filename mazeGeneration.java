import java.util.*;

public class mazeGeneration{
    int elemNumber;
    int row;
    int col;
    ArrayList<Integer> wallList;

    public mazeGeneration(int n, int m){
        row = n;
        col = m;
        elemNumber = n * m;
        wallList = new ArrayList<>();
        for (int i = 0; i < elemNumber * 2; i++){
            wallList.add(i);
        }
     }
    public void mazeUi(){
        int[] counter = new int[elemNumber];
        System.out.print("   ");
        for (int i = 1; i < col; i++){
            System.out.print(" _ ");

        }
        System.out.println("");
        for (int i = 0; i < wallList.size(); i++){
            if (wallList.get(i) > elemNumber - 1){
                counter[wallList.get(i) % elemNumber] += 2;
            }else{
                counter[wallList.get(i)] += 1;
            }
        }
        System.out.print("   ");
        for (int i = 1; i < counter.length - 1; i++){
            if (counter[i] == 1){
                System.out.print("|  ");
            }
            else if (counter[i] == 2){
                System.out.print(" _ ");
            }
            else if (counter[i] == 3){
                System.out.print("|_ ");
            }
            else if (counter[i] == 0){
                System.out.print("   ");
            }
            if (i % col == col - 1){
                System.out.println("|");
            }
        }
        System.out.println("");
    }
    public void generateMaze(){
        DisjSets sets = new DisjSets(elemNumber);
        int count = 0;
        while (sets.find(elemNumber - 1) != 0){
            Random rand = new Random(); 
            int random = rand.nextInt((elemNumber * 2) - count) ; 
            int random2 = wallList.get(random);
            if (random2 % col != 0 && random2 < elemNumber){
                if (sets.find(random2 - 1) != sets.find(random2)){
                    if ( sets.find(random2) == 0){
                        sets.union(sets.find(random2), sets.find(random2 - 1));
                    }else{
                        sets.union(sets.find(random2 - 1), sets.find(random2));
                    }
                    wallList.remove(random);
                    count++;        
                }
            }

            if (sets.find(elemNumber - 1) == 0){break;}
            if (random2 < elemNumber * 2 - col && random2 > elemNumber - 1){
                if (sets.find(random2 - elemNumber) != sets.find(random2 + col - elemNumber)){
                    if (sets.find(random2 + col - elemNumber) == 0){
                        sets.union(sets.find(random2 + col - elemNumber), sets.find(random2 - elemNumber)); 
                    }else{
                        sets.union(sets.find(random2 - elemNumber), sets.find(random2 + col - elemNumber));
                    }
                    wallList.remove(random);
                    count++;
                }
            }
        }
        mazeUi();
    }

    static class DisjSets
{
        /** Construct the disjoint sets object.
         * @param numElements the initial number of disjoint sets.
         */
        public DisjSets(int numElements)
        {
            s = new int [ numElements ];
            for( int i = 0; i < s.length; i++ )
                s[ i ] = -1;
        }

        /**   Union two disjoint sets.  
         *     Assume root1 and root2 are distinct and represent set names.
         * @param root1 the root of set 1.
         * @param root2 the root of set 2.          */
        public void union( int root1, int root2 )
        {
            s[ root2 ] = root1;
        }
        
        /** Perform a find.   Error checks omitted again for simplicity.
         * @param x the element being searched for.
         * @return the set containing x.         */
        public int find( int x )
        {
            if( s[ x ] < 0 )
                return x;
            else
                return find( s[ x ] );
        }

        private int [ ] s;
}



    public static void main(String[] args){ 
 
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        Scanner myObj2 = new Scanner(System.in);  // Create a Scanner object
 
        System.out.print("Input a value for the row number(Integer < 20): ");
        String userInput = myObj.nextLine();  // Read user input
        System.out.print("Input a value for the column number(Integer < 20): ");
        String userInput2 = myObj2.nextLine();  // Read user input
        mazeGeneration maze = new mazeGeneration(Integer.parseInt(userInput), Integer.parseInt(userInput2));
        maze.generateMaze(); // output will be in the command line

        

    } 
}