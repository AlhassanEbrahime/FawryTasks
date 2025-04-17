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
