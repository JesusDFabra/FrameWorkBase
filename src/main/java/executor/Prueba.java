package executor;

import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.CSVWriter;

import framework.data.dynamicValues.DynamicValuesCustomData;
import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;

public class Prueba {

	public static void main(String[] args) throws Exception {
		
//		DynamicValuesCustomData.setIdPrueba(1);
//		System.out.println("resultado: "+DynamicValuesCustomData.getNombre());
		
		Prueba p = new Prueba();
		Thread.sleep(5000);
		p.pruebaOCR3();
		
//		System.out.println(p.pruebaOCR());
//		p.pruebaOCR2();
		
		
//		Rectangle rec = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//		System.out.println(rec.getMaxX());
//		System.out.println(rec.getMaxY());
//		System.out.println(rec.getWidth());
//		System.out.println(rec.getHeight());
//		
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        Rectangle screenRect = ge.getMaximumWindowBounds();
//        int screenWidth = screenRect.width;
//        int screenHeight = screenRect.height;
//        System.out.println("Resolución de pantalla: " + screenWidth + "x" + screenHeight);
	}
	
	public String pruebaOCR() throws Exception {
		String charWhiteList = "aábcdeéfghiíjklmnñoópqrstuúvwxyzAÁBCDEÉFGHIÍJKLMNÑOÓPQRSTUÚVWXYZ0123456789 .,";
		
		File imageFile = new File("C:/Temp/6.png");
		BufferedImage image = ImageIO.read(imageFile);
		
		ITesseract tess = new Tesseract();
		System.setProperty("TESSERACT_DEBUG_LEVEL", "0");
			
		try {
			tess.setTessVariable("tessedit_char_whitelist", charWhiteList);
//			tess.setTessVariable("tessedit_write_images", "false");
			tess.setTessVariable("debug_file", "NUL");
			tess.setDatapath("./tessdata");
			tess.setLanguage("spa");
			String imageText = tess.doOCR(image);
			return imageText;
		}
		
		catch(Exception ex) {
			throw ex;
		}
	}
	
	public void pruebaOCR2() throws Exception {
		String charWhiteList = "aábcdeéfghiíjklmnñoópqrstuúvwxyzAÁBCDEÉFGHIÍJKLMNÑOÓPQRSTUÚVWXYZ0123456789 .,";
		
		File imageFile = new File("C:/Temp/6.png");
		BufferedImage image = ImageIO.read(imageFile);
		
		ITesseract tess = new Tesseract();
		System.setProperty("TESSERACT_DEBUG_LEVEL", "0");
		
		try {
//			tess.setTessVariable("tessedit_char_whitelist", charWhiteList);
//			tess.setTessVariable("tessedit_write_images", "false");
			tess.setTessVariable("debug_file", "NUL");
			tess.setDatapath("./tessdata");
			tess.setLanguage("spa");
			
			// Reconocer el texto en la imagen
            List<Word> words = tess.getWords(image, ITessAPI.TessPageIteratorLevel.RIL_WORD);
            
            // Buscar el texto específico y obtener su posición
            int x = -1;
            int y = -1;
            String textoBuscado = "resultado";
            for (Word word : words) {
                String texto = word.getText();
                if (texto.contains(textoBuscado)) {
                    Rectangle rect = word.getBoundingBox();
                    x = (rect.x + rect.width/2);
                    y = (rect.y + rect.height/2);
                    System.out.println("El texto '" + textoBuscado + "' se encuentra en las coordenadas: (" + x + ", " + y + ")");
                    break; // Terminar el bucle una vez que se haya encontrado el texto
                }
            }
            if (x == -1 && y == -1) {
                System.out.println("El texto no se encontró en la imagen.");
            }
		}
		
		catch(Exception ex) {
			throw ex;
		}
	}
	
	public void pruebaOCR3() throws Exception {
		
		Robot robot = new Robot();
		
		Rectangle pantalla = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage captura = robot.createScreenCapture(pantalla);
		
		ITesseract tess = new Tesseract();
		System.setProperty("TESSERACT_DEBUG_LEVEL", "0");
		
		try {
//			tess.setTessVariable("tessedit_char_whitelist", charWhiteList);
//			tess.setTessVariable("tessedit_write_images", "false");
			tess.setTessVariable("debug_file", "NUL");
			tess.setDatapath("./tessdata");
			tess.setLanguage("spa");
			
			// Reconocer el texto en la imagen
            List<Word> words = tess.getWords(captura, ITessAPI.TessPageIteratorLevel.RIL_WORD);
            
            // Buscar el texto específico y obtener su posición
            int x = -1;
            int y = -1;
            String textoBuscado = "variable";
            for (Word word : words) {
                String texto = word.getText();
                if (texto.contains(textoBuscado)) {
                    Rectangle rect = word.getBoundingBox();
                    x = (rect.x + rect.width/2);
                    y = (rect.y + rect.height/2);
                    System.out.println("El texto '" + textoBuscado + "' se encuentra en las coordenadas: (" + x + ", " + y + ")");
                    
                    robot.mouseMove(x, y);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    
                    break; // Terminar el bucle una vez que se haya encontrado el texto
                }
            }
            if (x == -1 && y == -1) {
                System.out.println("El texto no se encontró en la imagen.");
            }
		}
		
		catch(Exception ex) {
			throw ex;
		}
	}
	
	
}


