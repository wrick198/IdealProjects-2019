package com.knowledge_network.support.server;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by pentonbin on 18-4-4
 * <p>
 * 封装加密后的HttpServlet
 */
public class EncryptHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream output;
    private EncryptServletOutputStream encryptOutputStream;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response
     * @throws IllegalArgumentException if the response is null
     */
    public EncryptHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new ByteArrayOutputStream();
        encryptOutputStream = new EncryptServletOutputStream(output);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return encryptOutputStream;
    }


    public byte[] getDataStream() {
        return output.toByteArray();
    }
}
