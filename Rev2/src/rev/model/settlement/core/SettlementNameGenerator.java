package rev.model.settlement.core;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import static java.util.Arrays.asList;

/**
 * Generates random settlement names.
 * 
 *
 * @author mattgorelik
 * 
 */
public class SettlementNameGenerator {

    /**
     * The set of possible names.
     * 
     */
	private static final List<String> prefixes = new ArrayList<String>(asList("Beaver","Elm","Oak","Brook","Bloom","Fair","Rock", "Sun","Green", "River","Spring","Wood","Pine","Lake","Fern","Glen","Summer","Silver","Iron","Pleasant"));
	private static final List<String> suffixes = new ArrayList<String>(asList(" Mill", " Lake", " Ridge"," Springs"," Valley"," Creek","ton","view","field","ville","dale","wood","port","castle","haven","brook"," Hill", " Hollow"," Junction"," Point"));
    private static List<String> chosenPres = new ArrayList<String>(prefixes);
    private static List<String> chosenSuff = new ArrayList<String>(suffixes);
    /**
     * Returns the next settlement name.
     * 
     * @return the settlement name.
     */
    public static String getNext() {
    	if (chosenPres.size() == 0){
    		refill();
    	}
    	Collections.shuffle(chosenPres);
    	Collections.shuffle(chosenSuff);
    	String random = chosenPres.remove(0).concat(chosenSuff.remove(0));
        return random;
    }
    
    private static void refill(){
    	for(String s : prefixes)
    		chosenPres.add(s);
    	for(String str : suffixes)
    		chosenSuff.add(str);
    }
    /*
    public static void main(String[] args) {
    	for (int i = 1; i <= 100; i++)
    		System.out.println("The "+i+"th name is: " + getNext());   
    	}*/
}