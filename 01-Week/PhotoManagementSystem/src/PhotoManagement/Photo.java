package PhotoManagement;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Photo {
    private String title;
    private String Location;
    private LocalDate data;
    private Set<String> tags;

    public Photo(String title, String location, LocalDate data, Set<String> tags) {
        this.title = title;
        this.Location = location;
        this.data = data;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(title, photo.title) && Objects.equals(Location, photo.Location) && Objects.equals(data, photo.data) && Objects.equals(tags, photo.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, Location, data, tags);
    }
}
