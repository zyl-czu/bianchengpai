package com.github.zylczu.web.common.image.rest;

import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeDeWrapper;

import com.github.zylczu.api.model.vo.ResVo;
import com.github.zylczu.api.model.vo.constants.StatusEnum;
import com.github.zylczu.core.permission.Permission;
import com.github.zylczu.core.permission.UserRole;
import com.github.zylczu.service.image.service.ImageService;
import com.github.zylczu.web.common.image.vo.ImageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

/**
 * 图片服务，要求登录之后才允许操作
 *
 * @author LouZai
 * @date 2022/9/7
 */
@Permission(role = UserRole.LOGIN)
@RequestMapping(path = {"image/", "admin/image/", "api/admin/image/",})
@RestController
@Slf4j
public class ImageRestController {

    @Autowired
    private ImageService imageService;

    /**
     * 图片上传
     *
     * @return
     */

    @RequestMapping(path = "upload")
    public ResVo<ImageVo> upload(HttpServletRequest request) {
        ImageVo imageVo = new ImageVo();
        try {
            String imagePath = imageService.saveImg(request);
            imageVo.setImagePath(imagePath);
        } catch (Exception e) {
            log.error("save upload file error!", e);
            return ResVo.fail(StatusEnum.UPLOAD_PIC_FAILED);
        }
        return ResVo.ok(imageVo);
    }

    /**
     * 二维码识别
     *
     * @param request
     * @return 识别的内容
     */
    @RequestMapping(path = "qrscan")
    public ResVo<String> qrscan(HttpServletRequest request) throws Exception {
        MultipartFile file = null;
        if (request instanceof MultipartHttpServletRequest) {
            file = ((MultipartHttpServletRequest) request).getFile("image");
        }
        if (file != null) {
            return ResVo.ok(QrCodeDeWrapper.decode(ImageIO.read(file.getInputStream())));
        }
        return ResVo.ok("nill");
    }

    /**
     * 转存图片
     *
     * @param imgUrl
     * @return
     */
    @RequestMapping(path = "save")
    public ResVo<ImageVo> save(@RequestParam(name = "img", defaultValue = "") String imgUrl) {
        ImageVo imageVo = new ImageVo();
        if (StringUtils.isBlank(imgUrl)) {
            return ResVo.ok(imageVo);
        }

        String url = imageService.saveImg(imgUrl);
        imageVo.setImagePath(url);
        return ResVo.ok(imageVo);
    }
}
