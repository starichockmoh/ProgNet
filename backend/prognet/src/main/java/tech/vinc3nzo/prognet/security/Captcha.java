package tech.vinc3nzo.prognet.security;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

public class Captcha {
    static private final List<CaptchaPair> captchaList;

    static {
        try {
            captchaList = List.of(
                    CaptchaPair.of(new URI("https://i.ibb.co/8jnVMKb/captcha-1.png"), "5718"),
                    CaptchaPair.of(new URI("https://i.ibb.co/ctt7C36/captcha-2.png"), "9812"),
                    CaptchaPair.of(new URI("https://i.ibb.co/xjQZrbm/captcha-3.png"), "4512")
            );
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    static private final Random generator = new Random();

    static public CaptchaPair getRandom() {
        if (captchaList.size() <= 1) {
            return null;
        }
        return captchaList.get(generator.nextInt(captchaList.size()));
    }

    static public CaptchaPair getRandomButNot(CaptchaPair pair) {
        if (captchaList.size() <= 1) {
            return null;
        }
        while (true) {
            CaptchaPair entry = captchaList.get(generator.nextInt(captchaList.size()));
            if (!entry.equals(pair)) {
                return entry;
            }
        }
    }
}
