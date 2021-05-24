package com.zetcode.main;

import com.zetcode.bean.Order;

import static net.sf.dynamicreports.report.builder.DynamicReports.export;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperTable2 {

	public void start() throws JRException, DRException {

		String xmlFile = "src/main/resources/jrxml/table.jrxml";
		JasperReport jreport = JasperCompileManager.compileReport(xmlFile);

		ArrayList<Order> orders = getOrderList();
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(orders);

		Map params = new HashMap();
		params.put("datasource", ds);

		JasperPrint jprint = JasperFillManager.fillReport(jreport,
				params, new JREmptyDataSource());

		JasperExportManager.exportReportToPdfFile(jprint,
				"src/main/resources/table.pdf");      


//				String xmlFile = "jrxml/table.jrxml";
//				JasperReportBuilder report = new JasperReportBuilder();
//				try {
//		            //LinkedHashMap<String, Object> jsonMap = jsonToMap(getDataSourceJson());
//		            LinkedHashMap<String, Object> jsonMap = new LinkedHashMap<>();
//		            jsonMap.put("item", "A");
//		            jsonMap.put("unitPrice", "B");
//		            jsonMap.put("quantity", "C");
//		            
//		            InputStream jrxmlInputStream = this.getClass().getClassLoader().getResourceAsStream(xmlFile);
//		
//		            //setDataSourceList(Collections.singletonList(jsonMap));
//		            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(Collections.singletonList(jsonMap));
//		            		
//		            //JRDataSource ds = createDataSource();
//		
//		            report.setDataSource(datasource);
//		            report.setTemplateDesign(jrxmlInputStream);
//		
//		            JasperPdfExporterBuilder pdfExporter = export.pdfExporter("src/main/resources/table.pdf");
//		            report.toPdf(pdfExporter);
//		        } finally {
//		            report = null;
//		        }
	}

	/**
	 * @return
	 */
	private ArrayList<Order> getOrderList() {
		ArrayList<Order> orders = new ArrayList<>();
		for(int x=1;x<=5;x++) {
			orders.add(new Order("Item "+x,new java.math.BigDecimal(5.4), x));
		}
		return orders;
	}
}
