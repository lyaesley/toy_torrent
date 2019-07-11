package io.toy.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.iterable.S3Objects;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class AwsS3Util {
	@Value("${cloud.aws.credentials.accessKey}")
	private String ACCESSKEY;
	@Value("${cloud.aws.credentials.secretKey}")
	private String SECRETKEY;
	@Value("${cloud.aws.s3.bucket}")
	private String BUCKET;
	@Value("${cloud.aws.s3.region.static}")
	private String REGION;

	private AmazonS3 amazonS3;

	/**
	 * 위에 @Value 의 값을 생성자에서 사용할 경우 null 이 됨  @PostConstruct 를 사용하면 Bean 생성후 init 실행함.
	 * 대신 사용하는 곳에서 new Object(); 로는 init 되지 않고 @Autowired 를 사용해야 하는듯... 심오하다..
 	 */
	@PostConstruct
	public void init() {
		log.info("{} / {}", ACCESSKEY, SECRETKEY);
		AWSCredentials credentials = new BasicAWSCredentials(ACCESSKEY, SECRETKEY);
		amazonS3 = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.fromName(REGION))
				.build();
	}

	// 버킷 리스트를 가져오는 메서드이다.
	public List<Bucket> getBucketList() {
		return amazonS3.listBuckets();
	}

	public void getObjectList() {
		S3Objects.withPrefix(amazonS3, BUCKET, "torrent").forEach((S3ObjectSummary objectSummary) -> {
			System.out.println(objectSummary.getKey());
		});
		//S3ObjectSummarys 의 목록 은 필요할 때마다 한 번에 한 페이지 씩 느리게 가져옵니다.
		// 이 withBatchSize(int)방법으로 페이지의 크기를 제어 할 수 있습니다 .
	}

	public String upload(MultipartFile multipartFile, String dirName) throws IOException {
		File uploadFile = convert(multipartFile)
				.orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

		return uploadFile(uploadFile, dirName);
	}

	private String uploadFile(File uploadFile, String dirName) {
		String fileName = dirName + "/" + uploadFile.getName();
		String uoloadTorrentUrl = put(uploadFile, fileName);
		removeNewFile(uploadFile);
		return uoloadTorrentUrl;
	}

	private void removeNewFile(File targetFile) {
		if(targetFile.delete()) {
			log.info("파일이 삭제됐습니다.");
		}else {
			log.info("파일이 삭제되지 않았습니다.");
		}

	}

	private String put(File uploadFile, String fileName) {
		amazonS3.putObject(new PutObjectRequest(BUCKET, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
		return amazonS3.getUrl(BUCKET, fileName).toString();
	}

	private Optional<File> convert(MultipartFile file) throws IOException {
		File convertFile = new File(file.getOriginalFilename());
		if(convertFile.createNewFile()) {
			try (FileOutputStream fos = new FileOutputStream(convertFile)) {
				fos.write(file.getBytes());
			}
			return Optional.of(convertFile);
		}

		return Optional.empty();
	}


}
