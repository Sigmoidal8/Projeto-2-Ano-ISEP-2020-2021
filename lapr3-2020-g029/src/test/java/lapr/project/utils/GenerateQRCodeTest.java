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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GenerateQRCodeTest {
    
    public GenerateQRCodeTest() {
    }

    /**
     * Test of generateQRCode method, of class GenerateQRCode.
     */
    @Test
    public void testGenerateQRCode() throws Exception {
        System.out.println("generateQRCode");
        int randomNumber= 1;//(int)(Math.random()*100);
        String data = "Test" + " " + randomNumber;
        File image = new File("ScootersQRTests/Test" + randomNumber + ".jpg");

        BitMatrix matrix = new MultiFormatWriter()
                .encode(data, BarcodeFormat.QR_CODE, 500, 500);

        MatrixToImageWriter.writeToFile(matrix, "jpg", image);
    }
    //QR Code is generated to the folder called "ScooterQRTests" which is in the project folder
}
