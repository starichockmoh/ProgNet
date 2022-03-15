package tech.vinc3nzo.prognet.entity;

import javax.persistence.*;

@Entity
public class Contacts {
    @Id
    private Long id;

    private String facebook;
    private String github;
    private String instagram;
    private String mainLink;
    private String twitter;
    private String vk;
    private String website;
    private String youtube;

    protected Contacts() { }

    // TODO: discover what the MainLink really is.
    /**
     * Constructs a new Contacts instance.
     * @param facebook link to a Facebook profile
     * @param github link to a GitHub profile
     * @param instagram link to an Instagram profile
     * @param mainLink
     * @param twitter link to a Twitter profile
     * @param vk link to a Vkontakte profile
     * @param website link to a person's website
     * @param youtube link to a person's YouTube channel
     */
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
}
