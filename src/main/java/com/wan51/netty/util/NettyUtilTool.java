package com.wan51.netty.util;

import com.alibaba.fastjson.JSONObject;
import com.wan51.netty.RequestParam;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSONString;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by gm665 on 2017/3/22.
 */
public class NettyUtilTool {

    private static final Logger logger = Logger.getLogger(NettyUtilTool.class);

    private static final AsciiString CONNECTION = new AsciiString("Connection");
    private static final AsciiString KEEP_ALIVE = new AsciiString("keep-alive");

    static ChannelFutureListener CLOSE_CHANNEL = new ChannelFutureListener() {
        // Logger logger = Logger.getLogger("CLOSE CHANNEL");

        @Override
        public void operationComplete(ChannelFuture future) {
            logger.info("channel close!");
            future.channel().disconnect();
            future.channel().close();
        }
    };

    /**
     * 返回json数据到客户端
     * @param ctx
     * @param req
     * @param object
     */
    public static void writeToClient(ChannelHandlerContext ctx, FullHttpRequest req, Object object) {

        ByteBuf content = Unpooled.copiedBuffer(JSONObject.toJSONString(object), CharsetUtil.UTF_8);
        FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, OK, content);

        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(res, res.content().readableBytes());
        }
        boolean keepAlive = HttpUtil.isKeepAlive(req);
        res.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        HttpUtil.setContentLength(res, content.readableBytes());

        if (!keepAlive) {
            ctx.write(res).addListener(ChannelFutureListener.CLOSE);
        } else {
            res.headers().set(CONNECTION, KEEP_ALIVE);
            ctx.write(res);
        }
    }

    /**
     * 封装参数
     * @param requestParam
     * @param request
     */
    public static void getParamFromUri(RequestParam requestParam, FullHttpRequest request){
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.uri());
        Map<String, List<String>> params = queryStringDecoder.parameters();
        String token = extractParamsValue(params,"token");
        String service = extractParamsValue(params,"service");
        String action = extractParamsValue(params,"action");
        String data = extractParamsValue(params,"data");
        requestParam.setToken(token);
        requestParam.setService(service);
        requestParam.setAction(action);
        requestParam.setData(data);
    }

    /**
     * 从params提取数据
     * @param params
     * @param key
     * @return
     */
    public static String extractParamsValue(Map<String, List<String>> params,String key) {
        if (StringUtils.isEmpty("key")) {
            logger.error("the key is empty.");
            return "";
        }
        if (params == null || params.size() == 0) {
            logger.error("sorry, the params is null.");
            return "";
        }
        List<String> valueList = params.get(key);
        if (valueList == null || valueList.size() == 0) {
            return "";
        }
        if (valueList.size() > 1) {
            logger.error("the size() of valueList [key :" + key + "]  is +"
                    + valueList.size()
                    + ", i will use the last index of valueList[value:"
                    + valueList.get(valueList.size() - 1) + "]");
            return valueList.get(valueList.size() - 1);
        }
        return valueList.get(0);
    }

}
