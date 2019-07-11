package io.toy.aws;

import com.amazonaws.services.s3.model.Bucket;
import io.toy.util.AwsS3Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class S3UtilTest {
	@Autowired AwsS3Util awsS3Util;

	@Test
	public void getBucketList() {

		List<Bucket> bucketList = awsS3Util.getBucketList();

		for(Bucket node : bucketList) {
			System.out.println(node.getName());
		}
	}

	@Test
	public void getObjectList() {
		awsS3Util.getObjectList();
	}
}
