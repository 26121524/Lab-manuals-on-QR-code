package net.javaguides.springboot.qr;

import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

//implementation of  methods declared in interface QRCodeService
@Service
public class QRServiceImpl implements QRCodeService {
    @Override
    public byte[] generate(String text, int width, int height) {
        try (ByteArrayOutputStream bos = QRCode.from(text).withSize(width, height).stream();) {
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
