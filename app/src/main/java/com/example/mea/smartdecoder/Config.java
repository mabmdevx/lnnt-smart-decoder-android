package com.example.mea.smartdecoder;

/**
 * Created by mea on 6/19/2016.
 */
public class Config {
    // File upload url (replace the ip with your server address)
    public static final String FILE_UPLOAD_URL = BuildConfig.SERVER_ADDR + "/smart_decoder/api/api_file_upload.php";

    // Directory name to store captured images and videos
    public static final String IMAGE_DIRECTORY_NAME = "smart_decoder";

    // Shell url
    public static final String SHELL_URL = BuildConfig.SERVER_ADDR + ":4200";

    // Diag url
    public static final String DIAG_URL = BuildConfig.SERVER_ADDR + "/smart_decoder/api/api_diag_info.php";
}
