package maven.search;

import java.util.Arrays;

/**
 * Created by weiyi on 16/2/20.
 */
public class Project implements Comparable<Project> {

    private String groupId;

    private String version;

    private String artifactId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public int compareTo(Project o) {
        if(this.getVersion().equals(o.getVersion()))
            return 0;
        String[] arrays = new String[]{this.getVersion(),o.getVersion()};
        Arrays.sort(arrays);
        if(arrays[0].equals(this.getVersion())){
            return -1;
        }else {
            return 1;
        }

    }

    @Override
    public String toString() {
        return "Project{" +
                "groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
