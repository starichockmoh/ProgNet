package tech.vinc3nzo.prognet.security;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.net.URI;
import java.util.Objects;

@Entity
public class CaptchaPair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private URI imageUri;
    private String answer;

    protected CaptchaPair() {}

    public CaptchaPair(@NonNull URI imageUri, @NonNull String answer) {
        this.imageUri = imageUri;
        this.answer = answer;
    }

    public static CaptchaPair of(@NonNull URI imageUri, @NonNull String answer) {
        var pair = new CaptchaPair();
        pair.imageUri = imageUri;
        pair.answer = answer;
        return pair;
    }

    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public URI getImageUri() {
        return imageUri;
    }

    public void setImageUri(@NonNull URI imageUri) {
        this.imageUri = imageUri;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(@NonNull String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaptchaPair that = (CaptchaPair) o;
        return id.equals(that.id) && imageUri.equals(that.imageUri) && answer.equals(that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageUri, answer);
    }

    @Override
    public String toString() {
        return "CaptchaPair{" +
                "id=" + id +
                ", imageUri=" + imageUri +
                ", answer='" + answer + '\'' +
                '}';
    }
}
