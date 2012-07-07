package dominio.statistics;

import java.awt.*;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


import net.sourceforge.jpcap.client.*;
import net.sourceforge.jpcap.net.*;

public class StAplicationLayer extends JFrame{
  
   protected Packet paquete;
 
   
   static protected int[] vaplicationlayer = new int[7];
   static protected String[][] hack = new String[10][2];
   
   public StAplicationLayer (CaptureHistory history) {
  
   super ("Estadisticas Nivel Aplicacion");   
       /** obtener los datos **/
   int i; 
   int j;
   for (j = 0;j < 7 ;j++){
       vaplicationlayer[j] = 0;
   }
   
   for (i = 0;i < history.size();i++){
      paquete =  history.get(i); 
      new StPacketHandlerTCP(paquete);
   }  

 
   
   
  CategoryDataset dataset = createDataset();
  JFreeChart chart = createChart(dataset);
  chart = customizeChart(chart);
  ChartPanel chartPanel = new ChartPanel(chart);
  chartPanel.setPreferredSize(new Dimension(500, 270));
  getContentPane().add(chartPanel); 
  pack();
  setVisible(true);
  
  addWindowListener(new java.awt.event.WindowAdapter() {
         public void windowClosing(java.awt.event.WindowEvent evt) {
            	 exitForm(evt);
         }
    });
 
 
  }
  
  static void TCPPacket(TCPPacket tcpPacket ) {      
    /// Aplication Layer

    if (tcpPacket.getSourcePort()== 80 || tcpPacket.getDestinationPort() == 80){
        vaplicationlayer[0]++;
    } else {
        if (tcpPacket.getSourcePort()== 20 || tcpPacket.getDestinationPort() == 20 ||
            tcpPacket.getSourcePort()== 21 || tcpPacket.getDestinationPort() == 21){
            vaplicationlayer[1]++;
        }else{
           if (tcpPacket.getSourcePort()== 23 || tcpPacket.getDestinationPort() == 23){
               vaplicationlayer[2]++;    
           }else{
               if (tcpPacket.getSourcePort()== 22 || tcpPacket.getDestinationPort() == 22){
                vaplicationlayer[3]++;         
               }else{
                   if (tcpPacket.getSourcePort()== 25 || tcpPacket.getDestinationPort() == 25){
                     vaplicationlayer[4]++;         
                   }else{
                     if (tcpPacket.getSourcePort()== 110 || tcpPacket.getDestinationPort() == 110){
                       vaplicationlayer[5]++; 
                       }else{
                           vaplicationlayer[6]++;    
                       }    
                     } 
                   }
              } 
           }
      }
  }        
     
 
 private CategoryDataset createDataset(){
      // row keys...
      String series1 = "HTTP";
      String series2 = "FTP";
      String series3 = "Telnet";
      String series4 = "SSH";
      String series5 = "SMTP";
      String series6 = "POP3";
      String series7 = "Others";
      
      // column keys...
      String category1 = "Aplcation Layer Protocols";
   

      // create the dataset...
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();

      dataset.addValue(vaplicationlayer[0], series1, category1);  
      dataset.addValue(vaplicationlayer[1], series2, category1);  
      dataset.addValue(vaplicationlayer[2], series3, category1);
      dataset.addValue(vaplicationlayer[3], series4, category1);
      dataset.addValue(vaplicationlayer[4], series5, category1);
      dataset.addValue( vaplicationlayer[5], series6, category1);
      dataset.addValue( vaplicationlayer[6], series7, category1);
      
      
      return dataset;
   }

   private JFreeChart createChart(final CategoryDataset dataset){
      JFreeChart chart = ChartFactory.createBarChart3D(
         "Aplication Layer", // chart title
         "Tipo Protocolo", // domain axis label
         "Numero de paquetes", // range axis label
         dataset, // data
         PlotOrientation.VERTICAL, // orientation
         true, // include legend
         true, // tooltips?
         false // URLs?
         );
      return chart;
   }

   private JFreeChart customizeChart(final JFreeChart chart){
      return chart;
   }
 
    private void exitForm(java.awt.event.WindowEvent evt) {
    	 dispose();
    }
   
} //Fin clase