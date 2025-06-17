[![Maven Central](https://img.shields.io/maven-central/v/com.ajaxjs/aj-s3client?label=Latest%20Release)](https://central.sonatype.com/artifact/com.ajaxjs/aj-s3client)
[![Javadoc](https://img.shields.io/badge/javadoc-1.1-brightgreen.svg?)](https://dev.ajaxjs.com/docs/javadoc/aj-s3client/)
[![License](https://img.shields.io/badge/license-Apache--2.0-green.svg?longCache=true&style=flat)](http://www.apache.org/licenses/LICENSE-2.0.txt)
[![Email](https://img.shields.io/badge/Contact--me-Email-orange.svg)](mailto:frank@ajaxjs.com)

# A Lightweight S3 Client / S3 存储 Java 客户端

Tutorial: https://blog.csdn.net/zhangxin09/article/details/137671230.

Java Documents: https://dev.ajaxjs.com/docs/javadoc/aj-s3client/.

Support these S3 Storages directly:

- Cloudflare R2
- Scaleway
- Backblaze
- Aliyun OSS
- Netease OSS

## Install

```xml

<dependency>
    <groupId>com.ajaxjs</groupId>
    <artifactId>aj-s3client</artifactId>
    <version>1.1</version>
</dependency>
```

The configuration file `application.yml` for unit tests was not committed with the VCS. Its content is as follows:

```yaml
S3Storage:
  Nso:
    accessKey: xx
    accessSecret: xx
    api: nos-eastchina1.126.net
    bucket: xx
  Oss:
    accessKeyId: xx
    secretAccessKey: xx
    endpoint: oss-cn-beijing.aliyuncs.com
    bucket: xx
  LocalStorage: # 本地保存
    absoluteSavePath: c:\temp\ # 若有此值，保存这个绝对路径上
```