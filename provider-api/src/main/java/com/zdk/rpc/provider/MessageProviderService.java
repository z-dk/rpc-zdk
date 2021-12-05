package com.zdk.rpc.provider;

/**
 * <b>类 名 称</b> :  MessageProvider<br/>
 * <b>类 描 述</b> :  rpc<br/>
 * <b>创 建 人</b> :  zhudengkui<br/>
 * <b>创建时间</b> :  2021/12/5 20:28<br/>
 * <b>修 改 人</b> :  zhudengkui<br/>
 * <b>修改时间</b> :  2021/12/5 20:28<br/>
 * <b>修改备注</b> :  <br/>
 *
 * @author zdk
 */
public interface MessageProviderService {

    /**
     * 建立初次连接
     * @param name 名称
     * @return 返回连接信息
     */
    String getConnection(String name);
    
}
