package tech.vinc3nzo.prognet.rspentities;

public class UserResponseEntity {
    private String name;
    private Long id;
    private PhotoSet photos;
    private String status;
    private Boolean followed;

    public UserResponseEntity(String name, Long id, PhotoSet photos,
                              String status, Boolean followed)
    {
        this.name = name;
        this.id = id;
        this.photos = photos;
        this.status = status;
        this.followed = followed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhotoSet getPhotos() {
        return photos;
    }

    public void setPhotos(PhotoSet photos) {
        this.photos = photos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getFollowed() {
        return followed;
    }

    public void setFollowed(Boolean followed) {
        this.followed = followed;
    }

    @Override
    public String toString() {
        return "UserResponseEntity{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", photos=" + photos +
                ", status='" + status + '\'' +
                ", followed=" + followed +
                '}';
    }
}
