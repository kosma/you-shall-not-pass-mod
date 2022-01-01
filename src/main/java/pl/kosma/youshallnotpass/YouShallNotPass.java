package pl.kosma.youshallnotpass;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class YouShallNotPass implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("you-shall-not-pass");
    private static String FILE_PATH = "config/you-shall-not-pass.txt";

    public static String message = "Pay up bitches";

    @Override
    public void onInitialize() {
        try {
            message = new String(Files.readAllBytes(Paths.get(FILE_PATH)), StandardCharsets.UTF_8).strip();
        } catch (IOException e) {
            LOGGER.error("Failed to read %s: %s".formatted(FILE_PATH, e.getMessage()));
        }
        LOGGER.info("Yeet Message: %s".formatted(message));
    }
}
