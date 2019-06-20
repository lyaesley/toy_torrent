package io.toy.common.filestoreage.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UploadFile {
	
	private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

}
