/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.io.File;


public class GenerateQRCode {

    public File generateQRCode(int exclusiveNumber) throws Exception {
        String data = "Scooter" + " " + exclusiveNumber;
        File image = new File("ScootersQR/Scooter" + exclusiveNumber + ".jpg");

        BitMatrix matrix = new MultiFormatWriter()
                .encode(data, BarcodeFormat.QR_CODE, 500, 500);

        MatrixToImageWriter.writeToFile(matrix, "jpg", image);

        return image;
    }

}
