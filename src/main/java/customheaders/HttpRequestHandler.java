package customheaders;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.Http;
import burp.api.montoya.http.handler.*;
import burp.api.montoya.http.message.HttpHeader;
import burp.api.montoya.logging.Logging;

public class HttpRequestHandler implements HttpHandler {
    private final Logging logging;
    public HttpRequestHandler(MontoyaApi api) {
        this.logging = api.logging();
    }

    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent requestToBeSent) {
        HttpHeader custom_header = HttpHeader.httpHeader("Random-Token", "random-value");
        logging.logToOutput("Added custom header!");
        return RequestToBeSentAction.continueWith(requestToBeSent.withAddedHeader(custom_header));
    }

    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived responseReceived) {
        return null;
    }
}
