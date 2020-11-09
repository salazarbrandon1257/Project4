import java.util.*;

public class mazeGeneration{
    int elemNumber;
    ArrayList<Integer> wallList;

    public mazeGeneration(int n){
        elemNumber = n;
        wallList = new ArrayList<>();
        for (int i = 0; i < n * 2; i++){
            wallList.add(i);
        }
        System.out.println(Arrays.toString(wallList.toArray()));
    }
    public void generateMaze(){
        DisjSets sets = new DisjSets(elemNumber);
        int n = 0;
        while (sets.find(elemNumber - 1) != 0){
            Random rand = new Random(); 
            int random = rand.nextInt((elemNumber * 2) - n) ; 
            if (random % 4 != 0 && random < elemNumber){
                if (sets.find(random - 1) != sets.find(random)){
                    sets.union(random - 1, random);
                    wallList.remove(random);
                    n++;
                }
                System.out.println(Arrays.toString(wallList.toArray()));
                break;
            }
        }
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
        int elementNumber;
        System.out.print("Input a value for the row number(Integer < 20): ");
        String userInput = myObj.nextLine();  // Read user input
        System.out.print("Input a value for the column number(Integer < 20): ");
        String userInput2 = myObj2.nextLine();  // Read user input
        elementNumber = Integer.parseInt(userInput) * Integer.parseInt(userInput2);

        mazeGeneration maze = new mazeGeneration(elementNumber);
        maze.generateMaze();

        //System.out.println(sets.find(1));
        //sets.union(1, 8);
        //System.out.println(sets.find(8));
        

    } 
}