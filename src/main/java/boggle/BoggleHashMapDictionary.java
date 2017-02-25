package boggle;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
* Load a dictionary from a file.
*/
public class BoggleHashMapDictionary implements BoggleDictionary {

    public static final int MIN_WORD_SIZE = 3;

    // Map of all possible word roots in the dictionary
   // Boolean represents whether this is a word or not
   private Map<String, Boolean> words = new HashMap<String, Boolean>();
   private boolean indexPrefixes = false;


   public BoggleHashMapDictionary(BufferedReader reader, boolean indexPrefixes) {

       this.indexPrefixes = indexPrefixes;

       Pattern pattern = Pattern.compile("[a-z]{2,16}"); // assumes the Boggle board is never larger than 4x4

       try {
           //InputStream is = getClass().getResourceAsStream(dictionaryPath);
           //BufferedReader reader = new BufferedReader(new InputStreamReader(is));

           String word;
           while ((word = reader.readLine()) != null) {

               word = word.toLowerCase();
               if (pattern.matcher(word).matches()) {
                   int length = word.length();
                   if (length >= MIN_WORD_SIZE) {
                       words.put(word, Boolean.TRUE);
                   }

                   if (indexPrefixes) {
                       // for all valid words, index all prefixes starting with 2 characters
                       for (int i = 1; i < length; i++) {
                           String prefix = word.substring(0, i);
                           if (!words.containsKey(prefix)) {
                               words.put(prefix, Boolean.FALSE); // mark as not a word
                           }
                       }
                   }
               }

           }

           System.out.println("Loaded " + words.size() + " words.");

       } catch (IOException ioException) {
           ioException.printStackTrace();
       }

   }

   // isWord means the word is in the dictionary, regardless of whether it is a root word
   public boolean isWord(String word) {
       Boolean value = words.get(word.toLowerCase());
       return (word.length() >= MIN_WORD_SIZE && value != null && value);
   }

   // isWordRoot means the word is the root of another word
   public boolean isWordRoot(String word) {
       if (word.length() < MIN_WORD_SIZE) {
           return true;
       }
       if (indexPrefixes) {
           Boolean value = words.get(word.toLowerCase());
           // If the word is marked with false, it is a root word
           return (value != null && !value);
       } else {
           return true;
       }
   }

}
