package PhotoManagement;

import java.time.LocalDate;
import java.util.*;

public class PhotoManager {

    private Set<Photo> photos = new HashSet<>();
    private Map<String , Set<Photo>> tagIndex = new HashMap<>();
    private Map<String, Set<Photo>> locationIndex = new HashMap<>();
    private Map<LocalDate,Set<Photo>> dateIndex = new HashMap<>();


    public Photo addPhoto(String title , String location, LocalDate date , Set<String> tags){

        Photo photo = new Photo(title, location, date, tags);

        photos.add(photo);


        for(String tag : tags){
            tagIndex.computeIfAbsent(tag.toLowerCase(), k-> new HashSet<>()).add(photo);
        }


        locationIndex.computeIfAbsent(location.toLowerCase(), k-> new HashSet<>()).add(photo);


        dateIndex.computeIfAbsent(date, k-> new HashSet<>()).add(photo);

        return photo;

    }


    public boolean addTagsToPhoto(Photo photo, Set<String>newTags){

        if(photos.contains(photo)){

            for(String tag : newTags){

                photo.getTags().add(tag.toLowerCase());

                tagIndex.computeIfAbsent(tag.toLowerCase(), k-> new HashSet<>()).add(photo);
            }
            return true;
        }
        return false;
    }

    public Set<Photo> searchByTag(String tag){
        if (tagIndex.containsKey(tag.toLowerCase())){
            return tagIndex.get(tag);
        }
        return Collections.emptySet();
    }


    public Set<Photo> searchByLocation(String location){
        if(locationIndex.containsKey(location)){
            return locationIndex.get(location);
        }
        return Collections.emptySet();
    }


    public Set<Photo> SearchByDate(LocalDate date){
        if(dateIndex.containsKey(date)){
            return dateIndex.get(date);
        }
        return Collections.emptySet();
    }

    

}
