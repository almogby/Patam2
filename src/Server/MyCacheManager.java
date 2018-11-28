package Server;
import Generic.Solution;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;


public class MyCacheManager implements CacheManager{

	private final String nameOfFile = "Solution.txt";
    private HashMap<String, Solution> cache = new HashMap<>();
	    
    public MyCacheManager() {
        try {
        	
		       File file = new File(this.nameOfFile);
		       if (file.isFile() && file.canRead()) {
		           FileInputStream fis = new FileInputStream(this.nameOfFile);
		           ObjectInputStream ois = new ObjectInputStream(fis);
		           this.cache = (HashMap<String, Solution>) ois.readObject();
		           fis.close();
		           ois.close();
             }
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
		
    @Override
    public void save(String level, Solution solution) {
        if (!this.cache.containsKey(level)) {
            this.cache.put(level, solution);
            writeCacheIntoFile();
        }
    } 

    @Override
    public Solution load(String level) {
        if (this.cache.containsKey(level)) 
        {
            return this.cache.get(level);
        }
        return null;
    }
	

    private void writeCacheIntoFile() {
        try {
            FileOutputStream fos = new FileOutputStream(this.nameOfFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write the object
            oos.writeObject(this.cache);
            // closing 
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

