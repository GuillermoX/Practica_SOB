/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authn;

import com.sun.xml.messaging.saaj.util.Base64;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 *
 * @author guillermo
 */
public class BasicAuthDecoder implements AuthDecoder{
    
    @Override
    public List<String> decode(String auth){
        ArrayList<String> decoded;
        try{
            auth = auth.replace("Basic ", "");
            String decode = Base64.base64Decode(auth);
            StringTokenizer tokenizer = new StringTokenizer(decode, ":");
            decoded = new ArrayList<>();
            decoded.add(tokenizer.nextToken());
            decoded.add(tokenizer.nextToken());
        }
        catch(NoSuchElementException e){
            return null;
        }
        return decoded;
    }
}
