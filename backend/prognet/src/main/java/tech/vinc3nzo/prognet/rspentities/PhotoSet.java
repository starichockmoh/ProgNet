package tech.vinc3nzo.prognet.rspentities;

import java.net.URI;

public class PhotoSet {
    private URI small;
    private URI large;

    public PhotoSet() {
        this.small = null;
        this.large = null;
    }

    public PhotoSet(URI small, URI large) {
        this.small = small;
        this.large = large;
    }

    public URI getSmall() {
        return small;
    }

    public void setSmall(URI small) {
        this.small = small;
    }

    public URI getLarge() {
        return large;
    }

    public void setLarge(URI large) {
        this.large = large;
    }

    @Override
    public String toString() {
        return "PhotoSet{" +
                "small=" + small +
                ", large=" + large +
                '}';
    }
}
