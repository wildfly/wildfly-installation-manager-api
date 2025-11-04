package org.wildfly.installationmanager;

public class ChannelVersionChange {

    private String channelName;
    private String oldVersion;
    private String newVersion;
    private boolean isDowngrade;

    public ChannelVersionChange(String channelName, String oldVersion, String newVersion, boolean isDowngrade) {
        this.channelName = channelName;
        this.oldVersion = oldVersion;
        this.newVersion = newVersion;
        this.isDowngrade = isDowngrade;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getOldVersion() {
        return oldVersion;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public boolean isDowngrade() {
        return isDowngrade;
    }
}
