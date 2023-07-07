/*
 * Copyright (c) 2022-2023. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package customheaders;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.logging.Logging;

//Burp will auto-detect and load any class that extends BurpExtension.
public class AddHeaderWithCustomValue implements BurpExtension
{
    public Logging logging;
    @Override
    public void initialize(MontoyaApi api)
    {
        // rename the extension
        api.extension().setName("Add custom headers");

        // configure the logging
        logging = api.logging();
        logging.logToOutput("Logger initialized!");

        // register proxy handlers with Burp.
        api.proxy().registerRequestHandler(new MyProxyHttpRequestHandler(api));
        api.proxy().registerResponseHandler(new MyProxyHttpResponseHandler(api));
    }
}