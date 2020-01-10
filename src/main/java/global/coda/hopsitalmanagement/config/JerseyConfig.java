package global.coda.hopsitalmanagement.config;
import global.coda.hopsitalmanagement.api.ServiceApi;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * The type Jersey config.
 */
public class JerseyConfig extends ResourceConfig {
    /**
     * Instantiates a new Jersey config.
     */
    public JerseyConfig() {
        register(ServiceApi.class);
    }
}
