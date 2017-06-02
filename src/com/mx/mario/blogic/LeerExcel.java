/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.mario.blogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author MXI01020253A
 */
public class LeerExcel {

    public static void main(String args[]) throws IOException {
        FileInputStream file = new FileInputStream(new File("C:\\prueb_excel.xls"));
        /*
         * Obtenemos la primera pestaña a la que se quiera procesar indicando el indice.
         * Una vez obtenida la hoja excel con las filas que se quieren leer obtenemos el iterator
         * que nos permite recorrer cada una de las filas que contiene.
         */
        try ( // Crear el objeto que tendra el libro de Excel
                HSSFWorkbook workbook = new HSSFWorkbook(file)) {
            /*
            * Obtenemos la primera pestaña a la que se quiera procesar indicando el indice.
            * Una vez obtenida la hoja excel con las filas que se quieren leer obtenemos el iterator
            * que nos permite recorrer cada una de las filas que contiene.
             */
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row;
            // Recorremos todas las filas para mostrar el contenido de cada celda
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                // Obtenemos el iterator que permite recorres todas las celdas de una fila
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell celda;
                while (cellIterator.hasNext()) {
                    celda = cellIterator.next();
                    // Dependiendo del formato de la celda el valor se debe mostrar como String, Fecha, boolean, entero...

                    switch (celda.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(celda)) {
                                System.out.println(celda.getDateCellValue());
                            } else {
                                System.out.println(celda.getNumericCellValue());
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.println(celda.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            System.out.println(celda.getBooleanCellValue());
                            break;
                    }

                    /*try {
                    System.out.println(celda.getStringCellValue());
                    } catch (Exception e) {
                    System.err.println("Error: " + e);
                    }*/
                }
            }
            // cerramos el libro excel
        }
    }

}
