package tech.vinc3nzo.prognet.entity;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 * The class representing a basic entity (or a model)
 * instances of which are to be stored in a database.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade=ALL, mappedBy="user")
    private List<User> following;

    @OneToOne @MapsId
    private Contacts contacts;

    private String firstName;
    private String lastName;
    private String patronymic;
    private String aboutMe;
    private boolean lookingForAJob;
    private String lookingForAJobDescription;
    private String status;
    private String email;
    private String password;

    protected User() { }

    /**
     * Constructs a new User instance.
     * @param firstName first name of a user
     * @param lastName last name of a user
     * @param patronymic patronymic of a user
     * @param aboutMe short user's description
     * @param email user's email address
     * @param password user's password
     */
    public User(String firstName, String lastName, String patronymic,
                String aboutMe, @NonNull String email, @NonNull String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.aboutMe = aboutMe;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructs a new User instance.
     * @param fullName a user's (whitespace-separated) full name
     * @param aboutMe short user's description
     * @param email user's email address
     * @param password user's password
     */
    public User(String fullName, String aboutMe, @NonNull String email, @NonNull String password) {
        String[] name = fullName.split("\\s?", 2);
        this.firstName = name.length >= 1 ? name[0] : "";
        this.lastName = name.length >= 2 ? name[1] : "";
        this.patronymic = name.length >= 3 ? name[2] : "";
        this.aboutMe = aboutMe;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public boolean getLookingForAJob() {
        return lookingForAJob;
    }

    public void setLookingForAJob(boolean lookingForAJob) {
        this.lookingForAJob = lookingForAJob;
    }

    public String getLookingForAJobDescription() {
        return lookingForAJobDescription;
    }

    public void setLookingForAJobDescription(String lookingForAJobDescription) {
        this.lookingForAJobDescription = lookingForAJobDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id +
                ", following=" + following +
                ", contacts=" + contacts +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", lookingForAJob=" + lookingForAJob +
                ", lookingForAJobDescription='" + lookingForAJobDescription + '\'' +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ']';
    }
}
