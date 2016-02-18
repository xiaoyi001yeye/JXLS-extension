# JXLS-extension
JXLS扩展
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.riil.jxls-ext</groupId>
  <artifactId>jxls-ext</artifactId>
  <packaging>jar</packaging>
  <version>0.0.1-SNAPSHOT</version>
  <name>jXLS-ext</name>

  <properties>
	<jxls.version>1.0.7-SNAPSHOT</jxls.version>
  </properties>
  
  <dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>net.sf.jxls</groupId>
			<artifactId>jxls-core</artifactId>
			<version>${jxls.version}</version>
		</dependency>
		<dependency>
			<groupId>net.sf.jxls</groupId>
			<artifactId>jxls-examples</artifactId>
			<version>${jxls.version}</version>
		</dependency>
	</dependencies>
  </dependencyManagement>
 

    <repositories>
        <repository>
            <id>sourceforge</id>
            <name>jXLS snapshot repository</name>
            <url>http://jxls.sourceforge.net/repository/snapshots</url>
            <releases>
                <enabled>false</enabled>
            </releases>
        </repository>
    </repositories>


    <!--
	<build>
		<plugins>
		  <plugin>
			<artifactId>maven-assembly-plugin</artifactId>
			<configuration>
			  <descriptors>
				<descriptor>src/main/assembly/release.xml</descriptor>
			  </descriptors>
			</configuration>
		  </plugin>
			<plugin>
			  <groupId>org.apache.maven.plugins</groupId>
			  <artifactId>maven-site-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
	-->

  
</project>
