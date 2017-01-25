/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 *
 * @author glerisonlima
 */
public class TesteNumero {
    public static void main(String[] args) {
        String valor = "1540.10";
        BigDecimal v = new BigDecimal(NumberFormat.getIntegerInstance().format(valor));
        System.out.println(v);
    }
}
