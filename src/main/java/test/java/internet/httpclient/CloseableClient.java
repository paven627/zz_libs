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

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * This example demonstrates the recommended way of using API to make sure the
 * underlying connection gets released back to the connection manager.
 */
public class CloseableClient {

    public final static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
//        	?id=400000000392&type=2&token=e91de433a10baada0aa94ab2f3b87b95
            HttpGet httpget = new HttpGet("https://adapi.moji.com/api/misc/stop");
//            HttpGet httpget = new HttpGet("https://c.gdt.qq.com/gdt_mclick" +
//					".fcg?viewid" +
//
// "=fmfg4_WMRuggi8xfziSvr0pIA6mQwyj4GCW7U5tg7gejPvgMIDtVEOVindHbwiyBxYu81cP9ycCNkYOwl0epJpWWS_I_zKrsQMw49vBU0LxTkEfVRMU5BHdEQvoC1Vqap14RkKCYcI3eyy3cVUT2b0WX0!rKitRfq0SzaUbnBVDuvFx1WWpTdxKlooJvvX1qDGQZASQYyNATxFY78HBvoTEfX1vf6VKi&jtype=0&i=1&os=2&asi=%7B%22mf%22%3A%22SM-J7109%22%7D&acttype=1&s=%7B%22req_width%22%3A%22__REQ_WIDTH__%22%2C%22req_height%22%3A%22__REQ_HEIGHT__%22%2C%22width%22%3A%22__WIDTH__%22%2C%22height%22%3A%22__HEIGHT__%22%2C%22down_x%22%3A%22-999%22%2C%22down_y%22%3A%22-999%22%2C%22up_x%22%3A%22-999%22%2C%22up_y%22%3A%22-999%22%7D");
            System.out.println("Executing request " + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                StatusLine statusLine = response.getStatusLine();
                statusLine.getStatusCode();

                HttpEntity entity = response.getEntity();
                String s = EntityUtils.toString(entity);
                System.out.println(s);
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
