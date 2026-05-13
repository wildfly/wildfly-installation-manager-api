package org.wildfly.installationmanager.spi;

import java.nio.file.Path;

public class InstallationManagerEnvironment {

    public static final String HOME_DIR = "jboss.home.dir";
    public static final String SERVER_BASE_DIR = "jboss.server.base.dir";
    public static final String SERVER_CONFIG_DIR = "jboss.server.config.dir";
    public static final String SERVER_DATA_DIR = "jboss.server.data.dir";
    public static final String SERVER_LOG_DIR = "jboss.server.log.dir";
    public static final String SERVER_TEMP_DIR = "jboss.server.temp.dir";
    public static final String CONTROLLER_TEMP_DIR = "jboss.controller.temp.dir";
    public static final String DOMAIN_BASE_DIR = "jboss.domain.base.dir";
    public static final String DOMAIN_CONFIG_DIR = "jboss.domain.config.dir";
    public static final String DOMAIN_DATA_DIR = "jboss.domain.data.dir";
    public static final String DOMAIN_LOG_DIR = "jboss.domain.log.dir";
    public static final String DOMAIN_SERVERS_DIR = "jboss.domain.servers.dir";
    public static final String DOMAIN_TEMP_DIR = "jboss.domain.temp.dir";

    public static final String STARTUP_MARKER_FILE = "startup-marker";

    private final Path homeDir;
    private final Path baseDir;
    private final Path configDir;
    private final Path dataDir;
    private final Path logDir;
    private final Path tempDir;
    private final Path controllerTempDir;
    private final Path serversDir;
    private final String markerFile;

    private InstallationManagerEnvironment(Builder builder) {
        this.homeDir = builder.homeDir;
        this.baseDir = builder.baseDir;
        this.configDir = builder.configDir;
        this.dataDir = builder.dataDir;
        this.logDir = builder.logDir;
        this.tempDir = builder.tempDir;
        this.controllerTempDir = builder.controllerTempDir;
        this.serversDir = builder.serversDir;
        this.markerFile = builder.markerFile;
    }

    public Path getHomeDir() {
        return homeDir;
    }

    public Path getBaseDir() {
        return baseDir;
    }

    public Path getConfigDir() {
        return configDir;
    }

    public Path getDataDir() {
        return dataDir;
    }

    public Path getLogDir() {
        return logDir;
    }

    public Path getTempDir() {
        return tempDir;
    }

    public Path getControllerTempDir() {
        return controllerTempDir;
    }

    public Path getServersDir() {
        return serversDir;
    }

    public String getMarkerFile() {
        return markerFile;
    }

    public Path getMarkerFilePath() {
        return tempDir != null ? tempDir.resolve(markerFile) : null;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Path homeDir;
        private Path baseDir;
        private Path configDir;
        private Path dataDir;
        private Path logDir;
        private Path tempDir;
        private Path controllerTempDir;
        private Path serversDir;
        private String markerFile = STARTUP_MARKER_FILE;

        private Builder() {
        }

        public Builder withHomeDir(Path homeDir) {
            this.homeDir = homeDir;
            return this;
        }

        public Builder withBaseDir(Path baseDir) {
            this.baseDir = baseDir;
            return this;
        }

        public Builder withConfigDir(Path configDir) {
            this.configDir = configDir;
            return this;
        }

        public Builder withDataDir(Path dataDir) {
            this.dataDir = dataDir;
            return this;
        }

        public Builder withLogDir(Path logDir) {
            this.logDir = logDir;
            return this;
        }

        public Builder withTempDir(Path tempDir) {
            this.tempDir = tempDir;
            return this;
        }

        public Builder withControllerTempDir(Path controllerTempDir) {
            this.controllerTempDir = controllerTempDir;
            return this;
        }

        public Builder withServersDir(Path serversDir) {
            this.serversDir = serversDir;
            return this;
        }

        public Builder withMarkerFile(String markerFile) {
            this.markerFile = markerFile;
            return this;
        }

        public InstallationManagerEnvironment build() {
            // if basedir is not provided we presume standalone
            if (homeDir != null && baseDir == null) {
                baseDir = homeDir.resolve("standalone");
            }
            if (baseDir != null) {
                if (configDir == null) {
                    configDir = baseDir.resolve("configuration");
                }
                if (dataDir == null) {
                    dataDir = baseDir.resolve("data");
                }
                if (logDir == null) {
                    logDir = baseDir.resolve("log");
                }
                if (tempDir == null) {
                    tempDir = baseDir.resolve("tmp");
                }
            }
            if (controllerTempDir == null) {
                controllerTempDir = tempDir;
            }
            return new InstallationManagerEnvironment(this);
        }
    }
}
