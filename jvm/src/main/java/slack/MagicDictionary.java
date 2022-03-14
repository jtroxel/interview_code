package slack;
/*
PROBLEM: Transformation dictionary

1) Given a dictionary of words, determine whether it is possible to transform a given word into another with a fixed number of characters

2) Modify the approach so that transitions between varying quantities were possible

3) Modify the code to work as an API with lots of words added to the dictionary, but few tests

boolean isTransformable(String start, String end, List<String> dict)

EX: "dog" -> "hat", dict = ["dog", "cat", "hot", "hog", "eat", "dug", "dig"]

QUESTIONS?:
- Each intermediate transorm has to be in the dict.

SOLUTION IDEAS / INTUITION
 - This reminds me of using a Trie to store a language, or list of valid tokens, so
  head->d->o->g
        \->u->g
     \->c->a->t

  - traverse:  match frist, see if there are 2nd alternatives.
    - for current letter, need to look back as well?

 - Or How about a Matrix?
  | d, o, g |
  | c, a, t |
  | h, o, t |
  | h, o, g |
  | d, i, g |
  | d, u, g |

- Brute force
 - So we want to check a word against a list, looking for only N character diffs.  Can also check the end against the list.  Meet in the middle.  Each time looking for overlapping results, or sets.
   Idea is a double loop, searching forward and backwards.  We have a positive if the resulting sets overlap.
   Loop ??
   - Compare [dog] <-> [hat]
   - Forward: search [dog] -> dig, hog (+dog)
   - Backward: search [hat] -> hot (+hat)
   (second pass)
   - Compare [dog, dig, hog] <-> [hat, hot] -> this case means 2 transform away
   - search [dig, hog] -> [dog, dug] + [hot,dog] + [dig, hog] -> oh we add to a matchLeft list
   - search [hat, hot] -> [cat, hot] +  [hot] + [hat, hot] -> add to matchRight
   (third)
   - Compare [dog, dug, hot, dig, hog] <-> [cat, hot, hat]

Dictionary len = N, max word length = K.  X = chars to change.  K/X outer loop, N*N string compares innder loop.
   O(N**2)


*/


import java.util.*;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

class MagicDictionary {
  private int max_word_length = 3;
  private int matchChars = 1;
  public static void main(String[] args) {
    List<String> dict = Lists.newArrayList("dog", "cat", "hot", "hog", "eat", "dug", "dig");
    MagicDictionary transformer = new MagicDictionary();
    assert transformer.isTransformable("dog", "hat", dict);
  }
  public boolean isTransformable(String start, String end, List<String> dict) {
    Set<String> searchLeft = Sets.newHashSet(start);
    Set<String> searchRight = Sets.newHashSet(end);
    int pass = 1;
    do {
      System.out.println("Left: " + searchLeft + " Right: " + searchRight + " Pass: " + pass);
      if (!Collections.disjoint(searchLeft, searchRight)) {
        System.out.println("Found!!! ");
        return true;
      }
      searchLeft.addAll(searchStringsOneDiff(searchLeft, dict));
      searchRight.addAll(searchStringsOneDiff(searchRight, dict));
      pass += 1;
    } while (pass <= max_word_length);
    return false;
  }
  private Set<String> searchStringsOneDiff(Set<String> searchFor, List<String>dict) {
    Set<String> retSet = new HashSet<>();
    for (String searchWord : searchFor) {
      System.out.println("Comparing search: " + searchWord);
     for (String dictWord : dict) {
        System.out.println(". to " + dictWord);
        int matchingChars = 0;
        if (searchWord.equals(dictWord)) continue;
        for (int i = 0; i < dictWord.length(); i++) {
          if (searchWord.charAt(i) == dictWord.charAt(i)) {
            System.out.println(".  - char match " + dictWord.charAt(i));
            matchingChars += 1;
          }
        }
        System.out.println(". total matching " + matchingChars);
        if (matchingChars == (dictWord.length() - this.matchChars)) {
          System.out.println("Found word " + dictWord + " from " + searchWord);
          retSet.add(dictWord);
        }
      }
      System.out.println("Return Set: " + retSet.toString());
      return retSet;
    }
    return null;
  }
}
