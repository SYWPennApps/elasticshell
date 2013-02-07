/*
 * Licensed to Luca Cavanna (the "Author") under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Elastic Search licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.elasticsearch.shell.client.executors.indices;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.warmer.put.PutWarmerRequest;
import org.elasticsearch.action.admin.indices.warmer.put.PutWarmerResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.shell.JsonSerializer;
import org.elasticsearch.shell.client.executors.AbstractRequestBuilder;

import java.io.IOException;

/**
 * @author Luca Cavanna
 *
 * Request builder for (put) warmer API
 */
@SuppressWarnings("unused")
public class PutWarmerRequestBuilder<JsonInput, JsonOutput> extends AbstractRequestBuilder<PutWarmerRequest, PutWarmerResponse, JsonInput, JsonOutput> {

    public PutWarmerRequestBuilder(Client client, JsonSerializer<JsonInput, JsonOutput> jsonSerializer) {
        super(client, new PutWarmerRequest(""), jsonSerializer);
    }

    public PutWarmerRequestBuilder name(String name) {
        this.request.name(name);
        return this;
    }

    public PutWarmerRequestBuilder searchRequest(SearchRequest searchRequest) {
        request.searchRequest(searchRequest);
        return this;
    }

    public PutWarmerRequestBuilder searchRequest(SearchRequestBuilder searchRequest) {
        request.searchRequest(searchRequest);
        return this;
    }

    @Override
    protected ActionFuture<PutWarmerResponse> doExecute(PutWarmerRequest request) {
        return client.admin().indices().putWarmer(request);
    }

    @Override
    protected XContentBuilder toXContent(PutWarmerRequest request, PutWarmerResponse response, XContentBuilder builder) throws IOException {
        builder.startObject()
                .field(Fields.OK, true)
                .field(Fields.ACKNOWLEDGED, response.acknowledged());
        builder.endObject();
        return builder;
    }
}