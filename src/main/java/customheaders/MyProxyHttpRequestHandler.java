/*
 * Copyright (c) 2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package customheaders;


import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpHeader;
import burp.api.montoya.logging.Logging;
import burp.api.montoya.proxy.http.InterceptedRequest;
import burp.api.montoya.proxy.http.ProxyRequestHandler;
import burp.api.montoya.proxy.http.ProxyRequestReceivedAction;
import burp.api.montoya.proxy.http.ProxyRequestToBeSentAction;

class MyProxyHttpRequestHandler implements ProxyRequestHandler {
    private final Logging logging;
    public MyProxyHttpRequestHandler(MontoyaApi api) {
        this.logging = api.logging();
    }

    /**
     * This method is invoked before an HTTP request is received by the Proxy.
     */
    @Override
    public ProxyRequestReceivedAction handleRequestReceived(InterceptedRequest interceptedRequest) {
        // Add random UUID header to the intercepted request
        HttpHeader uuid_header = HttpHeader.httpHeader("Random-Token", GenerateRandomUUID.generateUUID());
        logging.logToOutput("added custom header!");
        return ProxyRequestReceivedAction.continueWith(interceptedRequest.withAddedHeader(uuid_header));
    }

    /**
     * This method is invoked after an HTTP request has been processed by the Proxy before it is sent.
     */
    @Override
    public ProxyRequestToBeSentAction handleRequestToBeSent(InterceptedRequest interceptedRequest) {
        //Do nothing with the user modified request, continue as normal.
        logging.logToError("continued normally.");
        return ProxyRequestToBeSentAction.continueWith(interceptedRequest);
    }
}
