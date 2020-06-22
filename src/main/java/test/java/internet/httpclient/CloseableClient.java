/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package test.java.internet.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.util.UUID;

public class CloseableClient {
    static String url = "http://10.11.12.26:8080/imp?adx=1000&width=320&height=480&unqid=af01c478-eaf0-48e0-bb6f" +
            "-d22f4231b557" +
            "&request_time=1589185175547&net=0&os=1&imei=8635590393099185&oaid=ffe7fba7-9bff-2862-ddfe-deffdbbd72f7" +
            "&device=HUAWEI&sid=0f484f948e7e1d829602b29677a80f2d&ip=101.29.75" +
            ".209&requestId=SocketTestRequestId&position_id=9383938&advertising_media=1001&adx_id=1000&ad_type=1" +
            "&advertiser_id=1211&campaign_id=1109&ad_group_id=1171&creative_id=1164&agent_id=1209&core_agent_id=1" +
            "&price=KwlJVJSPuqtiYCOR06JKGg&settlement_price=";


    public final static void main(String[] args) throws Exception {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(10);
        cm.setDefaultMaxPerRoute(2);
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
        CloseableHttpResponse response = null;
        for (int i = 0; i < 1; i++) {
            String uuid = UUID.randomUUID().toString();

            try {
                HttpGet httpget = new HttpGet(url);
                System.out.println("Executing request " + httpget.getRequestLine());
                response = httpclient.execute(httpget);
                System.out.println("-----------------------------------------");

                HttpEntity entity = response.getEntity();
                String s = EntityUtils.toString(entity);
                System.out.println(s);
                EntityUtils.consume(entity);
            } finally {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            }
        }
    }

}
