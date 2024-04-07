package oy.tol.tira.books;

public class WordCount implements Comparable<WordCount> {
    String word;
    int count;
    WordCount left;
    WordCount right;
    int hash;
    LinkedListImplementation<WordCount> list = null;

    public WordCount(){
        this.word="";
        this.count=0;
        WordCount left=null;
        WordCount right=null;
        hash=hashCode();
    }

    public WordCount(final WordCount wordcount) {
        this.word = new String(wordcount.word);
        this.count = wordcount.count;
        WordCount left=null;
        WordCount right=null;
        hash=hashCode();
    }
    
    public WordCount(String word, int count) {
        this.word = word;
        this.count = count;
        WordCount left=null;
        WordCount right=null;
        hash=hashCode();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word){
        this.word=word;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count){
        this.count=count;
    }




    public static void binaryTreeToListArray(WordCount node) {
       
        if (node == null)
            return;
        if(node.list!=null){
            for (int i = 0; i < node.list.size(); i++) {
                WordCount current = node.list.get(i);
                BinarySearchTreeBookImplementation.words[BinarySearchTreeBookImplementation.indexOfWordCount++]=current;
            }
        }else{
            BinarySearchTreeBookImplementation.words[BinarySearchTreeBookImplementation.indexOfWordCount++]=node;
        }

        binaryTreeToListArray(node.left);
        binaryTreeToListArray(node.right);
    }


 void insert(WordCount wordCount, int toInsertHash) throws RuntimeException {
      if (toInsertHash < this.hash) {
         if (null == left) {
            left = wordCount;
            BinarySearchTreeBookImplementation.uniqueWordCount++;
         } else {
            left.insert(wordCount, toInsertHash);
         }
      } else if (toInsertHash > this.hash) {
         if (null == right) {
            right = wordCount;
            BinarySearchTreeBookImplementation.uniqueWordCount++;
         } else {
            right.insert(wordCount, toInsertHash);
         }
      } else { // equal hashes
         if (this.equals(wordCount)) {
            // Key-value pair already in tree, update the value for the key.
            this.count++;
         } else {
            // OPTIONAL different key, same hash, put in the linked list.
            if (null == list) {
               list = new LinkedListImplementation<>();
               list.add(wordCount);
               BinarySearchTreeBookImplementation.uniqueWordCount++;
            } else {
               WordCount newItem = wordCount;
               int index = list.indexOf(newItem);
               if (index < 0) {
                  list.add(newItem);
                  BinarySearchTreeBookImplementation.uniqueWordCount++;
               } else {
                  list.get(index).count++;
               }
            }
            if (list.size() > BinarySearchTreeBookImplementation.maxProbingSteps) {
                BinarySearchTreeBookImplementation.maxProbingSteps = list.size();
            }
         }
      }
   }



    @Override
    public String toString() {
        return "word="+word+" count="+count;
    }

    @Override
    public int hashCode() {
        int hash=0;
        String hashString=word;
        for (int i = 0; i < hashString.length(); i++) {
            hash=37*hash+hashString.charAt(i);
        }
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof WordCount) {
            return this.word.equals(((WordCount)other).word);
        }
        return false;
    }

    @Override
    public int compareTo(WordCount other) {
        return (other.count)-count;
    }
}