package io.toy.torrent;

import io.toy.util.AwsS3Util;
import io.toy.util.ConvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller @RequestMapping("/torrent")
public class TorrentController {

	@Autowired
	AwsS3Util awsS3Util;

	@RequestMapping("/list")
	public String torrentList(HttpServletRequest request) {

		request.setAttribute("s3List", ConvUtil.toJsonObjectByClass(awsS3Util.getObjectList()));

		request.setAttribute("test", "우와앙");

		return "/torrent/torrentMain";
	}
}
