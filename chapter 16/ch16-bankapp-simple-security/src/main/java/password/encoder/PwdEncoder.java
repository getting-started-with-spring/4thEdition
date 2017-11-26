package password.encoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PwdEncoder {
	public static void main(String... args) {
		String cryptedPassword = new BCryptPasswordEncoder().encode("admin");
		System.out.println(cryptedPassword);
	}
}
