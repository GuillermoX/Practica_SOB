/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authn;

import java.util.List;

/**
 *
 * @author guillermo
 */
public interface AuthDecoder {
    public List<String> decode(String auth); 
}
