package tech.vinc3nzo.prognet.jpa.models;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class representing a basic entity (or a model)
 * instances of which are to be stored in a database.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<User> following;

    private String firstName;
    private String lastName;
    private String patronymic;
    private String aboutMe;
    private boolean lookingForAJob;
    private String lookingForAJobDescription;
    private String status;
    private String email;
    private String password;
    private String username;
    private Date dateCreated;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Contacts contacts;

    protected User() { }

    public User(String firstName, String lastName, String patronymic,
                String aboutMe, @NonNull String email, @NonNull String password, @NonNull String username)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.aboutMe = aboutMe;
        this.email = email;
        this.password = password;
        this.username = username;
        this.following = new ArrayList<>();
        this.dateCreated = new Date(System.currentTimeMillis());
        this.lookingForAJob = false;
        this.lookingForAJobDescription = "";
        this.status = "";
        this.contacts = new Contacts(this);
    }

    public User(String fullName, String aboutMe, @NonNull String email,
                @NonNull String password, @NonNull String username)
    {
        String[] splited = fullName.split("\\s+");
        this.firstName = splited.length >= 1 ? splited[0] : "";
        this.lastName = splited.length >= 2 ? splited[1] : "";
        this.patronymic = splited.length >= 3 ? splited[2] : "";
        this.aboutMe = aboutMe;
        this.email = email;
        this.password = password;
        this.username = username;
        this.following = new ArrayList<>();
        this.dateCreated = new Date(System.currentTimeMillis());
        this.lookingForAJob = false;
        this.lookingForAJobDescription = "";
        this.status = "";
        this.contacts = new Contacts(this);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User)o;
        return lookingForAJob == user.lookingForAJob
                && id.equals(user.id)
                && Objects.equals(following, user.following)
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(patronymic, user.patronymic)
                && Objects.equals(aboutMe, user.aboutMe)
                && Objects.equals(lookingForAJobDescription, user.lookingForAJobDescription)
                && Objects.equals(status, user.status)
                && email.equals(user.email)
                && password.equals(user.password)
                && username.equals(user.username)
                && Objects.equals(dateCreated, user.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, following, firstName, lastName,
                patronymic, aboutMe, lookingForAJob, lookingForAJobDescription,
                status, email, password, username, dateCreated);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", following=" + following +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", lookingForAJob=" + lookingForAJob +
                ", lookingForAJobDescription='" + lookingForAJobDescription + '\'' +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
