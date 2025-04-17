# Image management System POC

`Mian`
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

`Image class`

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
