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

 

public class Estadisticas extends JFrame {
  
   protected Packet paquete;
   
   static protected int[] v = new int[7];
   
   public Estadisticas (CaptureHistory history) {
   /** obtener los datos **/
   super("Nivel Enlace/Red/Transporte");
       
   int i; 
   int j;
   for (j = 0;j < 7 ;j++){
       v[j] = 0;
   }
  
   
   for (i = 0;i < history.size();i++){
      paquete =  history.get(i); 
      new StPacketHandler(paquete);
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
     
  static void EtherPacket(EthernetPacket ethernetPacket ) {      
	   v[0]++;
        
  }
   static void ARPPacket (ARPPacket arpPacket) {      
	  v[1]++;
  }
    static void IPPacket(IPPacket ipPacket ) {      
	  v[2]++;
  }
   
  static void TCPPacket(TCPPacket tcpPacket ) {      
	  v[3]++;
  }        
     
          
   static void ICMPPacket(ICMPPacket icmpPacket) {      
  	  v[4]++;
   }
     
   static void IGMPPacket(IGMPPacket igmpPacket) {      
	  v[5]++;
   }
  
 static void UDPPacket(UDPPacket udpPacket ) {      
	  v[6]++;
  }

 private CategoryDataset createDataset(){
      // row keys...
      String series1 = "ARP";
      String series2 = "TCP";
      String series3 = "ICMP";
      String series4 = "IGMP";
      String series5 = "UDP";
      String series6 = "Other";
      
      // column keys...
      String category1 = "Protocolos";
   

      // create the dataset...
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();

      dataset.addValue(v[1], series1, category1);  
      dataset.addValue(v[3], series2, category1);  
      dataset.addValue(v[4], series3, category1);
      dataset.addValue(v[5], series4, category1);
      dataset.addValue(v[6], series5, category1);
      dataset.addValue(v[0], series6, category1);
    
 
      return dataset;
   }

   private JFreeChart createChart(final CategoryDataset dataset){
      JFreeChart chart = ChartFactory.createBarChart3D(
         "Link/Network/Transport Layer", // chart title
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
   
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {
    	 dispose();
    }
    
   
} //Fin clase
 
  
 
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    
 
  
   


 


      
      
      
      
 
 
  
  
  

