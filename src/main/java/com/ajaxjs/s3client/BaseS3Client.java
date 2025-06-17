package com.ajaxjs.s3client;


import com.ajaxjs.util.http_request.model.ResponseEntity;
import lombok.Data;

@Data
public abstract class BaseS3Client implements S3Client {
    public static final String DATE = "Date";

    public static final String AUTHORIZATION = "Authorization";

    public static final String HTTPS = "https://";

    public static final String ACL_PUBLIC_READ = "public-read";

    private Config config;

    /**
     * 是否设置 HOST 字段到 请求头，如 true 则是 endpoint 去掉 http 后的地址
     */
    private boolean isSetHost;

    @Override
    public boolean putObject(String objectName, byte[] fileBytes) {
        return putObject(getConfig().getBucketName(), objectName, fileBytes);
    }

    @Override
    public boolean getObject(String objectName) {
        return getObject(getConfig().getBucketName(), objectName);
    }

    @Override
    public boolean deleteObject(String objectName) {
        return deleteObject(getConfig().getBucketName(), objectName);
    }

    /**
     * 检查响应实体是否表示成功。
     *
     * @param result 表示一个 HTTP 请求结果的响应实体对象。
     * @return 返回一个布尔值，如果 HTTP 响应码是200或204，表示成功，返回 true；否则，打印错误信息并返回 false。
     */
    public static boolean check(ResponseEntity result) {
        if (result.getHttpCode() == 200 || result.getHttpCode() == 204)
            return true;

        System.err.println(result);

        return false;
    }

    /**
     * 判断上传是否成功
     * 根据 HTTP 返回码和 ETag 是否存在来判断上传结果
     *
     * @param result 响应实体，包含 HTTP 响应信息和连接。
     * @param hash   用于比对的hash值，为 null时表示不进行hash值检查。
     * @return boolean 返回检查是否通过。通过返回 true，不通过返回 false。
     */
    protected static boolean eTagCheck(ResponseEntity result, String hash) {
        boolean isOk = true;
        String ETag = result.getConnection().getHeaderField("ETag");

        if (ETag == null)
            isOk = false;

        if (result.getHttpCode() != 200)
            isOk = false;

        if (hash != null && ETag != null && !ETag.equalsIgnoreCase("\"" + hash + "\""))
            isOk = false;

        if (!isOk)
            System.err.println(result);

        return isOk;
    }
}
