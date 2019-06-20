package io.toy.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
public class AwsS3Util {
	@Value("${cloud.aws.credentials.accessKey}")
	private String ACCESSKEY;
	@Value("${cloud.aws.credentials.secretKey}")
	private String SECRETKEY;

	private AmazonS3 conn;

	@PostConstruct
	public void init() {
		log.info("{} / {}", ACCESSKEY, SECRETKEY);
		AWSCredentials credentials = new BasicAWSCredentials(ACCESSKEY, SECRETKEY);
		conn = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.AP_NORTHEAST_2)
				.build();
	}

	// 버킷 리스트를 가져오는 메서드이다.
	public List<Bucket> getBucketList() {
		return conn.listBuckets();
	}


}
