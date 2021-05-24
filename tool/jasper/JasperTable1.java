package com.zetcode.main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zetcode.bean.Order;

import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class JasperTable1 {

	private List<JasperPrint> jasperPrintList = new ArrayList<>();

	public void start() throws JRException, DRException {
		String xmlFile = "src/main/resources/jrxml/table.jrxml"; //Step 1
		JasperReport jreport = JasperCompileManager.compileReport(xmlFile); //Step 2
		//
		jasperPrintList.add(getJasperPrint1(jreport));//Step 3
		jasperPrintList.add(getJasperPrint2(jreport));
		jasperPrintList.add(getJasperPrint3(jreport));
		jasperPrintList.add(getJasperPrint4(jreport));
		//
		generateReport();//Step 4
	}

	/**
	 * @param jreport 
	 * @return
	 * @throws JRException
	 */
	private JasperPrint getJasperPrint1(JasperReport jreport) throws JRException {
		//
		ArrayList<Order> orders = getOrderList(new BigDecimal("5.4"));
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(orders);
		//
		Map<String,Object> params1 = new HashMap<>();
		params1.put("datasource", ds);
		return JasperFillManager.fillReport(jreport,
				params1, new JREmptyDataSource());
	}

	/**
	 * @param jreport2 
	 * @return
	 * @throws JRException
	 */
	private JasperPrint getJasperPrint2(JasperReport jreport) throws JRException {
		//
		ArrayList<Order> orders = getOrderList(new BigDecimal("6.4"));
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(orders);
		//
		Map<String,Object> params1 = new HashMap<>();
		params1.put("datasource", ds);
		return JasperFillManager.fillReport(jreport,
				params1, new JREmptyDataSource());
	}

	/**
	 * @param jreport2 
	 * @return
	 * @throws JRException
	 */
	private JasperPrint getJasperPrint3(JasperReport jreport) throws JRException {
		//
		ArrayList<Order> orders = getOrderList(new BigDecimal("7.4"));
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(orders);
		//
		Map<String,Object> params1 = new HashMap<>();
		params1.put("datasource", ds);
		return JasperFillManager.fillReport(jreport,
				params1, new JREmptyDataSource());
	}
	
	private JasperPrint getJasperPrint4(JasperReport jreport) throws JRException {
		//
		ArrayList<Order> orders = getOrderList(new BigDecimal("8.4"));
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(orders);
		//
		Map<String,Object> params1 = new HashMap<>();
		params1.put("datasource", ds);
		return JasperFillManager.fillReport(jreport,
				params1, new JREmptyDataSource());
	}


	/**
	 * @param jasperPrint1
	 * @param jasperPrint2
	 * @throws JRException
	 */
	public void generateReport() throws JRException {
		JRPdfExporter exporter = new JRPdfExporter();  		  
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("src/main/resources/table.pdf"));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCreatingBatchModeBookmarks(true);
		exporter.setConfiguration(configuration);
		exporter.exportReport();
	}

	/**
	 * @return
	 */
	private ArrayList<Order> getOrderList(BigDecimal unitPrice) {
		ArrayList<Order> orders = new ArrayList<>();
		for(int x=1;x<=5;x++) {
			orders.add(new Order("Item "+x, unitPrice, 4));

		}
		return orders;
	}

}
