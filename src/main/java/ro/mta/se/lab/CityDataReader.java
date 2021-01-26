package ro.mta.se.lab;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class handling the input file of the program
 */
public class CityDataReader {
    public JSONObject read (String filename) throws FileNotFoundException, FileNotFoundException {

        var file = new File(filename);
        var scanner = new Scanner(file);
        var res = new JSONObject();

        var array = new JSONArray();
        if(!scanner.hasNextLine())
            return null;

        var keyNames = new ArrayList<String>();

        var data    = scanner.nextLine();
        var info   = data.split("\\s+");

        for(int i=0; i<info.length; i++)
            keyNames.add(info[i]);

        while(scanner.hasNextLine()){
            var localData = new JSONObject();

            data = scanner.nextLine();
            info = data.split("\\s+");

            for(int i=0; i<info.length; i++){
                localData.put(keyNames.get(i), info[i]);
            }

            array.put(localData);
        }

        res.put("data", array);
        scanner.close();

        return res;
    }
}
