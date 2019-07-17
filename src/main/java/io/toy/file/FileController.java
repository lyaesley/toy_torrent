package io.toy.file;

import io.toy.util.AwsS3Util;
import io.toy.util.ConvUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j @RequestMapping(path="/file")
@RestController
public class FileController {

	@Autowired
	AwsS3Util awsS3Util;

	@Value("${path.upload.torrent}") String pathUploadTorrent;

	@RequestMapping(path="/img/upload")
	public String uploadImg(@RequestParam("files") List<MultipartFile> mtFiles, MultipartHttpServletRequest multiRes) throws Exception {
		log.info("####### File Upload Start #######");
		List<MultipartFile> nodes = multiRes.getFiles("files");
		List<UploadResponse> resList = new ArrayList<>();

		if(Files.notExists(Paths.get(pathUploadTorrent))) {
			Files.createDirectory(Paths.get(pathUploadTorrent));
			log.info("Upload Directory Create : " + Paths.get(pathUploadTorrent).toString());
		}

		mtFiles.stream().filter( e -> !Files.exists(Paths.get(pathUploadTorrent, e.getOriginalFilename()))).forEach( mtFile -> {
			try {

				String fullName = mtFile.getOriginalFilename();
				File saveFile = new File(pathUploadTorrent + fullName);

				//파일명과 확장자 분리
				int index = fullName.lastIndexOf(".");
				String fileName = fullName.substring(0, index);
				String fileExt = fullName.substring(index + 1);

				//해당 경로에 파일 생성
				Files.copy(mtFile.getInputStream(), Paths.get(pathUploadTorrent, mtFile.getOriginalFilename()));

				//업로드결과
				UploadResponse res = new UploadResponse();
				res.setFileName(mtFile.getOriginalFilename());
				res.setFileSize(mtFile.getSize());
				res.setFileContentType(mtFile.getContentType());
				res.setPath("/pic/"+res.getFileName());
				resList.add(res);
				log.info("--- " + res.getFileName() + " success ---");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		log.info("####### File Upload End #######");
		return  ConvUtil.toJsonObjectByClass(resList);
	}

	@RequestMapping(path="/s3/upload")
	public String s3Upload(@RequestParam("files") List<MultipartFile> mtFiles, MultipartHttpServletRequest multiRes) throws Exception {
		log.info("####### S3 Upload Start #######");
		List<MultipartFile> nodes = multiRes.getFiles("files");
		List<S3Response> resList = new ArrayList<>();

		if(Files.notExists(Paths.get(pathUploadTorrent))) {
			Files.createDirectory(Paths.get(pathUploadTorrent));
			log.info("Upload Directory Create : " + Paths.get(pathUploadTorrent).toString());
		}


		mtFiles.stream().forEach( mtFile -> {
			try {

				String fullName = mtFile.getOriginalFilename();
				File saveFile = new File(pathUploadTorrent + fullName);

				//파일명과 확장자 분리
				int index = fullName.lastIndexOf(".");
				String fileName = fullName.substring(0, index);
				String fileExt = fullName.substring(index + 1);

				//해당 경로에 파일 생성
				//Files.copy(mtFile.getInputStream(), Paths.get(pathUploadTorrent, mtFile.getOriginalFilename()));

				String resJson = awsS3Util.upload(mtFile, null);

				//업로드결과
				S3Response s3Response = new S3Response();

				s3Response = ConvUtil.toClassByJsonClass(S3Response.class, resJson);
				resList.add(s3Response);
				log.info("--- " + s3Response.getFileName() + " success ---");

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		log.info("####### S3 Upload End #######");
		return  ConvUtil.toJsonObjectByClass(resList);
	}

	@AllArgsConstructor // 모든 필드를 파라마미터로 가진 생성자
	@NoArgsConstructor //파라미터가 없는 생성자
	@Data
	private static class UploadResponse {

		private String fileName;

		private long fileSize;

		private String fileContentType;

		private String attachmentUrl;

		private String path;

	}

	@AllArgsConstructor // 모든 필드를 파라마미터로 가진 생성자
	@NoArgsConstructor //파라미터가 없는 생성자
	@Data
	private static class S3Response {
		private String bucketName;
		private String fileName;
		private long fileSize;
		private String attachmentUrl;
		private Date lastModified;

	}
}
