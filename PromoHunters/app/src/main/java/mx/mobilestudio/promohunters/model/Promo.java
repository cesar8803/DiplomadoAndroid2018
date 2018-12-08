package mx.mobilestudio.promohunters.model;

import io.realm.Realm;
import io.realm.RealmObject;

public class Promo extends RealmObject {
    private String title;
    private Float price;
    private String link;
    private String description;
    private String imageLink="https://via.placeholder.com/300";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
