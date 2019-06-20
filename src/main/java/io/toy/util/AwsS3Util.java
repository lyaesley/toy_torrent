package io.toy.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;

public class AwsS3Util {
	@Value("${key.amazon.s3.accessKey}")
	String ACCESSKEY;
	@Value("${key.amazon.s3.secretKey}")
	String SECRETKEY;

	private AmazonS3 conn;

	public AwsS3Util() {
		AWSCredentials credentials = new BasicAWSCredentials(ACCESSKEY, SECRETKEY);
		conn = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.AP_NORTHEAST_2)
				.build();
	}


}
