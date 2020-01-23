// https://github.com/wordset/wordset-dictionary
import java.util.*; 
import java.io.File;
import java.io.FileNotFoundException;

class Main {
  private static String helper(String key,String line){
    int sc1 = line.indexOf(";");
    int sc2 = line.lastIndexOf(";");
    String word = line.substring(0,sc1); 
    if(key.equals(word.toLowerCase())){
      return "\t"+line.substring(sc1+1,sc2);
    }
    else
      return "";
  }
  private static String search(String key,Integer col,Integer row){
    String search = key.toLowerCase();
    col++;
    char dir = search.charAt(0);
    Integer length = key.length();    
    String result = row.toString()+ "\t" + col.toString() + "\t" + key + "\t";
    String path = "./dataset/" + dir + "/" + length.toString();    
    String res;
    try{
      Scanner scanner = new Scanner(new File(path));
      while (scanner.hasNextLine()) {
        res = helper(search,scanner.nextLine());
        if(!res.equals("")){
          scanner.close();   
          return result + res;   
        } 
      }
      scanner.close();
      return result + "~";
    } catch (FileNotFoundException e) {
			e.printStackTrace();
		}
     return result + "~";
  }
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String inpFile="./input.txt";
    try {
    Scanner scanner = new Scanner(new File(inpFile));
    int Row  = 0 ;
	System.out.println("ROW\tCOL\tLexem\t\tToken");	
      while (scanner.hasNextLine()) {
        Row++;
        String str = scanner.nextLine();	
	int Col = 0 ;
	String word = "";
	for (int i = 0; i < str.length(); i++){
	    char c = str.charAt(i);     
	    if("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(c)==-1){
	 	if(word.length()>0){
			String result = search(word,Col-word.length(),Row);
			if(result.charAt(result.length()-1)!='~'){
				System.out.println(result);
			}
		}
		word = "";
	    }
	    else{
		word += c;
		Col++;
	    }
	}	
      }
      scanner.close();
    }
    catch (FileNotFoundException e) {e.printStackTrace();}

  }
}
