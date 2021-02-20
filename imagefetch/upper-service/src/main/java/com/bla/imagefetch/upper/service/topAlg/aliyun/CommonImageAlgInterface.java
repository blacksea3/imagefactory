package com.bla.imagefetch.upper.service.topAlg.aliyun;

import com.bla.imagefetch.upper.service.VO.CommonAlgRequestVO;
import com.bla.imagefetch.upper.service.VO.CommonAlgResponseVO;

/**
 * 统一图片算法接口, 图片类算法全部实现这个接口
 *
 * @author blacksea3(jxt)
 * @date 2020/8/11 23:19
 */
public interface CommonImageAlgInterface {

    public CommonAlgResponseVO exec(CommonAlgRequestVO commonAlgRequestVO);

}
