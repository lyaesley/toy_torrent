package io.toy.torrent;

import io.toy.util.AwsS3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/torrent")
public class TorrentController {

	@Autowired
	AwsS3Util awsS3Util;

	@RequestMapping("/list")
	public String torrentList() {

		return "/torrent/torrentMain";
	}
}
