package com.ajaxjs.s3client.factory;


import com.ajaxjs.s3client.util.S3SigV4Utils;
import com.ajaxjs.util.ObjectHelper;
import com.ajaxjs.util.http_request.Post;
import com.ajaxjs.util.http_request.model.HttpConstants;

import java.util.Map;

public class Scaleway extends CloudflareR2 {
    @Override
    public boolean putObject(String bucketName, String objectName, byte[] fileBytes) {
        String now = S3SigV4Utils.now();
        String url = getConfig().getEndPoint() + "/" + bucketName + "/" + objectName;
        String contentSha256 = S3SigV4Utils.calcFileSHA256(fileBytes);
        Map<String, String> acl = ObjectHelper.mapOf("x-amz-acl", ACL_PUBLIC_READ);

        String signature = initSignatureBuilder(now, contentSha256, acl).getS3Signature(getCanonicalRequest(HttpConstants.PUT, url), contentSha256);

        return check(Post.put(url, fileBytes, setRequestHead(now, signature, contentSha256, acl)));
    }
}
