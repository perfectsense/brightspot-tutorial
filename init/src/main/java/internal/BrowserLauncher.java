package internal;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.psddev.dari.util.Settings;

@WebListener
public class BrowserLauncher implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String mavenArgs = Settings.get(String.class, "MAVEN_CMD_LINE_ARGS");
        String tomcatPort = Settings.get(String.class, "tomcat.port");

        if (System.getProperty(BrowserLauncher.class.getName()) != null
                || mavenArgs == null || !mavenArgs.contains("cargo:run")
                || tomcatPort == null
                || !Desktop.isDesktopSupported()) {
            return;
        }

        // set a system level flag so that the browser doesn't launch again when tomcat is reloaded.
        System.setProperty(BrowserLauncher.class.getName(), "true");

        try {
            Desktop.getDesktop().browse(new URI("http://localhost:" + tomcatPort + "/cms"));
        } catch (IOException | URISyntaxException e) {
            // fail silently
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
