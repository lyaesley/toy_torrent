package io.toy.torrent.core.properties;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class PropertiesConfig  implements EnvironmentPostProcessor{
	
	private static final String SERVICE_MODE = Optional.ofNullable(System.getProperty("SERVICE_MODE")).orElse("prod").toLowerCase();
	
	private final PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

		System.out.println("SERVICE_MODE: " + SERVICE_MODE);
		
		Resource path = new ClassPathResource("properties/application-" + SERVICE_MODE+ ".properties");
		
		List<PropertySource<?>> propertySource = loadProps(path);
		
		environment.getPropertySources().addFirst(propertySource.get(0));
		
	}
	
	private List<PropertySource<?>> loadProps(Resource path) {
		if (!path.exists()) {
			throw new IllegalArgumentException("Resource " + path + " does not exist");
		}
		try {
			return this.loader.load("properties", path);
		}
		catch (IOException ex) {
			throw new IllegalStateException(
					"Failed to load props configuration from " + path, ex);
		}
	}

}
