package com.spy.mall.gateway.config;

import com.spy.mall.common.utils.RsaUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


import javax.annotation.PostConstruct;
import java.security.PublicKey;
import java.util.List;

/**
 * @Author: spy
 * @Date: 2021/5/15 16:02
 */

@Data
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtProperties {

    private String pubKeyPath;
    private String cookieName;

    private PublicKey publicKey;

    @PostConstruct
    public void init() {
        try {
            // 把公钥文件和私钥文件对应的内容读取出来放入公钥和私钥对象
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
