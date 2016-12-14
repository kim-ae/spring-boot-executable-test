package br.com.kimae.app;

import java.io.IOException;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.sources.URLConfigurationSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Properties {

    private final int REFRESH = 60000;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    static {
        System.setProperty("archaius.configurationSource.defaultFileName", "configuration.properties");
    }

    public Properties() {
        if (!ConfigurationManager.isConfigurationInstalled()) {
            ConfigurationManager.install(dynamicConfig());
            ConfigurationManager.getDeploymentContext().setDeploymentEnvironment(getEnvironmentVariable());
        }
        try {
            ConfigurationManager.loadCascadedPropertiesFromResources("configuration");
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    private String getEnvironmentVariable() {
        if (System.getenv("AUDITOR_ENVIRONMENT") != null)
            return System.getenv("AUDITOR_ENVIRONMENT");
        return "dev";
    }

    private DynamicConfiguration dynamicConfig() {
        return new DynamicConfiguration(new URLConfigurationSource(),
                new FixedDelayPollingScheduler(REFRESH, REFRESH, true));
    }

    public String getString(String value) {
        return DynamicPropertyFactory.getInstance().getStringProperty(value, "NOT FOUND").getValue();
    }
}
