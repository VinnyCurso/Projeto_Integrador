/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author vinicius caetano
 */
public class ManipulaImagem {
    
        public static byte[] getImgBytes(RenderedImage image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", baos);
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }

        InputStream is = new ByteArrayInputStream(baos.toByteArray());

        return baos.toByteArray();
    }

    public static BufferedImage retornaImagem(byte[] img) {

        if (img != null) {
            try {
                InputStream input = new ByteArrayInputStream(img);
                BufferedImage imagem = ImageIO.read(input);
                return imagem;
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }else{
             return null;
        }
    }
    
}
