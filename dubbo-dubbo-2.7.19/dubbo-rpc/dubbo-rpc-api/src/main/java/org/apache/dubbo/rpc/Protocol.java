/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.rpc;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

import java.util.Collections;
import java.util.List;

/**
 * Protocol. (API/SPI, Singleton, ThreadSafe)
 */
@SPI("dubbo")
public interface Protocol {

    /**
     * Get default port when user doesn't config the port.
     *
     * @return default port
     */
    int getDefaultPort();

    /**
     * Export service for remote invocation: <br>
     * 1. Protocol should record request source address after receive a request:
     * RpcContext.getContext().setRemoteAddress();<br>
     * 2. export() must be idempotent, that is, there's no difference between invoking once and invoking twice when
     * export the same URL<br>
     * 3. Invoker instance is passed in by the framework, protocol needs not to care <br>
     *
     * 暴露远程服务：
     * 1.协议在接收请求时，应记录请求来源方地址信息：RpcContext.getContext().setRemoteAddress();
     * 2.export() 必须是幂等的，也就是暴露同一个 URL 的 Invoker 两次，和暴露一次没有区别；
     * 3.export() 传入的 Invoker 由框架实现并传入，协议不需要关心。
     *
     * @param <T>     服务的类型     Service type
     * @param invoker 服务的执行体    Service invoker
     * @return exporter 暴露服务的引用，用于取消暴露  exporter reference for exported service, useful for unexport the service later
     * @throws RpcException 当暴露服务出错时抛出，比如端口已占用  thrown when error occurs during export the service, for example: port is occupied
     */
    @Adaptive
    <T> Exporter<T> export(Invoker<T> invoker) throws RpcException;

    /**
     * Refer a remote service: <br>
     * 1. When user calls `invoke()` method of `Invoker` object which's returned from `refer()` call, the protocol
     * needs to correspondingly execute `invoke()` method of `Invoker` object <br>
     * 2. It's protocol's responsibility to implement `Invoker` which's returned from `refer()`. Generally speaking,
     * protocol sends remote request in the `Invoker` implementation. <br>
     * 3. When there's check=false set in URL, the implementation must not throw exception but try to recover when
     * connection fails.
     *
     * 引用远程服务：
     * 1.当用户调用 refer() 所返回的 Invoker 对象的 invoke() 方法时，协议需相应执行同 URL 远端 export() 传入的 Invoker 对象的 invoke() 方法；
     * 2.refer() 返回的 Invoker 由协议实现，协议通常需要在此 Invoker 中发送远程请求；
     * 3.当 url 中有设置 check=false 时，连接失败不能抛出异常，需内部自动恢复。
     *
     * @param <T>  服务的类型    Service type
     * @param type 服务的类型    Service class
     * @param url  远程服务的 URL 地址     URL address for the remote service
     * @return invoker 服务的本地代理      invoker service's local proxy
     * @throws RpcException 当连接服务提供方失败时抛出   when there's any error while connecting to the service provider
     */
    @Adaptive
    <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException;

    /**
     * Destroy protocol: <br>
     * 1. Cancel all services this protocol exports and refers <br>
     * 2. Release all occupied resources, for example: connection, port, etc. <br>
     * 3. Protocol can continue to export and refer new service even after it's destroyed.
     */
    void destroy();

    /**
     * Get all servers serving this protocol
     *
     * @return
     */
    default List<ProtocolServer> getServers() {
        return Collections.emptyList();
    }

}