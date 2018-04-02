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
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.net.URL;

/**
 * This example demonstrates how to abort an HTTP method before its normal
 * completion.
 */
public class ClientAbortMethod {

	public final static void main(String[] args) throws Exception {
		URL urlOfClass = ClientAbortMethod.class.getClassLoader()
				.getResource("org/slf4j/spi/LocationAwareLogger.class");
		System.out.println(urlOfClass);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// HttpGet httpget = new
			// HttpGet("http://c.gdt.qq.com/gdt_mclick.fcg?viewid=fmfg4_WMRuggi8xfziSvr0pIA6mQwyj4GCW7U5tg7gejPvgMIDtVEOVindHbwiyBxYu81cP9ycCNkYOwl0epJpWWS_I_zKrsQMw49vBU0LxTkEfVRMU5BHdEQvoC1Vqap14RkKCYcI3eyy3cVUT2b0WX0!rKitRfq0SzaUbnBVDuvFx1WWpTdxKlooJvvX1qDGQZASQYyNATxFY78HBvoTEfX1vf6VKi&jtype=0&i=1&os=2&asi=%7B%22mf%22%3A%22SM-J7109%22%7D&acttype=1&s=%7B%22req_width%22%3A%22__REQ_WIDTH__%22%2C%22req_height%22%3A%22__REQ_HEIGHT__%22%2C%22width%22%3A%22__WIDTH__%22%2C%22height%22%3A%22__HEIGHT__%22%2C%22down_x%22%3A%22-999%22%2C%22down_y%22%3A%22-999%22%2C%22up_x%22%3A%22-999%22%2C%22up_y%22%3A%22-999%22%7D");

			for (int i = 0; i < 1; i++) {

				HttpPost httpget = new HttpPost();
				httpget.setURI(new URI("http://192.168.1.23:9000/ad/log/stat"));
				httpget.setEntity(new StringEntity(
						"{\"common\":{\"identifier\":\"352425060557231\",\"app_version\":\"1007030801\","
								+ "\"os_version\":\"23\",\"device\":\"SM-J7109\",\"platform\":\"Android\",\"pid\":\"4999\","
								+ "\"language\":\"CN\",\"uid\":\"330797438\",\"width\":1080,\"height\":1920,"
								+ "\"package_name\":\"com.moji.mjweather\",\"net\":\"wifi\",\"unix\":1521604204175},"
								+ "\"params\":{\"ad_id\":400000000170,\"ad_index\":4007,\"ad_index_part\":-1,\"ad_partner\":-1,\"ad_type\":4,\"adverting_type\":1,\"assistType\":0,\"carrier\":4,\"city_id\":503,\"down_x\":\"383\",\"down_y\":\"206\",\"height\":\"295\",\"materiel_id\":\"400000000173\",\"net\":\"wifi\",\"openType\":false,\"position\":\"POS_FEED_STREAM_DETAILS\",\"price\":\"10\",\"property_type\":4,\"purpose\":0,\"requestId\":\"c4391fca-842c-4e62-bfc8-eacab4bdb269\",\"stat_type\":2,\"stat_value\":1,\"tabType\":0,\"uid\":330797438,\"unqid\":\"bdc7c5aa-93d5-4c53-b7ae-400021dad497\",\"up_x\":\"383\",\"up_y\":\"207\",\"weather\":\"-\",\"width\":\"1080\"}}"));

				System.out.println("Executing request " + httpget.getURI());
				CloseableHttpResponse response = httpclient.execute(httpget);
				try {
					System.out.println("----------------------------------------");
					System.out.println(response.getStatusLine());
					HttpEntity entity = response.getEntity();
					String string = EntityUtils.toString(entity);
					System.out.println(string);
					// Do not feel like reading the response body
					// Call abort on the request object
					httpget.abort();
				} finally {
					response.close();
				}
			}
		} finally {
			httpclient.close();
		}

	}

}
