package io.toy.aws;

import io.toy.util.AwsS3Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class S3UtilTest {
	private AwsS3Util awsS3Util;

	@Before
	public void init() {
		awsS3Util = new AwsS3Util();
	}

	@Test
	public void getList() {

		awsS3Util.getBucketList();
	}
}
