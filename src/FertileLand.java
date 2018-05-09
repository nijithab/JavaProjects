import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;

/**
 * Program to find fertile land area in the given farm land with rectangular barren land coordinates
 */
public class FertileLand {

    LinkedList<Integer[]> barrenLands;
    Boolean allNodes[][];
    LinkedList<Integer[]> nodeList;
    List<Integer> fertileLandCountList;
    final static int XLIM = 400;
    final static int YLIM = 600;


    FertileLand(){
        barrenLands = new LinkedList<Integer[]>();
        nodeList = new LinkedList<Integer []>();
        fertileLandCountList = new ArrayList<>();
        allNodes = new Boolean[XLIM][YLIM];
    }

    public static void main(String args[]) {
        FertileLand t = new FertileLand();
        String input;
        try {
            input = t.getInputFromSTDIN();
            System.out.println(t.getFertileLandOutput(input));
        } catch(Exception e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        }
    }

    /**
     * Returns all the fertile land area, sorted from smallest area to greatest, separated by a space
     * @param input
     * @return
     */
    public String getFertileLandOutput(String input){
        markBarrenLands(input);
        getFertileLands();
        return getSortedFertileLandCounts();
    }

    /**
     * Formats and returns the barren land coordinates that are read from system in
     * @param input
     * @return
     */
    private String[] getBarrenLandCoOrdinates(String input){
        input = input.replaceAll("[“”{}\"]","");
        return input.split(",");
    }


    /**
     * Mark all barren lands
     * @param input
     */
    private void markBarrenLands(String input){
        String[] barrenLandParts = getBarrenLandCoOrdinates(input);
        for(String s:barrenLandParts){
            if(!s.isEmpty()){
                String[] barrenLandCoOrdinates = s.trim().split(" ");
                Integer[] barrenLand = {Integer.parseInt(barrenLandCoOrdinates[0]), Integer.parseInt(barrenLandCoOrdinates[1]),
                        Integer.parseInt(barrenLandCoOrdinates[2]), Integer.parseInt(barrenLandCoOrdinates[3])};
                barrenLands.add(barrenLand);
            }
        }
        markBarrenLandNodes();
    }


    /**
     * Sets all barren land nodes to true whereas all other nodes by default are set to null
     */
    private void markBarrenLandNodes(){
        barrenLands.forEach(new Consumer<Integer[]>() {
            public void accept(Integer[] barrenLand) {
                for(int i = barrenLand[0]; i <= barrenLand[2]; i++)
                    for(int j = barrenLand[1]; j <= barrenLand[3]; j++)
                        allNodes[i][j] = Boolean.TRUE;
            }
        });
    }


    /**
     * Add fertile land nodes to the list
     * @param i
     * @param j
     */
    private void addFertileNodeToList(int i, int j){
        if(allNodes[i][j] == null){
            nodeList.add(new Integer[] {i, j});
        }
    }


    /**
     * Sort the fertile land counts and return the list
     * @return
     */
    private String getSortedFertileLandCounts(){
        Collections.sort(fertileLandCountList);
        return fertileLandCountList.toString().replaceAll("[\\[\\],]","");
    }


    /**
     * Read the input from STDIN
     * @return
     * @throws IOException
     */
    public String getInputFromSTDIN() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Input :");
        return br.readLine();
    }


    /**
     * Traverse through all the nodes and updates fertile land counts for each fertile land node
     */
    private void getFertileLands(){
        int groupCounter = -1;
        int i = 0;
        int j = 0;
        while(i<XLIM && j<YLIM){
            if(nodeList.isEmpty()) {
                if(allNodes[i][j] == null) {
                    groupCounter++;
                    fertileLandCountList.add(groupCounter, 0);
                    nodeList.add(new Integer[]{i, j});
                }
                if(i == (XLIM-1)){
                    i = 0;
                    j++;
                } else {
                    i++;
                }
            }
            traverseNodeList(groupCounter);
        }
    }

    /**
     *Traverses through each fertile land node using BFS
     * @param groupCounter
     */
    private void traverseNodeList(int groupCounter) {
        if(nodeList.size() >= 1) {
            Integer node[] = nodeList.pop();
            int x = node[0];
            int y = node[1];
            if (allNodes[x][y] == null) {
                allNodes[x][y] = Boolean.TRUE;
                fertileLandCountList.set(groupCounter, fertileLandCountList.get(groupCounter) + 1);
                traverseNodeAndUpdateNodeList(x, y);
            }
        }
    }

    private void traverseNodeAndUpdateNodeList(int x, int y) {
        //traverse through all the nodes
        if(x > 0) {
            addFertileNodeToList(x - 1, y);
        }
        if(y > 0) {
            addFertileNodeToList(x, y - 1);
        }
        if(x < (XLIM - 1)) {
            addFertileNodeToList(x + 1, y);
        }
        if(y < (YLIM - 1)) {
            addFertileNodeToList(x, y + 1);
        }
    }

}