package io.toy.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.iterable.S3Objects;
import com.amazonaws.services.s3.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

	public List<S3Response> getObjectList() {

		List<S3Response> s3ResponsesList = new ArrayList<>();


		S3Objects.withPrefix(amazonS3, BUCKET, "torrent").forEach((S3ObjectSummary objectSummary) -> {
			S3Response s3Response = new S3Response();

			s3Response.setBucketName(objectSummary.getBucketName());
			s3Response.setFileName(objectSummary.getKey());
			s3Response.setFileSize(objectSummary.getSize());
			s3Response.setLastModified(objectSummary.getLastModified());
			s3Response.setAttachmentUrl(amazonS3.getUrl(s3Response.getBucketName(), s3Response.getFileName()).toString());

			s3ResponsesList.add(s3Response);
		});
		//S3ObjectSummarys 의 목록 은 필요할 때마다 한 번에 한 페이지 씩 느리게 가져옵니다.
		// 이 withBatchSize(int)방법으로 페이지의 크기를 제어 할 수 있습니다 .

		return s3ResponsesList;
	}

	public String upload(MultipartFile multipartFile, String s3DirName) throws IOException {
		File uploadFile = convert(multipartFile)
				.orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));
		Optional<String> dirName = Optional.ofNullable(s3DirName);
		return uploadFile(uploadFile, dirName.orElse("torrent"));
	}

	private String uploadFile(File uploadFile, String dirName) {
		String fileName = dirName + "/" + uploadFile.getName();
		String uoloadFileUrl = put(uploadFile, fileName);
		removeNewFile(uploadFile);
		return uoloadFileUrl;
	}

	private void removeNewFile(File targetFile) {
		if(targetFile.delete()) {
			log.info("TEMP 파일이 삭제됐습니다.");
		}else {
			log.info("TEMP 파일이 삭제되지 않았습니다.");
		}

	}

	private String put(File uploadFile, String fileName) {
		amazonS3.putObject(new PutObjectRequest(BUCKET, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));

		S3Response s3Response = new S3Response();
		S3Object s3Objects = amazonS3.getObject(BUCKET, fileName);

		s3Response.setBucketName(s3Objects.getBucketName());
		s3Response.setFileName(s3Objects.getKey());
		//s3Response.setFileSize(s3Objects.getSize());
		//s3Response.setLastModified(s3Objects.getLastModified());
		s3Response.setAttachmentUrl(amazonS3.getUrl(s3Response.getBucketName(), s3Response.getFileName()).toString());

		return ConvUtil.toJsonObjectByClass(s3Response);
	}

	/*
	private String put(File uploadFile, String fileName) {
		amazonS3.putObject(new PutObjectRequest(BUCKET, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
		return amazonS3.getUrl(BUCKET, fileName).toString();
	}
	*/
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

	@AllArgsConstructor // 모든 필드를 파라마미터로 가진 생성자
	@NoArgsConstructor //파라미터가 없는 생성자
	@Data
	public static class S3Response {
		private String bucketName;
		private String fileName;
		private long fileSize;
		private String attachmentUrl;
		private Date lastModified;

	}
}
