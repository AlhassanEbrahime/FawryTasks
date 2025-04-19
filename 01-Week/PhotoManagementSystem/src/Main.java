import java.net.ServerSocket;
import java.util.*;

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
        Image image = new Image (tags, userImage);


        System.out.println("Enter tag");
        String query = sc.nextLine();
        System.out.println(image.search(query));


    }
}