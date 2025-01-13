package net.javaguides.springboot.qr;

public interface QRCodeService {
   byte[] generate(String text, int width, int height);
}