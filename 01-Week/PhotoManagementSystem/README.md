# Image management System POC

### `Main`
``` java 

public class Main {
    public static void main(String[] args)  {

        Scanner sc = new Scanner(System.in);
        List<String> tags = new ArrayList<>();
        Map<List<String>, String> userImage = new HashMap<>();
        System.out.println("Add your image: ");
        String photo = sc.nextLine();
        System.out.println("add tags (type Add to stop)");
        while(true){
            String tag = sc.nextLine();
            if(tag.equalsIgnoreCase("add")){
                break;
            }
            tags.add(tag);
        }
        userImage.put(tags, photo);
        Image image = new Image("profile image ", tags, userImage);


        System.out.println("Enter tag");
        String query = sc.nextLine();
        System.out.println(image.search(query));


    }
}

```

### `Image class`

``` java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Image {

    private List<String> metaData = new ArrayList<>();
    private Map<List<String>, String> image = new HashMap<>();

    public Image(List<String> metaData, Map<List<String>, String> image) {
        this.metaData = metaData;
        this.image = image;
    }



    String search(String query){
        if(metaData.contains(query)){
            return image.get(metaData);
        }else{
            throw new RuntimeException("image not found");
        }
    }




}

```

## enhancements 

- Using Inverted index
- Implement other features ( search by location , date, 



## Inverted Index 

### `Inverted Index class`

``` java 
import java.util.*;

public class InvertedIndex {

    private Map<String, List<Integer>> index;
    private List<String> documents;

    public InvertedIndex(){
        index = new HashMap<>();
        documents = new ArrayList<>();
    }

    public  int addDocument(String document){
        int docId = documents.size();
        documents.add(document);

        String[] tokens = tokenize(document);

        for(String token : tokens){
            token = normalize(token);
            if (token.isEmpty()){
                continue;
            }

            index.computeIfAbsent(token, K-> new ArrayList<>()).add(docId);
        }
        return docId;
    }

    public List<Integer> search(String query){
        String[] queryTokens = tokenize(query);

        if(queryTokens.length == 0){
            return Collections.emptyList();
        }

        for (int i = 0 ; i < queryTokens.length; i++) {
            queryTokens[i] = normalize(queryTokens[i]);
        }

        List<Integer> result = new ArrayList<>();
        String firstToken = queryTokens[0];

        if (index.containsKey(firstToken)) {
            result.addAll(index.get(firstToken));
        }else{
            return Collections.emptyList();
        }

        for(int i = 1; i < queryTokens.length; i++){
            String token = queryTokens[i];

            if (!index.containsKey(token)) {
                return Collections.emptyList();
            }

            List<Integer> postingList = index.get(token);
            result.retainAll(postingList);

            if(result.isEmpty()){
                return Collections.emptyList();
            }
        }
        return result;
    }

    public String getDocument(int docId){
        if (docId >=0 && docId < documents.size()){
            return documents.get(docId);
        }
        return null;
    }

    private String[] tokenize(String text){
        return text.split("\\W+");
    }

    private String normalize(String token){
        return token.toLowerCase();
    }

    public void printIndex(){
        for(Map.Entry<String, List<Integer>> entry : index.entrySet()){
            System.out.println(entry.getKey()+ ": " + entry.getValue());
        }
    }


}

```

