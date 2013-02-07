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
import org.elasticsearch.action.admin.indices.stats.IndicesStats;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.shell.JsonSerializer;
import org.elasticsearch.shell.client.executors.AbstractRequestBuilder;

import java.io.IOException;

import static org.elasticsearch.rest.action.support.RestActions.buildBroadcastShardsHeader;

/**
 * @author Luca Cavanna
 *
 * Request builder for stats API
 */
@SuppressWarnings("unused")
public class StatsRequestBuilder<JsonInput, JsonOutput> extends AbstractRequestBuilder<IndicesStatsRequest, IndicesStats, JsonInput, JsonOutput> {

    public StatsRequestBuilder(Client client, JsonSerializer<JsonInput, JsonOutput> jsonSerializer) {
        super(client, new IndicesStatsRequest(), jsonSerializer);
    }

    public StatsRequestBuilder indices(String... indices) {
        this.request.indices(indices);
        return this;
    }

    public StatsRequestBuilder all() {
        request.all();
        return this;
    }

    public StatsRequestBuilder clear() {
        request.clear();
        return this;
    }

    public StatsRequestBuilder types(String... types) {
        request.types(types);
        return this;
    }

    public StatsRequestBuilder groups(String... groups) {
        request.groups(groups);
        return this;
    }

    public StatsRequestBuilder docs(boolean docs) {
        request.docs(docs);
        return this;
    }

    public StatsRequestBuilder store(boolean store) {
        request.store(store);
        return this;
    }

    public StatsRequestBuilder indexing(boolean indexing) {
        request.indexing(indexing);
        return this;
    }

    public StatsRequestBuilder get(boolean get) {
        request.get(get);
        return this;
    }

    public StatsRequestBuilder search(boolean search) {
        request.search(search);
        return this;
    }

    public StatsRequestBuilder merge(boolean merge) {
        request.merge(merge);
        return this;
    }

    public StatsRequestBuilder refresh(boolean refresh) {
        request.refresh(refresh);
        return this;
    }

    public StatsRequestBuilder flush(boolean flush) {
        request.flush(flush);
        return this;
    }

    public StatsRequestBuilder warmer(boolean warmer) {
        request.warmer(warmer);
        return this;
    }

    @Override
    protected ActionFuture<IndicesStats> doExecute(IndicesStatsRequest request) {
        return client.admin().indices().stats(request);
    }

    @Override
    protected XContentBuilder toXContent(IndicesStatsRequest request, IndicesStats response, XContentBuilder builder) throws IOException {
        builder.startObject();
        builder.field(Fields.OK, true);
        buildBroadcastShardsHeader(builder, response);
        response.toXContent(builder, ToXContent.EMPTY_PARAMS);
        builder.endObject();
        return builder;
    }
}