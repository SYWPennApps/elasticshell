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
package org.elasticsearch.shell.command;

import org.apache.http.client.fluent.Request;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.shell.console.Console;

import java.io.IOException;
import java.io.PrintStream;

/**
 * @author Luca Cavanna
 *
 * Command that sends http DELETE requests to the url provided as input
 */
@ExecutableCommand(aliases = {"httpDelete", "delete"})
public class HttpDeleteCommand extends Command {

    @Inject
    HttpDeleteCommand(Console<PrintStream> console) {
        super(console);
    }

    @SuppressWarnings("unused")
    public HttpCommandResponse execute(String url) throws IOException {
        return new HttpCommandResponse(Request.Delete(url).execute().returnResponse());
    }
}
