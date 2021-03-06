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

import java.io.IOException;
import java.io.PrintStream;

import org.apache.http.client.methods.HttpHead;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.shell.console.Console;
import org.elasticsearch.shell.http.ShellHttpClient;

/**
 * @author Luca Cavanna
 *
 * Command that sends http HEAD requests to the url provided as input
 */
@ExecutableCommand(aliases = {"httpHead", "head"})
public class HttpHeadCommand extends Command {

    private final ShellHttpClient shellHttpClient;

    @Inject
    HttpHeadCommand(Console<PrintStream> console, ShellHttpClient shellHttpClient) {
        super(console);
        this.shellHttpClient = shellHttpClient;
    }

    @SuppressWarnings("unused")
    public HttpCommandResponse execute(String url) throws IOException {
        return new HttpCommandResponse(shellHttpClient.getHttpClient().execute(new HttpHead(url)));
    }
}
