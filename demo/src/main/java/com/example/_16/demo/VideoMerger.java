package com.example._16.demo;

import java.io.File;
import java.io.IOException;

public class VideoMerger {

	public static void main(String[] args) throws IOException {
		// The input videos to be merged
		String video1Path = "video1.mp4";
		String video2Path = "video2.mp4";

		// The output merged video file
		String mergedVideoPath = "merged_video.mp4";

		// Construct the FFmpeg command to merge the two videos
		ProcessBuilder pb = new ProcessBuilder(
				"ffmpeg",
				"-i", video1Path,
				"-i", video2Path,
				"-filter_complex", "[0:v][1:v]hstack=inputs=2[v];[0:a][1:a]amerge=inputs=2[a]",
				"-map", "[v]",
				"-map", "[a]",
				"-c:v", "libx264",
				"-crf", "23",
				"-preset", "ultrafast",
				"-c:a", "aac",
				"-b:a", "320k",
				"-ac", "2",
				"-ar", "44100",
				"-movflags", "+faststart",
				"-y", mergedVideoPath);

		// Run the FFmpeg command
		Process process = pb.start();
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
