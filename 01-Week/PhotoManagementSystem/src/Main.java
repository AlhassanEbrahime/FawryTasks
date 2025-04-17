import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args)  {

        Map<List<String>, String> Image = new HashMap<>();
        List<String>tags = new ArrayList<>();
        tags.add("Cairo");
        tags.add("Tahrir square");
        Image.put(tags, "profile image");

        if (tags.contains("Tahrir square")){
            System.out.println(Image.get(tags));
        }else{
            System.out.println("Image not found");
        }

    }
}