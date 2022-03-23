package tech.vinc3nzo.prognet.jpa.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String facebook;
    private String github;
    private String instagram;
    private String mainLink;
    private String twitter;
    private String vk;
    private String website;
    private String youtube;

    public Contacts() {
        facebook = "";
        github = "";
        instagram = "";
        mainLink = "";
        twitter = "";
        vk = "";
        website = "";
        youtube = "";
    }

    public Contacts(String facebook, String github, String instagram, String mainLink,
                    String twitter, String vk, String website, String youtube)
    {
        this.facebook = facebook;
        this.github = github;
        this.instagram = instagram;
        this.mainLink = mainLink;
        this.twitter = twitter;
        this.vk = vk;
        this.website = website;
        this.youtube = youtube;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getMainLink() {
        return mainLink;
    }

    public void setMainLink(String mainLink) {
        this.mainLink = mainLink;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getVk() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "id=" + id +
                ", facebook='" + facebook + '\'' +
                ", github='" + github + '\'' +
                ", instagram='" + instagram + '\'' +
                ", mainLink='" + mainLink + '\'' +
                ", twitter='" + twitter + '\'' +
                ", vk='" + vk + '\'' +
                ", website='" + website + '\'' +
                ", youtube='" + youtube + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacts)) return false;
        Contacts contacts = (Contacts) o;
        return Objects.equals(id, contacts.id)
                && Objects.equals(facebook, contacts.facebook)
                && Objects.equals(github, contacts.github)
                && Objects.equals(instagram, contacts.instagram)
                && Objects.equals(mainLink, contacts.mainLink)
                && Objects.equals(twitter, contacts.twitter)
                && Objects.equals(vk, contacts.vk)
                && Objects.equals(website, contacts.website)
                && Objects.equals(youtube, contacts.youtube);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, facebook, github, instagram,
                mainLink, twitter, vk, website, youtube);
    }
}
