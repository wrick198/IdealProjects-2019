package com.knowledge_network.support.server;

import javax.servlet.ServletOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by pentonbin on 18-4-4
 */
public class EncryptServletOutputStream extends ServletOutputStream {

    private DataOutputStream output;

    public EncryptServletOutputStream(OutputStream outputStream) {
        this.output = new DataOutputStream(outputStream);
    }

    @Override
    public void write(int b) throws IOException {
        output.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        output.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        output.write(b, off, len);
    }
}
